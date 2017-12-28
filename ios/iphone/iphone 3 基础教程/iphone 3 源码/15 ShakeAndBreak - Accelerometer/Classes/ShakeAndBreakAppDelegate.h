//
//  ShakeAndBreakAppDelegate.h
//  ShakeAndBreak
//
//  Created by jeff on 4/29/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class ShakeAndBreakViewController;

@interface ShakeAndBreakAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    ShakeAndBreakViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet ShakeAndBreakViewController *viewController;

@end

