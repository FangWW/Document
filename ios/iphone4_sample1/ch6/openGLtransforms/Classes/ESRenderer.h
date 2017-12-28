//
//  ESRenderer.h
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

#import <QuartzCore/QuartzCore.h>

#import <OpenGLES/EAGL.h>
#import <OpenGLES/EAGLDrawable.h>

@protocol ESRenderer <NSObject>

- (void) render;
- (BOOL) resizeFromLayer:(CAEAGLLayer *)layer;

@end
