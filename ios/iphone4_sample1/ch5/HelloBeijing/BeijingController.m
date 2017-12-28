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
	NSLog(@"调用了改变数字的方法");
	
}

-(IBAction) changeWords : (id) sender{
		words.text = @"世博欢迎您";
}

@end
