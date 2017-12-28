//
//  CheckPleaseViewController.m
//  CheckPlease
//
//  Created by jeff on 4/28/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "CheckPleaseViewController.h"
#import "CGPointUtils.h"
@implementation CheckPleaseViewController
@synthesize label;
- (void)eraseLabel {
    label.text = @"";
}
- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
    self.label = nil;
    [super viewDidUnload];
}
- (void)dealloc {
    [label release];
    [super dealloc];
}
#pragma mark -
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    UITouch *touch = [touches anyObject];
    CGPoint point = [touch locationInView:self.view];
    lastPreviousPoint = point;
    lastCurrentPoint = point;
    lineLengthSoFar = 0.0f;
}
- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event {
    
    UITouch *touch = [touches anyObject];
    CGPoint previousPoint = [touch previousLocationInView:self.view];
    CGPoint currentPoint = [touch locationInView:self.view];
    CGFloat angle = angleBetweenLines(lastPreviousPoint, 
                                      lastCurrentPoint, 
                                      previousPoint, 
                                      currentPoint);
    
    if (angle >= kMinimumCheckMarkAngle&& angle <= kMaximumCheckMarkAngle 
        && lineLengthSoFar > kMinimumCheckMarkLength) {
        label.text = @"Checkmark";
        [self performSelector:@selector(eraseLabel)
                   withObject:nil afterDelay:1.6];
    }
    
    lineLengthSoFar += distanceBetweenPoints(previousPoint, currentPoint);
    lastPreviousPoint = previousPoint;
    lastCurrentPoint = currentPoint;
}

@end
