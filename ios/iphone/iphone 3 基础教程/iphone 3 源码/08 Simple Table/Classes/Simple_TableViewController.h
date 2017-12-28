//
//  Simple_TableViewController.h
//  Simple Table
//
//  Created by jeff on 4/17/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface Simple_TableViewController : UIViewController 
    <UITableViewDelegate, UITableViewDataSource>         
{
    NSArray *listData;
}
@property (nonatomic, retain) NSArray *listData;
@end

