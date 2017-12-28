//
//  GameKitSampleAppDelegate.m
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright Apple 2010. All rights reserved.
//

#import "GameKitSampleAppDelegate.h"
#import "CommunalTouchBoard.h"

@implementation GameKitSampleAppDelegate

@synthesize window;


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    

    // Override point for customization after application launch
	UIViewController	*viewController = [[CommunalTouchBoard alloc] initWithNibName:@"CommunalTouchBoard" bundle:nil];
	viewController.view.frame = [UIScreen mainScreen].applicationFrame;
	[window addSubview:viewController.view];
	[window makeKeyAndVisible];
	
	return YES;
}


- (void)dealloc {
    [window release];
    [super dealloc];
}

@end
