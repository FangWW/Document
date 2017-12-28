//
//  Photo.h
//  LearnObjectiveC
//
//  Created by Scott Stevenson on 4/14/08.
//  Source may be reused with virtually no restriction. See License.txt
//

#import <Cocoa/Cocoa.h>

@interface Photo : NSObject
{    
    NSString* caption;
    NSString* photographer;
}

@property (retain) NSString* caption;
@property (retain) NSString* photographer;

@end
