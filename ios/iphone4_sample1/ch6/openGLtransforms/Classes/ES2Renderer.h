//
//  ES2Renderer.h
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

#import "ESRenderer.h"

#import <OpenGLES/ES2/gl.h>
#import <OpenGLES/ES2/glext.h>

@interface ES2Renderer : NSObject <ESRenderer>
{
@private
	EAGLContext *context;
	
	// The pixel dimensions of the CAEAGLLayer
	GLint backingWidth;
	GLint backingHeight;
	
	// The OpenGL names for the framebuffer and renderbuffer used to render to this view
	GLuint defaultFramebuffer, colorRenderbuffer;
	
	GLuint program;
}

- (void) render;
- (BOOL) resizeFromLayer:(CAEAGLLayer *)layer;

@end

