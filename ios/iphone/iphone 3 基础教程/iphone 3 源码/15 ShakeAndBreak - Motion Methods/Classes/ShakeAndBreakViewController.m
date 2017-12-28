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
    
    NSString *path = [[NSBundle mainBundle] pathForResource:@"glass"
                                                     ofType:@"wav"];
    AudioServicesCreateSystemSoundID((CFURLRef)[NSURL
                                                fileURLWithPath:path], &soundID);
    
    self.fixed = [UIImage imageNamed:@"home.png"];
    self.broken = [UIImage imageNamed:@"homebroken.png"];
    
    imageView.image = fixed;
    [self.view becomeFirstResponder];
}
- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    [self becomeFirstResponder];
}
- (BOOL) canBecomeFirstResponder {
    return YES;
}
- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
    self.imageView = nil;
    self.fixed = nil;
    self.broken = nil;
}
- (void)dealloc {
    [imageView release];
    [fixed release];
    [broken release];    
    [super dealloc];
}
#pragma mark Touch Handling
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    imageView.image = fixed;
    brokenScreenShowing = NO;
}
#pragma mark Motion Handling
- (void)motionEnded:(UIEventSubtype)motion withEvent:(UIEvent *)event {
    imageView.image = broken;
    AudioServicesPlaySystemSound (soundID);
    brokenScreenShowing = YES;    
    [super motionEnded:motion withEvent:event];
}
@end
