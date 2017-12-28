//
//  lvyouAppDelegate.h
//  lvyou
//
//  Created by svp on 7/28/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface lvyouAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
	UINavigationController *navController;
	UITabBarController *tabBarController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

