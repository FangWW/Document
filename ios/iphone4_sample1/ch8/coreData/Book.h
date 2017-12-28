//
//  Book.h
//  coreData
//
//  Created by wkso on 8/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <CoreData/CoreData.h>


@interface Book :  NSManagedObject  
{
}

@property (nonatomic, retain) NSString * Title;
@property (nonatomic, retain) NSSet* writtenBy;

@end


@interface Book (CoreDataGeneratedAccessors)
- (void)addWrittenByObject:(NSManagedObject *)value;
- (void)removeWrittenByObject:(NSManagedObject *)value;
- (void)addWrittenBy:(NSSet *)value;
- (void)removeWrittenBy:(NSSet *)value;

@end

