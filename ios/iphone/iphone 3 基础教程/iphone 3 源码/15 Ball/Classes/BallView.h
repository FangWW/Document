//
//  BallView.h
//  Ball
//
//  Created by jeff on 4/29/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <UIKit/UIKit.h>
#define kVelocityMultiplier    500

@interface BallView : UIView {
    UIImage *image;
    
    CGPoint    currentPoint;
    CGPoint    previousPoint;
    
    UIAcceleration *acceleration;
    CGFloat    ballXVelocity;
    CGFloat     ballYVelocity;
}
@property (nonatomic, retain) UIImage *image;
@property CGPoint currentPoint;
@property CGPoint previousPoint;
@property (nonatomic, retain) UIAcceleration *acceleration;
@property CGFloat ballXVelocity;
@property CGFloat ballYVelocity;
- (void)draw;
@end
