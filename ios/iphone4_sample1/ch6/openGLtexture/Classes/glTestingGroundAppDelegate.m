//
//  glTestingGroundAppDelegate.m
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

#import "glTestingGroundAppDelegate.h"
#import "EAGLView.h"

@implementation glTestingGroundAppDelegate

@synthesize window;
@synthesize glView;

- (void) applicationDidFinishLaunching:(UIApplication *)application
{
	[application setStatusBarHidden:YES];
	[glView startAnimation];
}

- (void) applicationWillResignActive:(UIApplication *)application
{
	[glView stopAnimation];
}

- (void) applicationDidBecomeActive:(UIApplication *)application
{
	[glView startAnimation];
}

- (void)applicationWillTerminate:(UIApplication *)application
{
	[glView stopAnimation];
}

- (void) dealloc
{
	[window release];
	[glView release];
	
	[super dealloc];
}

@end
