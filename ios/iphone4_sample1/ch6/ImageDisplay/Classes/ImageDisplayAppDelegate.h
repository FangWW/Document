//
//  ImageDisplayAppDelegate.h
//  ImageDisplay
//
//  Created by svp on 8/2/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class ImageDisplayViewController;

@interface ImageDisplayAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    ImageDisplayViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet ImageDisplayViewController *viewController;

@end

