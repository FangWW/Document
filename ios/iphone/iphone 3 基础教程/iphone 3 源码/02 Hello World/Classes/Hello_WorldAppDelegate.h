//
//  Hello_WorldAppDelegate.h
//  Hello World
//
//  Created by Jeff LaMarche on 6/8/08.
//  Copyright __MyCompanyName__ 2008. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Hello_WorldViewController;

@interface Hello_WorldAppDelegate : NSObject <UIApplicationDelegate> {
	IBOutlet UIWindow *window;
	IBOutlet Hello_WorldViewController *viewController;
}

@property (nonatomic, retain) UIWindow *window;
@property (nonatomic, retain) Hello_WorldViewController *viewController;

@end

