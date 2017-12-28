//
//  LocalizeMeAppDelegate.h
//  LocalizeMe
//
//  Created by jeff on 4/30/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class LocalizeMeViewController;

@interface LocalizeMeAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    LocalizeMeViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet LocalizeMeViewController *viewController;

@end

