//
//  MultiTouchDemoAppDelegate.h
//  MultiTouchDemo
//


#import <UIKit/UIKit.h>

@class MultiTouchDemoViewController;

@interface MultiTouchDemoAppDelegate : NSObject <UIApplicationDelegate> {
	IBOutlet UIWindow *window;
	IBOutlet MultiTouchDemoViewController *viewController;
}

@property (nonatomic, retain) UIWindow *window;
@property (nonatomic, retain) MultiTouchDemoViewController *viewController;

@end

