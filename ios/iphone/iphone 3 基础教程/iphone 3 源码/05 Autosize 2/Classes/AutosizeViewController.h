//
//  AutosizeViewController.h
//  Autosize
//
//  Created by jeff on 4/1/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AutosizeViewController : UIViewController {
    UIButton *button1;
    UIButton *button2;
    UIButton *button3;
    UIButton *button4;
    UIButton *button5;
    UIButton *button6;
}
@property (nonatomic, retain) IBOutlet UIView *button1;
@property (nonatomic, retain) IBOutlet UIView *button2;
@property (nonatomic, retain) IBOutlet UIView *button3;
@property (nonatomic, retain) IBOutlet UIView *button4;
@property (nonatomic, retain) IBOutlet UIView *button5;
@property (nonatomic, retain) IBOutlet UIView *button6;
@end

