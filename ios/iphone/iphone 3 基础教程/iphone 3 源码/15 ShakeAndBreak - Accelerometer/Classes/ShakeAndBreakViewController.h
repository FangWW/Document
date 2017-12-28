//
//  ShakeAndBreakViewController.h
//  ShakeAndBreak
//
//  Created by jeff on 4/29/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AudioToolbox/AudioToolbox.h>

#define kAccelerationThreshold        2.2
#define kUpdateInterval               (1.0f/10.0f)

@interface ShakeAndBreakViewController : UIViewController
    <UIAccelerometerDelegate> {
    UIImageView    *imageView;
    
    BOOL            brokenScreenShowing;
    SystemSoundID   soundID;
    UIImage        *fixed;
    UIImage        *broken;
}
@property (nonatomic, retain) IBOutlet UIImageView *imageView;
@property (nonatomic, retain) UIImage *fixed;
@property (nonatomic, retain) UIImage *broken;
@end

