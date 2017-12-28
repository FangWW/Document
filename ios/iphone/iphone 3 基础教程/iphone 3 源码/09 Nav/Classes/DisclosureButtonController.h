//
//  DisclosureButtonController.h
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SecondLevelViewController.h"

@class DisclosureDetailController;

@interface DisclosureButtonController : SecondLevelViewController {
    NSArray *list;
    DisclosureDetailController *childController;
}
@property (nonatomic, retain) NSArray *list;
@end
