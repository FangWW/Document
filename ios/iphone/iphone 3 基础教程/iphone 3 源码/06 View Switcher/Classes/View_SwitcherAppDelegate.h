//
//  View_SwitcherAppDelegate.h
//  View Switcher
//
//  Created by Jeff LaMarche on 7/6/08.
//  Copyright __MyCompanyName__ 2008. All rights reserved.
//

#import <UIKit/UIKit.h>

@class SwitchViewController;

@interface View_SwitcherAppDelegate : NSObject <UIApplicationDelegate> {
	IBOutlet UIWindow *window;
	IBOutlet SwitchViewController *switchViewController;
}

@property (nonatomic, retain) UIWindow *window;
@property (nonatomic, retain) SwitchViewController *switchViewController;

@end

