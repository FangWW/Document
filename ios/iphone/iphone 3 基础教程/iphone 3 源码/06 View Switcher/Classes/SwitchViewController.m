//
//  SwitchViewController.m
//  View Switcher
//
//  Created by Jeff LaMarche on 7/6/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//

#import "SwitchViewController.h"
#import "BlueViewController.h"
#import "YellowViewController.h"

@implementation SwitchViewController
@synthesize blueViewController;
@synthesize yellowViewController;

- (void)viewDidLoad
{
	BlueViewController *blueController = [[BlueViewController alloc] initWithNibName:@"BlueView" bundle:nil];
	self.blueViewController = blueController;
	[self.view insertSubview:blueController.view atIndex:0];
	[blueController release];
}
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
	if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
		// Initialization code
	}
	return self;
}

- (IBAction)switchViews:(id)sender
{
    [UIView beginAnimations:@"View Flip" context:nil];
    [UIView setAnimationDuration:1.25];
    [UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
    
    if (self.yellowViewController.view.superview == nil)
    {
        if (self.yellowViewController == nil)
        {
            YellowViewController *yellowController = 
            [[YellowViewController alloc] initWithNibName:@"YellowView" 
                                                   bundle:nil];
            self.yellowViewController = yellowController;
            [yellowController release];
        }
        [UIView setAnimationTransition:
         UIViewAnimationTransitionFlipFromRight
                               forView:self.view cache:YES];
        
        [blueViewController viewWillAppear:YES];
        [yellowViewController viewWillDisappear:YES];
        [blueViewController.view removeFromSuperview];
        [self.view insertSubview:yellowViewController.view atIndex:0];
        [yellowViewController viewDidDisappear:YES];
        [blueViewController viewDidAppear:YES];
    }
    else
    {
        if (self.blueViewController == nil)
        {
            BlueViewController *blueController = 
            [[BlueViewController alloc] initWithNibName:@"BlueView" 
                                                 bundle:nil];
            self.blueViewController = blueController;
            [blueController release];
        }
        [UIView setAnimationTransition:
         UIViewAnimationTransitionFlipFromLeft
                               forView:self.view cache:YES];
        
        [yellowViewController viewWillAppear:YES];
        [blueViewController viewWillDisappear:YES];
        [yellowViewController.view removeFromSuperview];
        [self.view insertSubview:blueViewController.view atIndex:0];
        [blueViewController viewDidDisappear:YES];
        [yellowViewController viewDidAppear:YES];
    }
    [UIView commitAnimations];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
	// Return YES for supported orientations
	return (interfaceOrientation == UIInterfaceOrientationPortrait);
}


- (void)didReceiveMemoryWarning {
	[super didReceiveMemoryWarning]; // Releases the view if it doesn't have a superview
	// Release anything that's not essential, such as cached data
    if (self.blueViewController.view.superview == nil) 
        self.blueViewController = nil;
    else
        self.yellowViewController = nil;
}


- (void)dealloc {
	[yellowViewController release];
	[blueViewController release];
	[super dealloc];
}

@end
