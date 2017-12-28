//
//  CellsViewController.h
//  Cells
//
//  Created by jeff on 4/17/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#define kNameValueTag     1
#define kColorValueTag    2

@interface CellsViewController : UIViewController 
    <UITableViewDataSource, UITableViewDelegate>
{
    NSArray    *computers;
}
@property (nonatomic, retain) NSArray *computers;
@end

