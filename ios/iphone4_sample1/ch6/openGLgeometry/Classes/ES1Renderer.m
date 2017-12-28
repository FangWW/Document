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
		
		glEnable(GL_CULL_FACE);
	}
	
	return self;
}



- (void) render
{
    // Replace the implementation of this method to do your own custom drawing

	
	// This application only creates a single context which is already set current at this point.
	// This call is redundant, but needed if dealing with multiple contexts.
    [EAGLContext setCurrentContext:context];    
	// This application only creates a single default framebuffer which is already bound at this point.
	// This call is redundant, but needed if dealing with multiple framebuffers.
    glBindFramebufferOES(GL_FRAMEBUFFER_OES, defaultFramebuffer);
    
	
	
	// ---------------------------------------------------------------
	// Determines the Normalized Device Coordinate -> Window Coordinate Transform
	// ---------------------------------------------------------------
	
	glViewport(0, 0, backingWidth, backingHeight);    
	
	// ---------------------------------------------------------------
	// Determines the Eye Coordinate -> Clip Coordinate Transform
	// ---------------------------------------------------------------

	glMatrixMode(GL_PROJECTION);
    glLoadIdentity();	
	glFrustumf(-2, 2, -3, 3, 5, 20);
	
	
	
	// ---------------------------------------------------------------
	// Determines the Object Coordinate -> Eye Coordinate Transform
	// ---------------------------------------------------------------
	glMatrixMode(GL_MODELVIEW);	
	glLoadIdentity();
	
	glTranslatef(0, 0, -8);
	
	glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
	
	// ####################### Geometry Demo #########################

#define TETRA_FRONT   0.0,    0.0,    1.0
#define TETRA_RIGHT 0.943,    0.0, -0.333
#define TETRA_TOP  -0.471,  0.816, -0.333
#define TETRA_LEFT -0.471, -0.816, -0.333
	
	static const GLfloat tetraVertices[] = {
		TETRA_TOP,
		TETRA_RIGHT,
		TETRA_LEFT,
		TETRA_FRONT,
		TETRA_TOP,
		TETRA_RIGHT,		
	};
	
#define RED      255,  0,  0,255
#define GREEN      0,255,  0,255
#define BLUE       0,  0,255,255
#define YELLOW   255,255,  0,255
	
	static const GLubyte tetraColors[] = {
		RED,
		GREEN,
		BLUE,
		YELLOW,
		RED,
		GREEN,		
	};
	
	static float t = 0.0;
	t += 1/30.f;
	//glVertexPointer(3, <#GLenum type#>, <#GLsizei stride#>, <#const GLvoid *pointer#>)
	glVertexPointer(3, GL_FLOAT, 0, tetraVertices);
    glEnableClientState(GL_VERTEX_ARRAY);
	glColorPointer(4, GL_UNSIGNED_BYTE,0, tetraColors);
	glEnableClientState(GL_COLOR_ARRAY);
	
	
	
	glPushMatrix();
	glTranslatef(-2, 2, -3);	
	glRotatef(30*t, 0, 1, 0);
	glScalef(3, 3, 3);
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 6);	
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(2, 2, 0);	
	glRotatef(-30*t, 1, 0, 0);
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 6);	
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(2, -2, 0);	
	glRotatef(-60*t, 1, 0, 1);
	glScalef(2, 2, 2);
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 6);	
	glPopMatrix();	 	 	 
	
	
	glDisableClientState(GL_VERTEX_ARRAY);
	glDisableClientState(GL_COLOR_ARRAY);
	
	// ###############################################################
	
	
	
	
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
