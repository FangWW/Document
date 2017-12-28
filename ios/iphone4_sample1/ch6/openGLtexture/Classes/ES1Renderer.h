//
//  ES1Renderer.h
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.

#import "ESRenderer.h"

#import <OpenGLES/ES1/gl.h>
#import <OpenGLES/ES1/glext.h>

#import "PVRTexture.h"

@interface ES1Renderer : NSObject <ESRenderer>
{
@private
	EAGLContext *context;
	
	// The pixel dimensions of the CAEAGLLayer
	GLint backingWidth;
	GLint backingHeight;
	
	// The OpenGL names for the framebuffer and renderbuffer used to render to this view
	GLuint defaultFramebuffer, colorRenderbuffer;
	PVRTexture * texture;
}

- (void) render;
- (BOOL) resizeFromLayer:(CAEAGLLayer *)layer;

@end
