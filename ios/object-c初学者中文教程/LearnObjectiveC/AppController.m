//
//  AppController.m
//  LearnObjectiveC
//
//  Created by Scott Stevenson on 4/14/08.
//  Source may be reused with virtually no restriction. See License.txt
//

#import "AppController.h"
#import "Photo.h"
#import "NSString-Utilities.h"


@implementation AppController

- (IBAction)buttonClicked:(id)sender
{
    // create a photo and set properties
    Photo* myPhoto = [[Photo alloc] init];
    myPhoto.caption = @"Day at the Beach";
    myPhoto.photographer = @"My Name";
    NSLog(@"%@", myPhoto );
    [myPhoto release];
        
    // use our -isURL category
    NSString* string1 = @"http://pixar.com/";
    NSString* string2 = @"Pixar";

    if ( [string1 isURL] )
        NSLog (@"string1 is a URL");

    if ( [string2 isURL] )
        NSLog (@"string2 is a URL");
}

@end
