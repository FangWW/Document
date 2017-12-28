//
//  WebPerson.h
//  SocialBook


#import <UIKit/UIKit.h>


@interface WebPerson : NSObject {
    NSString *firstName;
    NSString *lastName;
    NSString *urlString;
}

@property (nonatomic, copy) NSString *firstName;
@property (nonatomic, copy) NSString *lastName;
@property (nonatomic, copy) NSString *urlString;

@end
