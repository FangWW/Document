//
//  HelloBeijingAppDelegate.h
//  HelloBeijing
//
//  Created by svp on 7/8/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HelloBeijingAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;

-(IBAction) createView   : (id)sender;



@end

