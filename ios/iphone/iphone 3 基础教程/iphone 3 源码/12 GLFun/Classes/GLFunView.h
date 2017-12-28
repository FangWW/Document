#import <UIKit/UIKit.h>
#import "Constants.h"
#import "Texture2D.h"
#import "OpenGLES2DView.h"


@interface GLFunView : OpenGLES2DView {
    CGPoint        firstTouch;
    CGPoint        lastTouch;
    UIColor        *currentColor;
    BOOL        useRandomColor;
    
    ShapeType    shapeType;
    
    Texture2D    *sprite;
}
@property CGPoint firstTouch;
@property CGPoint lastTouch;
@property (nonatomic, retain) UIColor *currentColor;
@property BOOL useRandomColor;
@property ShapeType shapeType;
@property (nonatomic, retain) Texture2D *sprite;
@end
