/*
    File: CalibrationView.m
Abstract: CalibrationView builds and displays the calibration interface.

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

#import "CalibrationView.h"
#import "LevelViewController.h"
#import <QuartzCore/QuartzCore.h>

@interface CalibrationView (PrivateMethods) 
- (void)setupSubviews;
@end

@implementation CalibrationView

@synthesize viewController;

- (id)initWithFrame:(CGRect)frame viewController:(LevelViewController *)aController {
    self = [super initWithFrame:frame];
    if (self != nil) {
        self.viewController = aController;
        [self setupSubviews];
    }
    return self;
}

- (void)dealloc {
    [calibration1Button release];
    [calibration2Button release];
	[viewController release];
    [calibrationCompleteView release];
	[super dealloc];
}

// utility to retrieve app name (potentially localized) from plist
- (id)infoValueForKey:(NSString*)key {
	// InfoPlist.strings entries have priority over Info.plist ones.
	if ([[[NSBundle mainBundle] localizedInfoDictionary] objectForKey:key]) {
		return [[[NSBundle mainBundle] localizedInfoDictionary] objectForKey:key];
    }
	return [[[NSBundle mainBundle] infoDictionary] objectForKey:key];
}

- (UIButton *)buttonWithFrame:(CGRect)frame normalImage:(UIImage *)normalImage highlightedImage:(UIImage *)highlightedImage disabledImage:(UIImage *)disabledImage target:(id)target selector:(SEL)inSelector {
	UIButton *button = [[UIButton alloc] initWithFrame:frame];
	button.contentVerticalAlignment = UIControlContentVerticalAlignmentCenter;
	button.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
    
    // Image for normal state
	UIImage *newNormalImage = [normalImage stretchableImageWithLeftCapWidth:10 topCapHeight:10];
	[button setBackgroundImage:newNormalImage forState:UIControlStateNormal];
    
    // Image for highlighted state
	UIImage *newHighlightedImage = [highlightedImage stretchableImageWithLeftCapWidth:10 topCapHeight:10];
	[button setBackgroundImage:newHighlightedImage forState:UIControlStateHighlighted];
    
    // Image for disabled state
    UIImage *newDisabledImage = [disabledImage stretchableImageWithLeftCapWidth:10 topCapHeight:10];
	[button setBackgroundImage:newDisabledImage forState:UIControlStateDisabled];
    
	[button addTarget:target action:inSelector forControlEvents:UIControlEventTouchUpInside];
    button.adjustsImageWhenDisabled = YES;
    button.adjustsImageWhenHighlighted = YES;
	[button setBackgroundColor:[UIColor clearColor]];	// in case the parent view draws with a custom color or gradient, use a transparent color
    [button autorelease];
    return button;
}


- (void)setupSubviews {
	[self setOpaque:YES];
    
    UIImageView *levelBackView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"calibrate_background.png"]];
    levelBackView.center = self.center;
    levelBackView.opaque = YES;
    [self addSubview:levelBackView];
    [levelBackView release];
    
    // Add Done button
    float doneShiftDown = 6.0;
    float doneShiftRight = 6.0;
    
	UIImage *imageNormal = [UIImage imageNamed:@"calibrate_done_button.png"];
	UIImage *imageHighlighted = nil; 
	UIImage *imageDisabled = nil;
    
	CGRect buttonFrame = CGRectMake(doneShiftRight, doneShiftDown, imageNormal.size.width, imageNormal.size.height);
	UIButton *doneButton = [self buttonWithFrame:buttonFrame normalImage:imageNormal highlightedImage:imageHighlighted disabledImage:imageDisabled target:self.viewController selector:@selector(flipAction:)];
    [self addSubview:doneButton];
        
    // set up Calibrate1 button
    float c1ShiftRight = 75.0;
    float calibrateButtonsShiftDown = 36.0;
    
	imageNormal = [UIImage imageNamed:@"calibrate1_button.png"];
	imageHighlighted = [UIImage imageNamed:@"calibrate1_highlight.png"];
	imageDisabled = [UIImage imageNamed:@"calibrate1_disabled.png"];
	buttonFrame = CGRectMake(c1ShiftRight, calibrateButtonsShiftDown, imageNormal.size.width, imageNormal.size.height);
    calibration1Button = [self buttonWithFrame:buttonFrame normalImage:imageNormal highlightedImage:imageHighlighted disabledImage:imageDisabled target:self selector:@selector(calibrate1Action:)];
    calibration1Button.enabled = YES;
    [self addSubview:calibration1Button];
    
    // set up Calibrate2 button
    float c2ShiftRight = 210.0;
	imageNormal = [UIImage imageNamed:@"calibrate2_button.png"];
	imageHighlighted = [UIImage imageNamed:@"calibrate2_highlight.png"];
	imageDisabled = [UIImage imageNamed:@"calibrate2_disabled.png"];
	buttonFrame = CGRectMake(c2ShiftRight, calibrateButtonsShiftDown, imageNormal.size.width, imageNormal.size.height);
    calibration2Button = [self buttonWithFrame:buttonFrame normalImage:imageNormal highlightedImage:imageHighlighted disabledImage:imageDisabled target:self selector:@selector(calibrate2Action:)];
    calibration2Button.enabled = NO; 
    [self addSubview:calibration2Button];
    
    // set up Calibration Complete overlay
    calibrationCompleteView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"calibration_complete_large.png"]];
    calibrationCompleteView.center = self.center;
    calibrationCompleteView.hidden = YES;
    [self addSubview:calibrationCompleteView];
}

- (void)resetToInitialState:(id)sender {
    calibrationCompleteView.hidden = YES;
    calibration2Button.enabled = NO; 
}

- (void)calibrate1Action:(id)sender {
    [self.viewController calibrate1Action:self];
    calibration2Button.enabled = YES; 
}

- (void)calibrate2Action:(id)sender {
    [self.viewController calibrate2Action:self];
    calibrationCompleteView.hidden = NO;
}

@end
