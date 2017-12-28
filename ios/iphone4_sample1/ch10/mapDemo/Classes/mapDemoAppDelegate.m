//
//  mapDemoAppDelegate.m
//  mapDemo
//
//  Created by wkso on 7/30/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "mapDemoAppDelegate.h"
#import "mapDemoViewController.h"

@implementation mapDemoAppDelegate

@synthesize window;
@synthesize viewController;


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
	
	return YES;
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
