//
//  President.h
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>

#define kPresidentNumberKey         @"President"
#define kPresidentNameKey           @"Name"
#define kPresidentFromKey           @"FromYear"
#define kPresidentToKey             @"ToYear"
#define kPresidentPartyKey          @"Party"

@interface President : NSObject <NSCoding> {
    int         number;
    NSString    *name;
    NSString    *fromYear;
    NSString    *toYear;
    NSString    *party;    
}
@property int number;
@property (nonatomic, retain) NSString *name;
@property (nonatomic, retain) NSString *fromYear;
@property (nonatomic, retain) NSString *toYear;
@property (nonatomic, retain) NSString *party;
@end
