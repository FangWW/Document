//
//  Author.h
//  coreData
//
//  Created by wkso on 8/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <CoreData/CoreData.h>

@class Book;

@interface Author :  NSManagedObject  
{
}

@property (nonatomic, retain) NSString * Name;
@property (nonatomic, retain) NSSet* wrote;

@end


@interface Author (CoreDataGeneratedAccessors)
- (void)addWroteObject:(Book *)value;
- (void)removeWroteObject:(Book *)value;
- (void)addWrote:(NSSet *)value;
- (void)removeWrote:(NSSet *)value;

@end

