//
//  WebPhotoesAppDelegate.h
//  WebPhotoes
//
//  Created by svp on 8/6/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PhotoTableViewController.h"

@interface WebPhotoesAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
	PhotoTableViewController *photoTableViewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

