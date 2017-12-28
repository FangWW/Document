//
//  ShakeAndBreakViewController.m
//  ShakeAndBreak
//
//  Created by jeff on 4/29/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "ShakeAndBreakViewController.h"

@implementation ShakeAndBreakViewController
@synthesize imageView;
@synthesize fixed;
@synthesize broken;
- (void) viewDidLoad {
    UIAccelerometer *accel = [UIAccelerometer sharedAccelerometer];
    accel.delegate = self;
    accel.updateInterval = kUpdateInterval;
    
    NSString *path = [[NSBundle mainBundle] pathForResource:@"glass"
                                                     ofType:@"wav"];
    AudioServicesCreateSystemSoundID((CFURLRef)[NSURL
                                                fileURLWithPath:path], &soundID);
    
    self.fixed = [UIImage imageNamed:@"home.png"];
    self.broken = [UIImage imageNamed:@"homebroken.png"];
    
    imageView.image = fixed;
}
- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
    self.imageView = nil;
    self.fixed = nil;
    self.broken = nil;
    [super viewDidUnload];
}
- (void)dealloc {
    [imageView release];
    [fixed release];
    [broken release];    
    [super dealloc];
}
#pragma mark -
- (void)accelerometer:(UIAccelerometer *)accelerometer 
        didAccelerate:(UIAcceleration *)acceleration {
    if (! brokenScreenShowing) {
        if (acceleration.x > kAccelerationThreshold 
            || acceleration.y > kAccelerationThreshold
            || acceleration.z > kAccelerationThreshold) {
            imageView.image = broken;
            AudioServicesPlaySystemSound (soundID);
            brokenScreenShowing = YES;
        }
    }
}
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    imageView.image = fixed;
    brokenScreenShowing = NO;
}
@end
