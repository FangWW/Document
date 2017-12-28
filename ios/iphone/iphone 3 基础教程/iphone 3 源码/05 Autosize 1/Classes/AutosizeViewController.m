//
//  AutosizeViewController.m
//  Autosize
//
//  Created by jeff on 4/1/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import "AutosizeViewController.h"

@implementation AutosizeViewController


- (BOOL)shouldAutorotateToInterfaceOrientation:
(UIInterfaceOrientation)interfaceOrientation {
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}
- (void)viewDidUnload {
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

@end
