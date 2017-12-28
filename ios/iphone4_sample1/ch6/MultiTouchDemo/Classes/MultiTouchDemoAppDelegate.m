//
//  MultiTouchDemoAppDelegate.m
//  MultiTouchDemo


#import "MultiTouchDemoAppDelegate.h"
#import "MultiTouchDemoViewController.h"

@implementation MultiTouchDemoAppDelegate

@synthesize window;
@synthesize viewController;

- (void)applicationDidFinishLaunching:(UIApplication *)application
{	
    [window addSubview:viewController.view];
	[window makeKeyAndVisible];
}

- (void)dealloc
{
    [viewController release];
	[window release];
	[super dealloc];
}

@end
