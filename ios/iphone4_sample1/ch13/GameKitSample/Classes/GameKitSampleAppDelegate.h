//
//  GameKitSampleAppDelegate.h
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright Apple 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface GameKitSampleAppDelegate : NSObject <UIApplicationDelegate> {
	IBOutlet UINavigationController	*nav;
    UIWindow *window;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@end

