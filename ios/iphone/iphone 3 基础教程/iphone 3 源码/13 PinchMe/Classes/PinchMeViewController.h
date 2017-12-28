//
//  PinchMeViewController.h
//  PinchMe
//
//  Created by jeff on 4/28/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#define kMinimumPinchDelta 100
@interface PinchMeViewController : UIViewController {
    UILabel *label;
    CGFloat initialDistance;
}
@property (nonatomic, retain) IBOutlet UILabel *label;
@property CGFloat initialDistance;
- (void)eraseLabel;
@end

