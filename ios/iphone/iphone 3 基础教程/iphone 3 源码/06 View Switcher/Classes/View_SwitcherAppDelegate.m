//
//  View_SwitcherAppDelegate.m
//  View Switcher
//
//  Created by Jeff LaMarche on 7/6/08.
//  Copyright __MyCompanyName__ 2008. All rights reserved.
//

#import "View_SwitcherAppDelegate.h"
#import "SwitchViewController.h"

@implementation View_SwitcherAppDelegate

@synthesize window;
@synthesize switchViewController;

- (void)applicationDidFinishLaunching:(UIApplication *)application {	
	
	// Override point for customization after app launch	
	[window addSubview:switchViewController.view];
    [window makeKeyAndVisible];
}


- (void)dealloc {
	[window release];
	[switchViewController release];
	[super dealloc];
}


@end
