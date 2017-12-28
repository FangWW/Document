//
//  DisclosureDetailController.m
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import "DisclosureDetailController.h"

@implementation DisclosureDetailController
@synthesize label;
@synthesize message;
- (void)viewWillAppear:(BOOL)animated {
    label.text = message;
    [super viewWillAppear:animated];
}
- (void)viewDidUnload {
    self.label = nil;
    self.message = nil;
}
- (void)dealloc {
    [label release];
    [message release];
    [super dealloc];
}
@end
