//
//  mapDemoAppDelegate.h
//  mapDemo
//
//  Created by wkso on 7/30/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class mapDemoViewController;

@interface mapDemoAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    mapDemoViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet mapDemoViewController *viewController;

@end

