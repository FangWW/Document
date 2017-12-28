/*
    File: LevelAppDelegate.m
Abstract: LevelAppDelegate is responsible for creating the application window at
launch and instantiating a LevelViewController, which manages the views that
will appear in that window.

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

#import "LevelAppDelegate.h"
#import "LevelViewController.h"

static NSString *BubbleLevelCalibrationOffsetKey = @"BubbleLevelCalibrationOffsetKey";

@implementation LevelAppDelegate

@synthesize window;
@synthesize levelViewController;

// +initialize is invoked before the class receives any other messages, so it
// is a good place to set up application defaults
+ (void)initialize {
    if ([self class] == [LevelAppDelegate class]) {
        // Register a default value for the instrument calibration. 
        // This will be used if the user hasn't calibrated the instrument.
        NSNumber *defaultCalibrationOffset = [NSNumber numberWithFloat:0.0];
        NSDictionary *resourceDict = [NSDictionary dictionaryWithObject:defaultCalibrationOffset forKey:BubbleLevelCalibrationOffsetKey];
		[[NSUserDefaults standardUserDefaults] registerDefaults:resourceDict];
    }
}


// Invoked after the application has been launched and initialized but before it has received its first event.
- (void)applicationDidFinishLaunching:(UIApplication *)application {
	// Set up the level view controller
    levelViewController = [[LevelViewController alloc] init];
    [window addSubview:levelViewController.view];
    
    // Restore calibration for device
    float restoredOffset = [[NSUserDefaults standardUserDefaults] floatForKey:BubbleLevelCalibrationOffsetKey];
    levelViewController.calibrationOffset = restoredOffset;
}


// Invoked immediately before the application terminates.
- (void)applicationWillTerminate:(UIApplication *)application {
    float calibrationOffset = levelViewController.calibrationOffset;
    NSNumber *offset = [NSNumber numberWithFloat:calibrationOffset];
    [[NSUserDefaults standardUserDefaults] setObject:offset forKey:BubbleLevelCalibrationOffsetKey];
}


- (void)dealloc {
    [levelViewController release];
    [window release];
	[super dealloc];
}

@end
