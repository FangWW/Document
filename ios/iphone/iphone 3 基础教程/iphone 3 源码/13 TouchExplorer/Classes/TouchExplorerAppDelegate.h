//
//  TouchExplorerAppDelegate.h
//  TouchExplorer
//
//  Created by jeff on 4/27/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TouchExplorerViewController;

@interface TouchExplorerAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    TouchExplorerViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet TouchExplorerViewController *viewController;

@end

