//
//  ImageLoadingOperation.m
//  MyTableView


#import "ImageLoadingOperation.h"

NSString *const ImageResultKey = @"image";
NSString *const URLResultKey = @"url";

@implementation ImageLoadingOperation

- (id)initWithImageURL:(NSURL *)theImageURL target:(id)theTarget action:(SEL)theAction
{
    self = [super init];
    if (self) {
        imageURL = [theImageURL retain];
        target = theTarget;
        action = theAction;
    }
    return self;
}

- (void)dealloc
{
    [imageURL release];
    
    [super dealloc];
}

- (void)main
{
    // Synchronously oad the data from the specified URL.
    NSData *data = [[NSData alloc] initWithContentsOfURL:imageURL];
    UIImage *image = [[UIImage alloc] initWithData:data];
    
    // Package it up to send back to our target.
    NSDictionary *result = [NSDictionary dictionaryWithObjectsAndKeys:image, ImageResultKey, imageURL, URLResultKey, nil];
    [target performSelectorOnMainThread:action withObject:result waitUntilDone:NO];
    
    [data release];
    [image release];
}

@end
