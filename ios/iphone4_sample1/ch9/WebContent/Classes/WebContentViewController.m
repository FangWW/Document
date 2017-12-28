//
//  WebContentViewController.m
//  WebContent
//
//  Created by svp on 8/11/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "WebContentViewController.h"

@implementation WebContentViewController

-(BOOL) textFieldShouldReturn:(UITextField *)textField
{
	NSURL *url = [NSURL  URLWithString:textField.text];
	NSURLRequest *request = [NSURLRequest requestWithURL:url];
	[webView loadRequest:request];
	return YES;
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



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
	
	NSString *htmlContent = @"<div style=\"font-family:Helvetica, Arial, sans-serif;font-size:48pt;\" align=\"center\">"; 
	NSMutableString *htmlPage =[NSMutableString  new];
	[htmlPage appendString:htmlContent];
	[htmlContent release];
	[ htmlPage appendString:@"欢迎使用手机网页"]; 
	[ htmlPage appendString: @"</ span>"]; 
	[webView loadHTMLString:htmlPage baseURL:nil]; 	
	[htmlPage release];
    [super viewDidLoad];
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
	[webView release];
    [super dealloc];
}

@end
