//
//  SocialBookWebService.m
//  SocialBook
//

#import "SocialBookWebService.h"
#import "WebPerson.h"


@implementation SocialBookWebService

+ (void)addPersonWithFirst:(NSString*)first last:(NSString*)last url:(NSString*)url toArray:(NSMutableArray*)array
{
    WebPerson *person = [[WebPerson alloc] init];
    person.firstName = first;
    person.lastName = last;
    person.urlString = url;
    [array addObject:person];
    [person release];
}

- (id)init
{
    if ((self = [super init])) {
        people = [[NSMutableArray alloc] init];
        
        [SocialBookWebService addPersonWithFirst:@"曹操" last:@"" url:@"http://www.xinlaoshi.com/caocao" toArray:people];
        [SocialBookWebService addPersonWithFirst:@"刘邦" last:@"" url:@"http://www.xinlaoshi.com/liubang" toArray:people];
        [SocialBookWebService addPersonWithFirst:@"Jun" last:@"Tang" url:@"http://www.xinlaoshi.com/tangjun" toArray:people];
        [SocialBookWebService addPersonWithFirst:@"朱元璋" last:@"" url:@"http://www.xinlaoshi.com/zhuyanzhang" toArray:people];
        [SocialBookWebService addPersonWithFirst:@"李世民" last:@"" url:@"http://www.xinlaoshi.com/lisimin" toArray:people];
    }
    
    return self;
}

- (void)dealloc
{
    [people release];
    
    [super dealloc];
}

- (NSArray*)webPeople
{
    return [[people copy] autorelease];;
}

@end
