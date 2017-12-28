//
//  InputWordsViewController.m
//  PickPhoto
//
//  Created by svp on 8/14/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "InputWordsViewController.h"


@implementation InputWordsViewController

@synthesize textField;
@synthesize delegate;

- (IBAction)doneInput:(id)sender
{
	NSLog(@"enter the done input part");
    if ([self.delegate respondsToSelector:@selector(inputWordsViewController:didInputWords:)]) {
		NSLog(@"enter if statement");
        [self.delegate inputWordsViewController:self didInputWords:textField.text];
    }
}

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
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
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[textField release];
    [super dealloc];
}


@end
