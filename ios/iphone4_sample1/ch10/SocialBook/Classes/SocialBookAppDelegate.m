#import "SocialBookAppDelegate.h"
#import "SocialPeopleTableViewController.h"

@implementation SocialBookAppDelegate

@synthesize window;
@synthesize navigationController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {	
    SocialPeopleTableViewController *viewController = [[SocialPeopleTableViewController alloc] init];
	navigationController = [[UINavigationController alloc] initWithRootViewController:viewController];
    [viewController release];
    [window addSubview:navigationController.view];
    
}

- (void)dealloc {
    [navigationController release];
	[window release];
	[super dealloc];
}


@end
