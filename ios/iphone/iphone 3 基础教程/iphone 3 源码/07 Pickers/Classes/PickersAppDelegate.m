//
//  PickersAppDelegate.m
//  Pickers
//
//  Created by Jeff LaMarche on 7/7/08.
//  Copyright __MyCompanyName__ 2008. All rights reserved.
//

#import "PickersAppDelegate.h"

@implementation PickersAppDelegate

@synthesize window;
@synthesize rootController;

- (void)applicationDidFinishLaunching:(UIApplication *)application {	
	
	// Override point for customization after app launch	
	[window addSubview:rootController.view];
    [window makeKeyAndVisible];
}


- (void)dealloc {
	[rootController release];
	[window release];
	[super dealloc];
}

@end
