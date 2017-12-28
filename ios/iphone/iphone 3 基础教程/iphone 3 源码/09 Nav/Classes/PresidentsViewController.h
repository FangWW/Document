//
//  PresidentsViewController.h
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SecondLevelViewController.h"

@interface PresidentsViewController : SecondLevelViewController {
        NSMutableArray *list;
}
@property (nonatomic, retain) NSMutableArray *list;
@end
