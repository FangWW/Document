//
//  TouchValue.h
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright 2010 Apple. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface TouchValue : NSObject <NSCoding> {

	CGPoint	point;
}

@property (nonatomic) CGPoint point;

@end
