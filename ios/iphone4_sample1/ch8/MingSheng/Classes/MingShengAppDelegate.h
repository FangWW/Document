//
//  MingShengAppDelegate.h
//  MingSheng
//
//  Created by svp on 8/5/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MsTableViewController.h"

@interface MingShengAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
	MsTableViewController *msTableViewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

