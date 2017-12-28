//
//  YunWenJianAppDelegate.h
//  YunWenJian
//
//  Created by svp on 8/21/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class YunWenJianViewController;

@interface YunWenJianAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    YunWenJianViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet YunWenJianViewController *viewController;

@end

