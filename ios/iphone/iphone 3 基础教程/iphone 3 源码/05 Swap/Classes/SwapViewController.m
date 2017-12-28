//
//  SwapViewController.m
//  Swap
//
//  Created by jeff on 4/1/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "SwapViewController.h"

@implementation SwapViewController
@synthesize landscape;
@synthesize portrait;
@synthesize landscapeFooButton;
@synthesize portraitFooButton;
@synthesize landscapeBarButton;
@synthesize portraitBarButton;
-(IBAction)buttonPressed:(id)sender {
	
	if (sender == portraitFooButton || sender == landscapeFooButton) {
		portraitFooButton.hidden = YES;
		landscapeFooButton.hidden = YES;
	}
	else {
		portraitBarButton.hidden = YES;
		landscapeBarButton.hidden = YES;
	}
}
- (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)
interfaceOrientation duration:(NSTimeInterval)duration {	
    if (interfaceOrientation == UIInterfaceOrientationPortrait)
    {
        self.view = self.portrait;
        self.view.transform = CGAffineTransformIdentity;
        self.view.transform = CGAffineTransformMakeRotation(degreesToRadian(0));
        self.view.bounds = CGRectMake(0.0, 0.0, 300.0, 480.0);	
    }
    else if (interfaceOrientation == UIInterfaceOrientationLandscapeLeft)
    {
        self.view = self.landscape;
        self.view.transform = CGAffineTransformIdentity;
        self.view.transform = CGAffineTransformMakeRotation(degreesToRadian(-90));
        self.view.bounds = CGRectMake(0.0, 0.0, 460.0, 320.0);
    }
    else if (interfaceOrientation == UIInterfaceOrientationPortraitUpsideDown)
    {
        self.view = self.portrait;
        self.view.transform = CGAffineTransformIdentity;
        self.view.transform = CGAffineTransformMakeRotation(degreesToRadian(180));
        self.view.bounds = CGRectMake(0.0, 0.0, 300.0, 480.0);
    }
    else if (interfaceOrientation == UIInterfaceOrientationLandscapeRight)
    {
        self.view = self.landscape;
        self.view.transform = CGAffineTransformIdentity;
        self.view.transform = 
        CGAffineTransformMakeRotation(degreesToRadian(90));
        self.view.bounds = CGRectMake(0.0, 0.0, 460.0, 320.0);
    }
}
 - (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
     return YES;
 }
 
- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	self.landscape = nil;
    self.portrait = nil;
    self.landscapeFooButton = nil;
    self.landscapeBarButton = nil;
    self.portraitFooButton = nil;
    self.portraitBarButton = nil;
    [super viewDidUnload];
}
- (void)dealloc {
	[landscape release];
	[portrait release];
	[landscapeFooButton release];	
	[portraitFooButton release];
	[landscapeBarButton release];
	[portraitBarButton release];
	
	[super dealloc];
}

@end
