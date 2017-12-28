//
//  TapTapsAppDelegate.h
//  TapTaps
//
//  Created by jeff on 4/28/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TapTapsViewController;

@interface TapTapsAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    TapTapsViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet TapTapsViewController *viewController;

@end

