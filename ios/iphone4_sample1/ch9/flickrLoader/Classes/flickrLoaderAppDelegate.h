//
//  flickrLoaderAppDelegate.h
//  flickrLoader


#import <UIKit/UIKit.h>
#import "RootViewController.h"

@interface flickrLoaderAppDelegate : NSObject <UIApplicationDelegate> {
    
    UIWindow *window;
    UINavigationController *navigationController;
	RootViewController *rootViewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;

@end

