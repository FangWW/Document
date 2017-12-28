//
//  NSString-Utilities.m
//  LearnObjectiveC
//
//  Created by Scott Stevenson on 4/14/08.
//  Source may be reused with virtually no restriction. See License.txt
//

#import "NSString-Utilities.h"
            
@implementation NSString (Utilities)

- (BOOL) isURL
{
    if ( self.length < 7 ) return NO;
    
    NSRange range = NSMakeRange(0,7);
    NSString* prefix = [self substringWithRange:range];

    if ( [prefix isEqualToString:@"http://"] )
        return YES;
    else
        return NO;
}

@end
