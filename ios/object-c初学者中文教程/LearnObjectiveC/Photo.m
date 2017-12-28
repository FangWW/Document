//
//  Photo.m
//  LearnObjectiveC
//
//  Created by Scott Stevenson on 4/14/08.
//  Source may be reused with virtually no restriction. See License.txt
//

#import "Photo.h"
        
@implementation Photo

@synthesize caption;
@synthesize photographer;

- (id) init
{
    if ( self = [super init] )
    {
        self.caption = @"Default Caption";
        self.photographer = @"Default Photographer";
    }
    return self;
}

- (void) dealloc
{
    self.caption = nil;
    self.photographer = nil;
    [super dealloc];
}

- (NSString*)description
{    
    return [NSString stringWithFormat:@"Photo caption: %@, photographer: %@", self.caption, self.photographer];
}
@end
