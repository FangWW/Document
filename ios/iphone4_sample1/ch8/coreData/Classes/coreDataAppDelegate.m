//
//  coreDataAppDelegate.m
//  coreData
//
//  Created by wkso on 8/25/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "coreDataAppDelegate.h"
#import "coreDataViewController.h"
#import "Book.h"
#import "Author.h"

@implementation coreDataAppDelegate

@synthesize window;
@synthesize viewController;
@synthesize managedObjectContext;



-(NSManagedObjectContext*) managedObjectContext {

	if (managedObjectContext==nil) {
		NSArray *paths=NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,NSUserDomainMask,YES);
		NSString *basePath=([paths count]>0) ? [paths objectAtIndex:0] :nil;
		NSURL *url = [NSURL fileURLWithPath: [basePath stringByAppendingPathComponent:@"books.sqlite"]];
		NSError *err;
		NSPersistentStoreCoordinator * persistentStoreCoordinator = [[NSPersistentStoreCoordinator alloc] 
																	 initWithManagedObjectModel: 
																	 [NSManagedObjectModel mergedModelFromBundles:nil]];
		if (![persistentStoreCoordinator addPersistentStoreWithType:NSSQLiteStoreType configuration:nil 
																URL:url options:nil error:&err]) {
			NSLog(@"failed to add persistent store with type to persistent store coordinator");
		}
		managedObjectContext = [[NSManagedObjectContext alloc] init];
		[managedObjectContext setPersistentStoreCoordinator:persistentStoreCoordinator];
	}
	return managedObjectContext;	
}

-(void)demo{
	
	Book* b1= (Book*) [NSEntityDescription insertNewObjectForEntityForName:@"Book" 
													inManagedObjectContext:self.managedObjectContext];
	Book* b2= (Book*) [NSEntityDescription insertNewObjectForEntityForName:@"Book" 
													inManagedObjectContext:self.managedObjectContext];
	
	Author* a1 = (Author*) [NSEntityDescription insertNewObjectForEntityForName:@"Author" 
														 inManagedObjectContext:self.managedObjectContext];
	Author* a2 = (Author*) [NSEntityDescription insertNewObjectForEntityForName:@"Author" 
														 inManagedObjectContext:self.managedObjectContext];
	
	b1.Title=@"iPhone Development Book";
	a1.Name=@"SuWeiJi";
	b2.Title=@"Cloud Computing Concepts";
	a2.Name=@"Zhenghong Yang";
	[a2 addWroteObject:b1];
	[a2 addWroteObject:b2];
	[a1 addWroteObject:b1];
	
	NSArray* booksAuthor2Wrote=[a2.wrote allObjects];
	for(int i=0; i<[booksAuthor2Wrote count]; i++) {
		Book* tempBook = (Book*) [booksAuthor2Wrote objectAtIndex:i];
		NSLog(@"Book %@ wrote include: %@", a2.Name, tempBook.Title);
	}
	NSArray* authorOfBook1=[b1.writtenBy allObjects];
	for(int i=0; i<[authorOfBook1 count]; i++) {
		Author* tempAuthor = (Author*) [authorOfBook1 objectAtIndex:i];
		NSLog(@"The book %@ was written by: %@", b1.Title, tempAuthor.Name);
	}
	

	//[self.managedObjectContext save:NULL];
	
	/*
	
	/*NSSet *set = [self.managedObjectContext insertedObjects];
	NSArray *all = [set allObjects];
	for(int i=0; i<[all count];i++) {
		NSObject *temp = [all objectAtIndex:i];
		if ([temp isKindOfClass:[Book class]])
			NSLog(@"Fetching book %@",((Book*)temp).Title);
		else NSLog(@"Author %@",((Author*)temp).Name);
	}*/
}
							

	#pragma mark -
#pragma mark Application lifecycle

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
	[self managedObjectContext];
	[self demo];
    return YES;
}


- (void)applicationWillResignActive:(UIApplication *)application {
    /*
     Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
     Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
     */
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    /*
     Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
     If your application supports background execution, called instead of applicationWillTerminate: when the user quits.
     */
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    /*
     Called as part of  transition from the background to the inactive state: here you can undo many of the changes made on entering the background.
     */
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    /*
     Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
     */
}


- (void)applicationWillTerminate:(UIApplication *)application {
    /*
     Called when the application is about to terminate.
     See also applicationDidEnterBackground:.
     */
}


#pragma mark -
#pragma mark Memory management

- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application {
    /*
     Free up as much memory as possible by purging cached data objects that can be recreated (or reloaded from disk) later.
     */
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
