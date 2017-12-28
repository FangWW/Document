//
//  QuartzFunAppDelegate.m
//  QuartzFun
//
//  Created by jeff on 4/27/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "QuartzFunAppDelegate.h"
#import "QuartzFunViewController.h"

@implementation QuartzFunAppDelegate

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
