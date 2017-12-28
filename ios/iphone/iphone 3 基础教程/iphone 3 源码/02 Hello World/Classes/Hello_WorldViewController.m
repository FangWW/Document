//
//  Hello_WorldAppDelegate.m
//  Hello World
//
//  Created by Jeff LaMarche on 6/8/08.
//  Copyright __MyCompanyName__ 2008. All rights reserved.
//

#import "Hello_WorldViewController.h"

@implementation Hello_WorldViewController

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
	// Return YES for supported orientations
	return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (void)didReceiveMemoryWarning {
	[super didReceiveMemoryWarning]; // Releases the view if it doesn't have a superview
	// Release anything that's not essential, such as cached data
}

- (void)dealloc {
	[super dealloc];
}

@end
