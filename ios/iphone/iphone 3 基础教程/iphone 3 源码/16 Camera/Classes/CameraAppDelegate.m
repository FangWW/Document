//
//  CameraAppDelegate.m
//  Camera
//
//  Created by jeff on 4/29/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "CameraAppDelegate.h"
#import "CameraViewController.h"

@implementation CameraAppDelegate

@synthesize window;
@synthesize viewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
