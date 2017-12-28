//
//  PickPhotoAppDelegate.h
//  PickPhoto
//
//  Created by svp on 8/14/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class PickPhotoViewController;

@interface PickPhotoAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    PickPhotoViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet PickPhotoViewController *viewController;

@end

