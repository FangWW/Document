//
//  PickPhotoViewController.m
//  PickPhoto
//
//  Created by svp on 8/14/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "PickPhotoViewController.h"

@implementation PickPhotoViewController

@synthesize imageView;

@synthesize wordsLabel;

- (IBAction)showImagePicker:(id)sender
{
    UIImagePickerController *imagePickerController = [[UIImagePickerController alloc] init];
    imagePickerController.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
    imagePickerController.delegate = self;
    
    [self presentModalViewController:imagePickerController animated:YES];
    
    [imagePickerController release];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingImage:(UIImage *)image editingInfo:(NSDictionary *)editingInfo
{
    imageView.image = image;
    
    [self dismissModalViewControllerAnimated:YES];
	
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker
{
    [self dismissModalViewControllerAnimated:YES];    
	
}

- (IBAction)inputWords:(id)sender
{
    InputWordsViewController *inputWordsViewController = [[InputWordsViewController alloc] init];
    inputWordsViewController.delegate = self;
    
    [self presentModalViewController:inputWordsViewController animated:YES];
    
    [inputWordsViewController release];    
}

- (void)inputWordsViewController:(InputWordsViewController *)controller didInputWords:(NSString *)text
{
    wordsLabel.text = text;
    
    [self dismissModalViewControllerAnimated:YES];    
}



/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/


/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/


/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[imageView release];
	[wordsLabel release];
    [super dealloc];
}

@end
