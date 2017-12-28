/*
    File: LevelView.m
Abstract: LevelView builds and displays the primary user interface of the Bubble
Level application.

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

#import "LevelView.h"
#import "CalibrationView.h"
#import "SoundEffect.h"
#import <QuartzCore/QuartzCore.h>

#define kMaxVariationForFarSound 6.0
#define kMaxVariationForNearSound 2.0
#define kMaxVariationForLevelSound 0.5
#define kMaxAngle 90.0

CGFloat DegreesToRadians(CGFloat degrees) {return degrees * M_PI / 180;};
CGFloat RadiansToDegrees(CGFloat radians) {return radians * 180/M_PI;};


@interface LevelView (PrivateMethods)
- (void)setupSounds;
- (void)setupSubviewsWithContentFrame:(CGRect)frameRect;
@end


@implementation LevelView

@synthesize viewController;

#pragma mark -
#pragma mark === Setting up / Tearing down ===
#pragma mark -

- (id)initWithFrame:(CGRect)frame viewController:(LevelViewController *)aController {
    self = [super initWithFrame:frame];
    if (self != nil) {
        self.viewController = aController;

        // set default state
        holdButtonIsShowing = YES;
        
        // set up sounds and views
        [self setupSounds];
        [self setupSubviewsWithContentFrame:frame];
    }
    return self;
}

- (void)setupSounds {
    NSBundle *mainBundle = [NSBundle mainBundle];

    farSound = [[SoundEffect alloc] initWithContentsOfFile:[mainBundle pathForResource:@"farSound" ofType:@"caf"]];
    nearSound = [[SoundEffect alloc] initWithContentsOfFile:[mainBundle pathForResource:@"nearSound" ofType:@"caf"]];
    levelSound = [[SoundEffect alloc] initWithContentsOfFile:[mainBundle pathForResource:@"levelSound" ofType:@"caf"]];
}

- (UIButton *)buttonWithTitle:(NSString *)title target:(id)target selector:(SEL)inSelector frame:(CGRect)frame image:(UIImage*)image {
	UIButton *button = [[UIButton alloc] initWithFrame:frame];
	button.contentVerticalAlignment = UIControlContentVerticalAlignmentCenter;
	button.contentHorizontalAlignment = UIControlContentHorizontalAlignmentCenter;
	[button setTitle:title forState:UIControlStateNormal & UIControlStateHighlighted & UIControlStateSelected];
	[button setTitleColor:[UIColor blackColor] forState:UIControlEventTouchDown];
	UIImage *newImage = [image stretchableImageWithLeftCapWidth:10 topCapHeight:10];
	[button setBackgroundImage:newImage forState:UIControlStateNormal];
	[button addTarget:target action:inSelector forControlEvents:UIControlEventTouchUpInside];
    button.adjustsImageWhenDisabled = YES;
    button.adjustsImageWhenHighlighted = YES;
	[button setBackgroundColor:[UIColor clearColor]];	// in case the parent view draws with a custom color or gradient, use a transparent color
    [button autorelease];
    return button;
}

- (void)setupSubviewsWithContentFrame:(CGRect)frameRect {
    UIImageView *levelFrontView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"levelBackground.png"]];
    levelFrontView.center = self.center;
    levelFrontView.opaque = YES;
   
    // set up bubble view
    bubbleView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"bubble.png"]];
    bubbleView.center = self.center;
    
    // set up vial lines view
    UIImageView *vialLinesView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"vial_lines.png"]];
    vialLinesView.center = self.center;
    
    // set up up/down arrow view
    upDownArrowsView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"arrows_cw.png"]];
    upDownArrowsView.center = self.center;

    // set up down/up arrow view
    downUpArrowsView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"arrows_ccw.png"]];
    downUpArrowsView.center = self.center;
    downUpArrowsView.hidden = YES;  

    // set up info button
    // since the 'i' image is small, we make the button frame larger than the image to make it easier for the user to hit
    float infoShiftRight = 284.0;
    float sizeIncrease = 20.0;
    
	UIButton *infoButton = [[UIButton buttonWithType:UIButtonTypeInfoLight] retain];
	infoButton.backgroundColor = [UIColor clearColor];
    infoButton.frame = CGRectMake(infoShiftRight, 0.0, infoButton.frame.size.width+sizeIncrease, infoButton.frame.size.height+sizeIncrease);
	[infoButton addTarget:self.viewController action:@selector(flipAction:) forControlEvents:UIControlEventTouchUpInside];

    // set up Hold button
    float holdShiftRight = 190.0;
    float holdShiftDown = 32.0;
    
	UIImage *buttonImage = [UIImage imageNamed:@"hold_button.png"];
	CGRect buttonFrame = CGRectMake(holdShiftRight, holdShiftDown, buttonImage.size.width, buttonImage.size.height);
	holdButton = [self buttonWithTitle:nil target:self selector:@selector(toggleHoldButton:) frame:buttonFrame image:buttonImage];
    
    // set up shadow degree display, to give displayed text more punch
    // this is an identical text display, but in white and offset 2 pixels
    float displayTextWidth = 180.0;
    float displayTextHeight = 60.0;
    float displayShiftRight = 124.0;
    float displayShiftDown = 264.0;
    UIFont *displayFont = [UIFont fontWithName:@"Helvetica" size:60];
    shadowDegreeDisplay = [[UILabel alloc] initWithFrame:CGRectMake(displayShiftRight-2.0, displayShiftDown, displayTextWidth, displayTextHeight)];
    shadowDegreeDisplay.font = displayFont;
    shadowDegreeDisplay.textColor = [UIColor whiteColor];
    shadowDegreeDisplay.backgroundColor = [UIColor clearColor];
    shadowDegreeDisplay.textAlignment = UITextAlignmentCenter;
    
    // set up degree display
    degreeDisplay = [[UILabel alloc] initWithFrame:CGRectMake(displayShiftRight, displayShiftDown, displayTextWidth, displayTextHeight)];
    degreeDisplay.font = displayFont;
    degreeDisplay.textColor = [UIColor colorWithRed:66.0/255.0 green:73.0/255.0 blue:113.0/255.0 alpha:1.0];
    degreeDisplay.backgroundColor = [UIColor clearColor];
    degreeDisplay.textAlignment = UITextAlignmentCenter;
    
    // Transform for rotating textual display
	CATransform3D landscapeTransform = CATransform3DIdentity;
    landscapeTransform = CATransform3DRotate(landscapeTransform, DegreesToRadians(-90), 0, 0, 1);
    degreeDisplay.layer.transform = landscapeTransform;
    shadowDegreeDisplay.layer.transform = landscapeTransform;
    infoButton.layer.transform = landscapeTransform;
            
    // add view in proper order and location
    [self addSubview:levelFrontView];
    [self addSubview:upDownArrowsView];
    [self addSubview:downUpArrowsView];
    [self addSubview:bubbleView];
    [self addSubview:vialLinesView];
    [self addSubview:shadowDegreeDisplay];
    [self addSubview:degreeDisplay];
    [self addSubview:holdButton];
    [self addSubview:infoButton];

    // release views we no longer need to address
    [levelFrontView release];
    [vialLinesView release];
    
    [self setNeedsDisplay];
}

- (void)dealloc {
    [bubbleView release];
    [upDownArrowsView release];
    [downUpArrowsView release];
    [shadowDegreeDisplay release];
    [degreeDisplay release];
    [holdButton release];
    [farSound release];
    [nearSound release];
    [levelSound release];    
    [super dealloc];
}

#pragma mark -
#pragma mark === Actions ===
#pragma mark -
- (void)updateLevelSoundForAngle:(float)angle {
    float absAngle = fabs(angle);
    
    if (absAngle <= kMaxVariationForLevelSound) {
        [levelSound play];
    } else if (absAngle <= kMaxVariationForNearSound) {
        [nearSound play];
    } else if (absAngle <= kMaxVariationForFarSound) {
        [farSound play];
    }
}

- (void)updateArrowsForAngle:(float)angle {
    if (angle < 0.0) {
        downUpArrowsView.hidden = YES;
        upDownArrowsView.hidden = NO;
    } else if (angle > 0.0) {
        downUpArrowsView.hidden = NO;
        upDownArrowsView.hidden = YES;
    } else {  // turn off the arrows if we're level
        downUpArrowsView.hidden = YES;
        upDownArrowsView.hidden = YES;
    }
}

- (void)updateBubbleForAngle:(float)angle {
    float halfVialLength = 320.0 / 2;    
    float zoomAngle = angle * 4 ;  // real bubble floats up more rapidly than sine function
    
    if (zoomAngle > kMaxAngle) zoomAngle = kMaxAngle ;   // stop at the end
	if (zoomAngle < -kMaxAngle) zoomAngle = -kMaxAngle ; // stop at the other end
    
    float newY = self.center.y - sin(DegreesToRadians(zoomAngle)) * halfVialLength;
    
    bubbleView.center=CGPointMake(self.center.x, newY);
}

- (void)updateReadoutForAngle:(float)angle {
    // limit it to no more or less than the maximum angle from level
    if (angle > kMaxAngle) angle = kMaxAngle;
    if (angle < -kMaxAngle) angle = -kMaxAngle;
    
    NSString *newAngleString = [NSString stringWithFormat:@"%0.1f", angle];
    NSString *angleStringWithDegree = [newAngleString stringByAppendingString:@"º"];
    shadowDegreeDisplay.text = angleStringWithDegree;
    degreeDisplay.text = angleStringWithDegree;
    [degreeDisplay setNeedsDisplay];
    [shadowDegreeDisplay setNeedsDisplay];
}

- (void)updateToInclinationInRadians:(float)rads {
    float rotation = -RadiansToDegrees(rads);
    static int soundUpdateCounter = 0;
    
    if (holdButtonIsShowing) {  // Don't update if user has toggled the Hold button to the Release state
        [self updateReadoutForAngle:rotation];
        [self updateBubbleForAngle:rotation];
        [self updateArrowsForAngle:rotation];
        soundUpdateCounter++;
    
        if (soundUpdateCounter == 10) { // update sound at a tenth the rate of the animation
            [self updateLevelSoundForAngle:rotation];
            soundUpdateCounter = 0;
        }
    }
}

// Display only updates if Hold/Release button hasn't been pressed 
- (void)toggleHoldButton:(id)sender {
    if (holdButtonIsShowing == YES) {
        holdButtonIsShowing = NO;
        [holdButton setImage:[UIImage imageNamed:@"release_button.png"] forState:UIControlStateNormal];
    } else {
        holdButtonIsShowing = YES;
        // set image on Hold button
        [holdButton setImage:[UIImage imageNamed:@"hold_button.png"] forState:UIControlStateNormal];
    }    
}

@end
