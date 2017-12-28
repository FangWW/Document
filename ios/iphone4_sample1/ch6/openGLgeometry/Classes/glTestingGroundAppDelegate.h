//
//  glTestingGroundAppDelegate.h
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class EAGLView;

@interface glTestingGroundAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    EAGLView *glView;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet EAGLView *glView;

@end

