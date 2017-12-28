#import <UIKit/UIKit.h>
#import "Constants.h"

@interface QuartzFunView : UIView {
    CGPoint        firstTouch;
    CGPoint        lastTouch;
    UIColor        *currentColor;
    ShapeType      shapeType;
    UIImage        *drawImage;
    BOOL           useRandomColor;
    
    CGRect         redrawRect;
}
@property CGPoint firstTouch;
@property CGPoint lastTouch;
@property (nonatomic, retain) UIColor *currentColor;
@property ShapeType shapeType;
@property (nonatomic, retain) UIImage *drawImage;
@property BOOL useRandomColor;
@property CGRect redrawRect;
@end
