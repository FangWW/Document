//
//  BeijingController.m
//
//  Created by svp on 7/10/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "BeijingController.h"

@implementation BeijingController

-(IBAction) changeNumber:(id)sender{
	int sliderValue = slider.value;
	number.text = [NSString stringWithFormat:@"%d",sliderValue];
	
}
@end
