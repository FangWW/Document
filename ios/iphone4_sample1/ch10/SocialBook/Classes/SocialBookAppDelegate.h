#import <UIKit/UIKit.h>

@class SocialBookViewController;

@interface SocialBookAppDelegate : NSObject <UIApplicationDelegate> {
	IBOutlet UIWindow *window;
	
    UINavigationController *navigationController;
}

@property (nonatomic, retain) UIWindow *window;
@property (nonatomic, retain) UINavigationController *navigationController;

@end

