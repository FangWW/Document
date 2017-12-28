//
//  OpenGLES2DView.h
//  GLFun
//
//  Created by Jeff LaMarche on 8/5/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <OpenGLES/EAGL.h>
#import <OpenGLES/ES1/gl.h>
#import <OpenGLES/ES1/glext.h>
#import <QuartzCore/QuartzCore.h>
#import <OpenGLES/EAGLDrawable.h>
#import "Constants.h"

@interface OpenGLES2DView : UIView {

@protected
	EAGLContext *context;
	GLuint viewRenderbuffer, viewFramebuffer;
	GLint backingWidth, backingHeight;
}

@end
