//
//  PersonTests.m
//  SocialBook


#import "PersonTests.h"
#import "WebPerson.h"

@implementation PersonTests

-(void) testCreatePerson
{
	WebPerson *person = [[WebPerson alloc] init];
	STAssertNotNil(person,nil);
	
}

-(void) testSetFirstName
{
	NSString *firstName = @"zhenghong";
	WebPerson *person = [[WebPerson alloc] init];
	person.firstName = firstName;
	STAssertEqualObjects(firstName,person.firstName,nil);
	
}

@end
