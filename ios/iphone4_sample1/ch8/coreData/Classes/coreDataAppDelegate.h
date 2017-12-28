//
//  coreDataAppDelegate.h
//  coreData
//
//  Created by wkso on 8/25/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <CoreData/CoreData.h>

@class coreDataViewController;

@interface coreDataAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    coreDataViewController *viewController;
	NSManagedObjectContext* managedObjectContenxt;
	
}
@property (nonatomic, retain) NSManagedObjectContext* managedObjectContext;

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet coreDataViewController *viewController;

@end

