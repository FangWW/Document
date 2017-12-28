//
//  CheckListController.h
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SecondLevelViewController.h"

@interface CheckListController : SecondLevelViewController {
    NSArray *list;
    NSIndexPath    * lastIndexPath;
}
@property (nonatomic, retain) NSIndexPath * lastIndexPath;
@property (nonatomic, retain) NSArray *list;
@end
