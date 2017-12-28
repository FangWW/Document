//
//  CheckPleaseAppDelegate.h
//  CheckPlease
//
//  Created by jeff on 4/28/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CheckPleaseViewController;

@interface CheckPleaseAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    CheckPleaseViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet CheckPleaseViewController *viewController;

@end

