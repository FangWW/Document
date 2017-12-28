//
//  TouchValue.m
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright 2010 Apple. All rights reserved.
//

#import "TouchValue.h"


@implementation TouchValue

@synthesize point;

- (id)initWithCoder:(NSCoder *)decoder
{
	if (self = [super init])
	{
		CGFloat	x = [decoder decodeFloatForKey:@"x"];
		CGFloat y = [decoder decodeFloatForKey:@"y"];
		self.point = CGPointMake(x, y);
	}
	
	return self;
}

- (void)encodeWithCoder:(NSCoder *)encoder
{
	[encoder encodeFloat:self.point.x forKey:@"x"];
	[encoder encodeFloat:self.point.y forKey:@"y"];
}

@end
