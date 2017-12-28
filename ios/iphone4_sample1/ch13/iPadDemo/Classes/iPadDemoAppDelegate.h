//
//  iPadDemoAppDelegate.h
//  iPadDemo
//
//  Created by wkso on 9/2/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class iPadDemoViewController;

@interface iPadDemoAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    iPadDemoViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet iPadDemoViewController *viewController;

@end

