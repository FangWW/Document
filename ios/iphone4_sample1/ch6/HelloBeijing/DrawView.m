//
//  DrawView.m
//
//  Created by svp on 7/27/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "DrawView.h"

@implementation DrawView
static int height = 0;

-(void) drawRect:(CGRect)rect{
	
	height=height+10;
	//CGRect bounds = [self bounds];
	//[[UIColor greenColor] set];
	//UIRectFill(bounds);
	//CGRect square = CGRectMake(50, 50, 100, height);
	//[[UIColor yellowColor] set];
	//UIRectFill(square);
	//[[UIColor blackColor] set];
	//UIRectFrame(square);
	
	CGContextRef context = UIGraphicsGetCurrentContext();
	[[UIColor greenColor] set];
	UIRectFill([self bounds]);
	CGContextBeginPath(context);
	CGContextMoveToPoint(context, 50+height, 10);
	CGContextAddLineToPoint(context, 10+height, 150);
	CGContextAddLineToPoint(context, 100+height, 150);	
	CGContextClosePath(context);
	[[UIColor yellowColor] setFill];
	[[UIColor blackColor] setStroke];
	CGContextDrawPath(context, kCGPathFillStroke);			
				
}

@end
