//
//  ImageDisplayViewController.m
//  ImageDisplay
//
//  Created by svp on 8/2/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "ImageDisplayViewController.h"

@implementation ImageDisplayViewController



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



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
	
	UIImage *image = [UIImage imageNamed:@"IMG_1936.JPG"];
	imageView = [[UIImageView alloc] initWithImage:image];
	[self.view  addSubview:imageView];
	[(UIScrollView*) self.view  setContentSize:[image size]];
	[(UIScrollView*) self.view  setMaximumZoomScale:2.0];
    [super viewDidLoad];
}

-(UIView *) viewForZoomingInScrollView:(UIScrollView *) scrollView{
	return imageView;
}

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
    [super dealloc];
}

@end
