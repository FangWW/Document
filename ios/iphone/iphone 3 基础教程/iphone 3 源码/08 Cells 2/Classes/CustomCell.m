//
//  CustomCell.m
//  Cells
//
//  Created by jeff on 4/17/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import "CustomCell.h"


@implementation CustomCell
@synthesize nameLabel;
@synthesize colorLabel;

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {

    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
- (void)dealloc {
    [nameLabel release];
    [colorLabel release];
    [super dealloc];
}


@end
