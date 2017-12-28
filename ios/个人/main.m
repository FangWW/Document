#import<Foundation/Foundation.h>

@interface Contact:NSObject{
	NSString *name;
	NSString *tel;
	NSString *address;
}
-(NSString *)name;
-(NSString *)tel;
-(NSString *)address;
-(void)setName:(NSString *)nameParam;
-(void)setTel:(NSString *)telParam;
-(void)setAddress:(NSString *)addressParam;
-init:(NSString *)nameParam:(NSString *)telParam:(NSString *)addressParam;
@end
@implementation Contact
-(NSString *)name{
	return name;
}
-(NSString *)tel{
	return tel;
}
-(NSString *)address{
	return address;
}
-(void)setName:(NSString *)nameParam{
	name=nameParam;
}
-(void)setTel:(NSString *)telParam{
	tel=telParam;
}
-(void)setAddress:(NSString *)addressParam{
	address=addressParam;
}
-init:(NSString *)nameParam:(NSString *)telParam:(NSString *)addressParam{
	if((self=[super init])){
	name=nameParam;
	tel=telParam;
	address=addressParam;
	}
	return self;
}
@end

@interface ContactService:NSObject
+(void)init;
+(void)add:(Contact *)contact;
+(int)find:(NSString *)name;
+(void)del:(NSString *)name;
+(void)showAll;
@end

static NSMutableDictionary *contacts;
@implementation ContactService
+(void)init{
	contacts=[NSMutableDictionary dictionary];
}
+(void)add:(Contact *)contact{
	[contacts setObject:contact forKey:[contact name]];
}
+(int)find:(NSString *)name{
	int i=0;
	for(;i<[contacts count];i++){
		if([[[contacts objectForKey:[[contacts allKeys] objectAtIndex:i]]name]isEqualToString:name])
			return i;
	}
	return -1;
}
+(void)del:(NSString *)name{
	int index=[ContactService find:name];
	if(index>=0)
		[contacts removeObjectForKey:[[contacts allKeys] objectAtIndex:index]];
}
+(void)showAll{
	NSLog(@"****************");
	NSLog(@"姓名\t电话\t住址");
	int i=0;
	for(;i<[contacts count];i++){
		Contact *item=[contacts objectForKey:[[contacts allKeys] objectAtIndex:i]];
		NSLog(@"%@\t%@\t%@",[item name],[item tel],[item address]);
	}
	NSLog(@"****************");
}
@end

int main(int argc,const char * argv[]){
NSAutoreleasePool *pool=[[NSAutoreleasePool alloc]init];
Contact *c1=[[Contact alloc]init:@"张三":@"088-222222":@"武汉"];
Contact *c2=[[Contact alloc]init:@"李四":@"088-222222":@"南京"];
Contact *c3=[[Contact alloc]init:@"王五":@"088-222222":@"天津"];
[ContactService init];
[ContactService add:c1];
[ContactService add:c2];
[ContactService add:c3];
[ContactService showAll];
[ContactService del:[c1 name]];
[ContactService showAll];
[pool drain];
return 0;

}



