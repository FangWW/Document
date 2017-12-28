//
//  SocialBookWebService.h
//  SocialBook


#import <UIKit/UIKit.h>


@interface SocialBookWebService : NSObject {
    NSMutableArray *people;
}

// returns an array of WebPerson instances
- (NSArray*)webPeople;

@end
