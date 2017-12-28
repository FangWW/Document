//
//  BallViewController.m
//  Ball
//
//  Created by jeff on 4/29/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "BallViewController.h"
#import "BallView.h"
@implementation BallViewController

- (void)viewDidLoad {
    UIAccelerometer *accelerometer = [UIAccelerometer sharedAccelerometer];
    accelerometer.delegate = self;
    accelerometer.updateInterval =  kUpdateInterval;
    [super viewDidLoad];
}
#pragma mark -
- (void)accelerometer:(UIAccelerometer *)accelerometer 
        didAccelerate:(UIAcceleration *)acceleration {
    
    [(BallView *)self.view setAcceleration:acceleration];
    [(BallView *)self.view draw];
}
@end
