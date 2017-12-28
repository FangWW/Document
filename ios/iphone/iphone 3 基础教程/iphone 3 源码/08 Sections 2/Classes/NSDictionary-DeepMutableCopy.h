//
//  NSDictionary-DeepMutableCopy.h
//  Sections
//
//  Created by jeff on 4/21/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NSDictionary(DeepMutableCopy)
-(NSMutableDictionary *)mutableDeepCopy;
@end
