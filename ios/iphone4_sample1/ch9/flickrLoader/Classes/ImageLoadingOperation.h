
//  ImageLoadingOperation.h


#import <UIKit/UIKit.h>

extern NSString *const ImageResultKey;
extern NSString *const URLResultKey;

@interface ImageLoadingOperation : NSOperation {
    NSURL *imageURL;
    id target;
    SEL action;
}

- (id)initWithImageURL:(NSURL *)imageURL target:(id)target action:(SEL)action;

@end
