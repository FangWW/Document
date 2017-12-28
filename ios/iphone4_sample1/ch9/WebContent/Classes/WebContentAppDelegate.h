//
//  WebContentAppDelegate.h
//  WebContent
//
//  Created by svp on 8/11/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class WebContentViewController;

@interface WebContentAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    WebContentViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet WebContentViewController *viewController;

@end

