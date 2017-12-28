//
//  AppSettingsAppDelegate.m
//  AppSettings
//
//  Created by jeff on 4/22/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "AppSettingsAppDelegate.h"
#import "MainViewController.h"

@implementation AppSettingsAppDelegate


@synthesize window;
@synthesize mainViewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {
    
	MainViewController *aController = [[MainViewController alloc] initWithNibName:@"MainView" bundle:nil];
	self.mainViewController = aController;
	[aController release];
	
    mainViewController.view.frame = [UIScreen mainScreen].applicationFrame;
	[window addSubview:[mainViewController view]];
    [window makeKeyAndVisible];
}


- (void)dealloc {
    [mainViewController release];
    [window release];
    [super dealloc];
}

@end
