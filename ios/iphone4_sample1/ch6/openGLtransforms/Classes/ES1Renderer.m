//
//  ES1Renderer.m
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

#import "ES1Renderer.h"

@implementation ES1Renderer

// Create an ES 1.1 context
- (id) init
{
	if (self = [super init])
	{
		context = [[EAGLContext alloc] initWithAPI:kEAGLRenderingAPIOpenGLES1];
        
        if (!context || ![EAGLContext setCurrentContext:context])
		{
            [self release];
            return nil;
        }
		
		// Create default framebuffer object. The backing will be allocated for the current layer in -resizeFromLayer
		glGenFramebuffersOES(1, &defaultFramebuffer);
		glGenRenderbuffersOES(1, &colorRenderbuffer);
		glBindFramebufferOES(GL_FRAMEBUFFER_OES, defaultFramebuffer);
		glBindRenderbufferOES(GL_RENDERBUFFER_OES, colorRenderbuffer);
		glFramebufferRenderbufferOES(GL_FRAMEBUFFER_OES, GL_COLOR_ATTACHMENT0_OES, GL_RENDERBUFFER_OES, colorRenderbuffer);
		
		//glEnable(GL_TEXTURE_2D);
		NSString * path = [[NSBundle mainBundle] pathForResource: @"unitSquare" ofType:@"pvrtc"];
		texture = [[PVRTexture alloc] initWithContentsOfFile: path];
		
		glBindTexture(GL_TEXTURE_2D, texture.name);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, 1.0f);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

		
		
	}
	
	return self;
}



- (void) render
{
    // Replace the implementation of this method to do your own custom drawing
    
    static const GLfloat squareVertices[] = {
        -1.f,  -1.f,
		1.f,  -1.f,       
        -1.f,   1.f,		
		1.f,   1.f,
    };
	
	static const GLfloat squareTextureCoords[] = {
		0.f,   1.f,
		1.f,   1.f,
        0.f,   0.f,
		1.f,   0.f,
    };
		
    static float t = 0.0;
	t += 1/30.f;
	
	// This application only creates a single context which is already set current at this point.
	// This call is redundant, but needed if dealing with multiple contexts.
    [EAGLContext setCurrentContext:context];    
	// This application only creates a single default framebuffer which is already bound at this point.
	// This call is redundant, but needed if dealing with multiple framebuffers.
    glBindFramebufferOES(GL_FRAMEBUFFER_OES, defaultFramebuffer);
    
	// ##################### Transform Demo #########################
	
	// ---------------------------------------------------------------
	// Determines the Normalized Device Coordinate -> Window Coordinate Transform
	// ---------------------------------------------------------------
	
	glViewport(0, 0, backingWidth, backingHeight);	
	
	// ---------------------------------------------------------------
	// Determines the Eye Coordinate -> Clip Coordinate Transform
	// ---------------------------------------------------------------

	glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
	//Typically see: glOrthof() -- 2D, glFrustumf() -- 3D	
	
	// ---------------------------------------------------------------
	// Determines the Object Coordinate -> Eye Coordinate Transform
	// ---------------------------------------------------------------
	glMatrixMode(GL_MODELVIEW);	
	glLoadIdentity();

	//Typically see: glTranslatef(), glRotatef(), glScalef()
	// order of operations matters
	
	// ###############################################################
	
	glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);

	glEnable(GL_TEXTURE_2D);
    glVertexPointer(2, GL_FLOAT, 0, squareVertices);
    glEnableClientState(GL_VERTEX_ARRAY);
    glTexCoordPointer(2, GL_FLOAT, 0, squareTextureCoords);
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    
	glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	glDisableClientState(GL_COLOR_ARRAY);
	
	// This application only creates a single color renderbuffer which is already bound at this point.
	// This call is redundant, but needed if dealing with multiple renderbuffers.
    glBindRenderbufferOES(GL_RENDERBUFFER_OES, colorRenderbuffer);
    [context presentRenderbuffer:GL_RENDERBUFFER_OES];
}

- (BOOL) resizeFromLayer:(CAEAGLLayer *)layer
{	
	// Allocate color buffer backing based on the current layer size
    glBindRenderbufferOES(GL_RENDERBUFFER_OES, colorRenderbuffer);
    [context renderbufferStorage:GL_RENDERBUFFER_OES fromDrawable:layer];
	glGetRenderbufferParameterivOES(GL_RENDERBUFFER_OES, GL_RENDERBUFFER_WIDTH_OES, &backingWidth);
    glGetRenderbufferParameterivOES(GL_RENDERBUFFER_OES, GL_RENDERBUFFER_HEIGHT_OES, &backingHeight);
	
    if (glCheckFramebufferStatusOES(GL_FRAMEBUFFER_OES) != GL_FRAMEBUFFER_COMPLETE_OES)
	{
		NSLog(@"Failed to make complete framebuffer object %x", glCheckFramebufferStatusOES(GL_FRAMEBUFFER_OES));
        return NO;
    }
    
    return YES;
}

- (void) dealloc
{
	// Tear down GL
	if (defaultFramebuffer)
	{
		glDeleteFramebuffersOES(1, &defaultFramebuffer);
		defaultFramebuffer = 0;
	}

	if (colorRenderbuffer)
	{
		glDeleteRenderbuffersOES(1, &colorRenderbuffer);
		colorRenderbuffer = 0;
	}
	
	// Tear down context
	if ([EAGLContext currentContext] == context)
        [EAGLContext setCurrentContext:nil];
	
	[context release];
	context = nil;
	
	[super dealloc];
}

@end
