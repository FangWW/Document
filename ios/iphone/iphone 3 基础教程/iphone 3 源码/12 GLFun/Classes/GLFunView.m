#import "GLFunView.h"
#import "UIColor-Random.h"

@implementation GLFunView
@synthesize firstTouch;
@synthesize lastTouch;
@synthesize currentColor;
@synthesize useRandomColor;
@synthesize shapeType;
@synthesize sprite;
- (id)initWithCoder:(NSCoder*)coder {
    if (self = [super initWithCoder:coder]) {
        self.currentColor = [UIColor redColor];
        self.useRandomColor = NO;
        self.sprite = [[Texture2D alloc] initWithImage:[UIImage 
                                            imageNamed:@"iphone.png"]];
        glBindTexture(GL_TEXTURE_2D, sprite.name);
    }
    return self;
}

- (void)draw  {
    glLoadIdentity();
    
    glClearColor(0.78f, 0.78f, 0.78f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    
    CGColorRef color = currentColor.CGColor;
    const CGFloat *components = CGColorGetComponents(color);
    CGFloat red = components[0];
    CGFloat green = components[1];
    CGFloat blue = components[2];
    
    glColor4f(red,green, blue, 1.0);
    
    switch (shapeType) {
        case kLineShape: {
            glDisable(GL_TEXTURE_2D);
            GLfloat vertices[4];
            
            // Convert coordinates
            vertices[0] =  firstTouch.x;
            vertices[1] = self.frame.size.height - firstTouch.y;
            vertices[2] = lastTouch.x;
            vertices[3] = self.frame.size.height - lastTouch.y;
            glLineWidth(2.0);
            glVertexPointer (2, GL_FLOAT , 0, vertices);
            glDrawArrays (GL_LINES, 0, 2);
            break;
        }
        case kRectShape: {
            glDisable(GL_TEXTURE_2D);
            // Calculate bounding rect and store in vertices
            GLfloat vertices[8];
            GLfloat minX = (firstTouch.x > lastTouch.x) ?
            lastTouch.x : firstTouch.x;
            GLfloat minY = (self.frame.size.height - firstTouch.y > 
                            self.frame.size.height - lastTouch.y) ? 
            self.frame.size.height - lastTouch.y : 
            self.frame.size.height - firstTouch.y;
            GLfloat maxX = (firstTouch.x > lastTouch.x) ?
            firstTouch.x : lastTouch.x;
            GLfloat maxY = (self.frame.size.height - firstTouch.y > 
                            self.frame.size.height - lastTouch.y) ? 
            self.frame.size.height - firstTouch.y : 
            self.frame.size.height - lastTouch.y;
            
            vertices[0] = maxX;
            vertices[1] = maxY;
            vertices[2] = minX;
            vertices[3] = maxY;
            vertices[4] = minX;
            vertices[5] = minY;
            vertices[6] = maxX;
            vertices[7] = minY;
            
            glVertexPointer (2, GL_FLOAT , 0, vertices);
            glDrawArrays (GL_TRIANGLE_FAN, 0, 4);
            break;
        }
        case kEllipseShape: {
            glDisable(GL_TEXTURE_2D);
            GLfloat vertices[720];
            GLfloat xradius = (firstTouch.x > lastTouch.x) ?
            (firstTouch.x - lastTouch.x)/2 : 
            (lastTouch.x - firstTouch.x)/2;
            GLfloat yradius = (self.frame.size.height - firstTouch.y > 
                               self.frame.size.height - lastTouch.y) ? 
            ((self.frame.size.height - firstTouch.y) - 
             (self.frame.size.height - lastTouch.y))/2 : 
            ((self.frame.size.height - lastTouch.y) -
             (self.frame.size.height - firstTouch.y))/2; 
            for (int i = 0; i <= 720; i+=2) {
                GLfloat xOffset = (firstTouch.x > lastTouch.x) ?
                lastTouch.x + xradius 
                : firstTouch.x + xradius;
                GLfloat yOffset = (self.frame.size.height - firstTouch.y > 
                                   self.frame.size.height - lastTouch.y) ?
                self.frame.size.height - lastTouch.y + yradius : 
                self.frame.size.height - firstTouch.y + yradius;
                vertices[i] = (cos(degreesToRadian(i))*xradius) + xOffset;
                vertices[i+1] = (sin(degreesToRadian(i))*yradius) +
                yOffset;
                
            }
            glVertexPointer (2, GL_FLOAT , 0, vertices);
            glDrawArrays (GL_TRIANGLE_FAN, 0, 360);
            break;
            
        }
        case kImageShape:
            glEnable(GL_TEXTURE_2D);
            [sprite drawAtPoint:CGPointMake(lastTouch.x, 
                                            self.frame.size.height - lastTouch.y)];
            break;
        default:
            break;
    }
    
    glBindRenderbufferOES(GL_RENDERBUFFER_OES, viewRenderbuffer);
    [context presentRenderbuffer:GL_RENDERBUFFER_OES];
}
- (void)dealloc {
    [currentColor release];
    [sprite release];
    [super dealloc];
}
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    if (useRandomColor)
        self.currentColor = [UIColor randomColor];
    
    UITouch* touch = [[event touchesForView:self] anyObject];
    firstTouch = [touch locationInView:self];
    lastTouch = [touch locationInView:self];
    [self draw];
}
- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event {  
    
    UITouch *touch = [touches anyObject];
    lastTouch = [touch locationInView:self];
    
    [self draw];
    
}
- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event {
    UITouch *touch = [touches anyObject];
    lastTouch = [touch locationInView:self];
    
    [self draw];
}
@end
