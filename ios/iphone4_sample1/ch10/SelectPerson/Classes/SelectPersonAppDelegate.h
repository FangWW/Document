//
//  SelectPersonAppDelegate.h
//  SelectPerson
//
//  Created by svp on 8/14/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class SelectPersonViewController;

@interface SelectPersonAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    SelectPersonViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet SelectPersonViewController *viewController;

@end

