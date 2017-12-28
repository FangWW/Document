//
//  SocialBookAppDelegate.m
//  SocialBook
//
//  Created by Alexandre Aybes on 5/17/08.
//  Copyright Apple, Inc. 2008. All rights reserved.
//

#import "SocialBookViewController.h"

@implementation SocialBookViewController

- (void)loadView {
}


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
