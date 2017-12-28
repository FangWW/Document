#import "RootViewController.h"
#import "JSON.h"
#import "ImageLoadingOperation.h"
#import "PictureDetails.h"
#import "FlickrAPIKey.h"

NSString *const LoadingPlaceholder = @"Loading";

const NSInteger NumberOfImages = 30;

// http://www.flickr.com/services/api/

@interface RootViewController (Internal)
- (void)showLoadingIndicators;
- (void)hideLoadingIndicators;

- (void)beginLoadingFlickrData;
- (void)synchronousLoadFlickrData;
- (void)didFinishLoadingFlickrDataWithResults:(NSDictionary *)results;

- (UIImage *)cachedImageForURL:(NSURL *)url;
@end

@implementation RootViewController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        photoURLs = [[NSMutableArray alloc] init];
        photoNames = [[NSMutableArray alloc] init];
        photoIDs = [[NSMutableArray alloc] init];
		
        // We only want our operation queue to perform one operation at a time.
        // Trying to perform a bunch of network loading operations at once in this case is a bad idea.
        operationQueue = [[NSOperationQueue alloc] init];
        [operationQueue setMaxConcurrentOperationCount:1];
        
        cachedImages = [[NSMutableDictionary alloc] init];
    }
    return self;
}

- (void)dealloc
{
    [photoNames release];
    [photoURLs release];
	[photoIDs release];
	
    [operationQueue release];
    
    [cachedImages release];
    
    [super dealloc];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    
    [self showLoadingIndicators];
    [self beginLoadingFlickrData];
}

- (void)viewDidLoad
{
    UIBarButtonItem *rightButton = [[UIBarButtonItem alloc] 
									initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(add:)];
	self.navigationItem.rightBarButtonItem = rightButton;
	[rightButton release];
	self.tableView.rowHeight = 95; // 75 pixel square image + 10 pixels of padding on either side.
}

#pragma mark -
#pragma mark Table View Datasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [photoNames count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Default"];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithFrame:CGRectZero reuseIdentifier:@"Default"] autorelease];
    }
    
    cell.textLabel.text = [photoNames objectAtIndex:indexPath.row];    
    cell.imageView.image = [self cachedImageForURL:[photoURLs objectAtIndex:indexPath.row]];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	NSLog(@"selected");
	PictureDetails *pictureDetailedViewController = [[PictureDetails alloc] initWithNibName:@"PictureDetails" bundle:[NSBundle mainBundle]];
	pictureDetailedViewController.photoID = [photoIDs objectAtIndex:indexPath.row];
	 
	[self.navigationController pushViewController:pictureDetailedViewController animated:YES];
	[pictureDetailedViewController release];
}

#pragma mark -
#pragma mark Loading Progress UI

- (void)showLoadingIndicators
{
    if (!spinner) {
        spinner = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
        [spinner startAnimating];
        
        loadingLabel = [[UILabel alloc] initWithFrame:CGRectZero];
        loadingLabel.font = [UIFont systemFontOfSize:20];
        loadingLabel.textColor = [UIColor grayColor];
        loadingLabel.text = @"Loading...";
        [loadingLabel sizeToFit];
        
        static CGFloat bufferWidth = 8.0;
        
        CGFloat totalWidth = spinner.frame.size.width + bufferWidth + loadingLabel.frame.size.width;
        
        CGRect spinnerFrame = spinner.frame;
        spinnerFrame.origin.x = (self.tableView.bounds.size.width - totalWidth) / 2.0;
        spinnerFrame.origin.y = (self.tableView.bounds.size.height - spinnerFrame.size.height) / 2.0;
        spinner.frame = spinnerFrame;
        [self.tableView addSubview:spinner];
        
        CGRect labelFrame = loadingLabel.frame;
        labelFrame.origin.x = (self.tableView.bounds.size.width - totalWidth) / 2.0 + spinnerFrame.size.width + bufferWidth;
        labelFrame.origin.y = (self.tableView.bounds.size.height - labelFrame.size.height) / 2.0;
        loadingLabel.frame = labelFrame;
        [self.tableView addSubview:loadingLabel];
    }
}

- (void)hideLoadingIndicators
{
    if (spinner) {
        [spinner stopAnimating];
        [spinner removeFromSuperview];
        [spinner release];
        spinner = nil;
        
        [loadingLabel removeFromSuperview];
        [loadingLabel release];
        loadingLabel = nil;
    }
}

#pragma mark -
#pragma mark Flickr Data Loading

- (void)beginLoadingFlickrData
{
    // One way to use operations is to create an invocation operation,
    // packaging up a target and selector to run.
    NSInvocationOperation *operation = [[NSInvocationOperation alloc] initWithTarget:self selector:@selector(synchronousLoadFlickrData) object:nil];
    [operationQueue addOperation:operation];
    [operation release];
}

- (void)synchronousLoadFlickrData
{
    // Construct a Flickr API request.
	// Important! Enter your Flickr API key in FlickrAPIKey.h
    NSString *urlString = [NSString stringWithFormat:@"http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=%@&tags=%@&per_page=%d&format=json&nojsoncallback=1", FlickrAPIKey, @"Hanzhou", NumberOfImages];
    NSURL *url = [NSURL URLWithString:urlString];
    
    // Get the contents of the URL as a string, and parse the JSON into Foundation objects.
    NSString *jsonString = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    NSDictionary *results = [jsonString JSONValue];    
    
    [self performSelectorOnMainThread:@selector(didFinishLoadingFlickrDataWithResults:) withObject:results waitUntilDone:NO];
}

- (void)didFinishLoadingFlickrDataWithResults:(NSDictionary *)results
{
    // Now we need to dig through the resulting objects.
    // Read the documentation and make liberal use of the debugger or logs.
    NSArray *photos = [[results objectForKey:@"photos"] objectForKey:@"photo"];
    for (NSDictionary *photo in photos) {
        // Get the title for each photo
        NSString *title = [photo objectForKey:@"title"];
        [photoNames addObject:(title.length > 0 ? title : @"Untitled")];
        
        // Construct the URL for each photo.
        NSString *photoURLString = [NSString stringWithFormat:@"http://farm%@.static.flickr.com/%@/%@_%@_s.jpg", [photo objectForKey:@"farm"], [photo objectForKey:@"server"], [photo objectForKey:@"id"], [photo objectForKey:@"secret"]];
        [photoURLs addObject:[NSURL URLWithString:photoURLString]];
		[photoIDs addObject:[photo objectForKey:@"id"]];
    }    
    
    [self hideLoadingIndicators];
    [self.tableView reloadData];
    [self.tableView flashScrollIndicators]; // Hint to the user how many items are in the list.
}

#pragma mark -
#pragma mark Cached Image Loading

- (UIImage *)cachedImageForURL:(NSURL *)url
{
    id cachedObject = [cachedImages objectForKey:url];
    if (cachedObject == nil) {
        // Set the loading placeholder in our cache dictionary.
        [cachedImages setObject:LoadingPlaceholder forKey:url];        
        
        // Create and enqueue a new image loading operation
        ImageLoadingOperation *operation = [[ImageLoadingOperation alloc] initWithImageURL:url target:self action:@selector(didFinishLoadingImageWithResult:)];
        [operationQueue addOperation:operation];
        [operation release];
    } else if (![cachedObject isKindOfClass:[UIImage class]]) {
        // We're already loading the image. Don't kick off another request.
        cachedObject = nil;
    }
    
    return cachedObject;
}

- (void)didFinishLoadingImageWithResult:(NSDictionary *)result
{
    NSURL *url = [result objectForKey:@"url"];
    UIImage *image = [result objectForKey:@"image"];
    
    // Store the image in our cache.
    // One way to enhance this application further would be to purge images that haven't been used lately,
    // or to purge aggressively in response to a memory warning.
    [cachedImages setObject:image forKey:url];
    
    [self.tableView reloadData];
}

@end
