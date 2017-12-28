//
//  TouchImageView.h
//  MultiTouchDemo
//


#import <UIKit/UIKit.h>


@interface TouchImageView : UIImageView {
    CGAffineTransform originalTransform;
    CFMutableDictionaryRef touchBeginPoints;
}

@end
