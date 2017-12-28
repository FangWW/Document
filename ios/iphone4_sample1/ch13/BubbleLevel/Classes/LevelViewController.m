/*
    File: LevelViewController.m
Abstract: The LevelViewController object manages the views that make up the user
interface of the Bubble Level application.

 Version: 1.9

Disclaimer: IMPORTANT:  This Apple software is supplied to you by Apple
Inc. ("Apple") in consideration of your agreement to the following
terms, and your use, installation, modification or redistribution of
this Apple software constitutes acceptance of these terms.  If you do
not agree with these terms, please do not use, install, modify or
redistribute this Apple software.

In consideration of your agreement to abide by the following terms, and
subject to these terms, Apple grants you a personal, non-exclusive
license, under Apple's copyrights in this original Apple software (the
"Apple Software"), to use, reproduce, modify and redistribute the Apple
Software, with or without modifications, in source and/or binary forms;
provided that if you redistribute the Apple Software in its entirety and
without modifications, you must retain this notice and the following
text and disclaimers in all such redistributions of the Apple Software.
Neither the name, trademarks, service marks or logos of Apple Inc. may
be used to endorse or promote products derived from the Apple Software
without specific prior written permission from Apple.  Except as
expressly stated in this notice, no other rights or licenses, express or
implied, are granted by Apple herein, including but not limited to any
patent rights that may be infringed by your derivative works or by other
works in which the Apple Software may be incorporated.

The Apple Software is provided by Apple on an "AS IS" basis.  APPLE
MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS USE AND
OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.

IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED
AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

Copyright (C) 2010 Apple Inc. All Rights Reserved.

*/

#import "LevelViewController.h"
#import "CalibrationView.h"
#import "LevelView.h"

#define kTransitionDuration	0.75
#define kUpdateFrequency 20  // Hz
#define kFilteringFactor 0.05
#define kNoReadingValue 999

@implementation LevelViewController

@synthesize calibrationView;
@synthesize calibrationOffset;

#pragma mark -
#pragma mark === Setting up / Tearing down ===
#pragma mark -

- init {
	if (self = [super init]) {
		[[UIAccelerometer sharedAccelerometer] setUpdateInterval:(1.0 / kUpdateFrequency)];
		[[UIAccelerometer sharedAccelerometer] setDelegate:self];
        calibrationOffset = 0.0;
        firstCalibrationReading = kNoReadingValue;
	}
	return self;
}

- (void)dealloc {
    [levelView release]; 
    [calibrationView release]; 
    [super dealloc];
}


- (void)viewDidLoad {
	CGRect applicationFrame = [[UIScreen mainScreen] applicationFrame];

	// add the top-most parent view
	UIView *contentView = [[UIView alloc] initWithFrame:applicationFrame];
	contentView.backgroundColor = [UIColor blackColor];
	self.view = contentView;
	[contentView release];

	levelView = [[LevelView alloc] initWithFrame:applicationFrame viewController:self];
	[self.view addSubview:levelView];
    
    calibrationView = [[CalibrationView alloc] initWithFrame:applicationFrame viewController:self];
}


#pragma mark -
#pragma mark === Flip action ===
#pragma mark -
- (void)flipAction:(id)sender {
	[UIView beginAnimations:nil context:NULL];
	[UIView setAnimationDuration:kTransitionDuration];
	[UIView setAnimationTransition:([levelView superview] ? UIViewAnimationTransitionFlipFromLeft : UIViewAnimationTransitionFlipFromRight) forView:self.view cache:YES];
	
	if ([calibrationView superview]) {
		[calibrationView removeFromSuperview];
		[self.view addSubview:levelView];
	} else {
		[levelView removeFromSuperview];
        [calibrationView resetToInitialState:self];
		[self.view addSubview:calibrationView];
	}
	[UIView commitAnimations];
}


#pragma mark -
#pragma mark === Calibration ===
#pragma mark -
- (float)calibratedAngleFromAngle:(float)rawAngle {
    float calibratedAngle = calibrationOffset + rawAngle;
    return calibratedAngle;
}

- (void)calibrate1Action:(id)sender {
    firstCalibrationReading = currentRawReading;
}

- (void)calibrate2Action:(id)sender {
    // can't calibrate unless there's an initial reading
    if (firstCalibrationReading != kNoReadingValue) {
        // get the sign of the measurement with the max displacement. The offset will have the opposite sign.
        float maxDisplacement = (fabs(firstCalibrationReading) > fabs(currentRawReading)) ? firstCalibrationReading : currentRawReading;
         NSInteger sign = (maxDisplacement >= 0) ? -1 : 1;
         calibrationOffset = sign * (fabs(firstCalibrationReading) + fabs(currentRawReading)) / 2;
    } else {
        NSLog(@" no initial reading, can't calculate offset");
    }
    
    // reset for next calibration
    firstCalibrationReading != kNoReadingValue;
}


#pragma mark -
#pragma mark === Responding to accelerations ===
#pragma mark -
// UIAccelerometer delegate method, which delivers the latest acceleration data.
- (void)accelerometer:(UIAccelerometer *)accelerometer didAccelerate:(UIAcceleration *)acceleration {
    // Use a basic low-pass filter to only keep the gravity in the accelerometer values for the X and Y axes
    accelerationX = acceleration.x * kFilteringFactor + accelerationX * (1.0 - kFilteringFactor);
    accelerationY = acceleration.y * kFilteringFactor + accelerationY * (1.0 - kFilteringFactor);
    
    // keep the raw reading, to use during calibrations
    currentRawReading = atan2(accelerationY, accelerationX);
    
    float calibratedAngle = [self calibratedAngleFromAngle:currentRawReading];
    
    [levelView updateToInclinationInRadians:calibratedAngle];
}

@end
