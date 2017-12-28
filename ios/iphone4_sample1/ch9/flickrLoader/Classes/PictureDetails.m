//
//  PictureDetails.m
//  flickrLoader


#import "PictureDetails.h"
#import "JSON.h"
#import "ImageLoadingOperation.h"

@interface PictureDetails (Internal)
- (void)synchronousLoadFlickrData;
- (void)didFinishLoadingFlickrDataWithResults:(NSDictionary *)results;
@end


@implementation PictureDetails

@synthesize photoID;
@synthesize photoTitle;
@synthesize description;
@synthesize userName;
@synthesize realName;
@synthesize imageView;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [self synchronousLoadFlickrData];
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
	
	UIBarButtonItem *foodButton = [[UIBarButtonItem alloc]
								   initWithTitle:@"美食信息" style:UIBarButtonItemStyleBordered target:self action:@selector(foodInfo:)];
	self.navigationItem.rightBarButtonItem = foodButton;
	[foodButton release];
	
    [super viewDidLoad];
}


/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

#pragma mark -
#pragma mark Flickr Data Loading

- (void)synchronousLoadFlickrData
{
    // Construct a Flickr API request.
	// Important! Enter your Flickr API key in FlickrAPIKey.h
    NSString *urlString = [NSString stringWithFormat:@"http://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=%@&photo_id=%@&format=json&nojsoncallback=1", @"583366ad887b297494873eb1710fa739", photoID];
    NSURL *url = [NSURL URLWithString:urlString];

    // Get the contents of the URL as a string, and parse the JSON into Foundation objects.
    NSString *jsonString = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
	NSLog(@"%@", jsonString);
    NSDictionary *results = [jsonString JSONValue];    
    
    [self performSelectorOnMainThread:@selector(didFinishLoadingFlickrDataWithResults:) withObject:results waitUntilDone:NO];
}

- (void)didFinishLoadingFlickrDataWithResults:(NSDictionary *)results
{
	NSDictionary *photo = [results objectForKey:@"photo"];
	
	[description setText:[[photo objectForKey:@"description"] objectForKey:@"_content"]];
	[photoTitle setText:[[photo objectForKey:@"title"] objectForKey:@"_content"]];
	[userName setText:[[photo objectForKey:@"owner"] objectForKey:@"username"]];
	[realName setText:[[photo objectForKey:@"owner"] objectForKey:@"realname"]];

	NSString *photoURLString = [NSString stringWithFormat:@"http://farm%@.static.flickr.com/%@/%@_%@_m.jpg", [photo objectForKey:@"farm"], [photo objectForKey:@"server"], [photo objectForKey:@"id"], [photo objectForKey:@"secret"]];
	NSURL *url = [NSURL URLWithString:photoURLString];

	NSData *data = [[NSData alloc] initWithContentsOfURL:url];
    UIImage *image = [[UIImage alloc] initWithData:data];
    
	[imageView setImage:image];
	
//	[data release];
//	[image release];
}

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

@end
