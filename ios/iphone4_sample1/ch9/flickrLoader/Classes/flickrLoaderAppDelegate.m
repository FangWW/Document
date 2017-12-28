//
//  flickrLoaderAppDelegate.m
//  flickrLoader


#import "flickrLoaderAppDelegate.h"
#import "RootViewController.h"


@implementation flickrLoaderAppDelegate

@synthesize window;
@synthesize navigationController;


#pragma mark -
#pragma mark Application lifecycle

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
	
    rootViewController = [[RootViewController alloc] initWithStyle:UITableViewStylePlain];
    rootViewController.view.frame = [UIScreen mainScreen].applicationFrame;
	rootViewController.title = @"杭州";
    [navigationController pushViewController:rootViewController animated:NO];
	 
	[window addSubview:[navigationController view]];
    [window makeKeyAndVisible];
	return YES;
}


- (void)applicationWillTerminate:(UIApplication *)application {
	// Save data if appropriate
}


#pragma mark -
#pragma mark Memory management

- (void)dealloc {
	[navigationController release];
	[window release];
	[super dealloc];
}


@end

