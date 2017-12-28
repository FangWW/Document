//
//  CustomPickerViewController.h
//  Pickers
//
//  Created by Jeff LaMarche on 7/7/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CustomPickerViewController : UIViewController <UIPickerViewDataSource, UIPickerViewDelegate> {
	IBOutlet	UIPickerView *picker;
	IBOutlet	UILabel *winLabel;
	IBOutlet	UIButton *button;
	
	NSArray *column1;
	NSArray *column2;
	NSArray *column3;
	NSArray *column4;
	NSArray *column5;
}
-(IBAction)spin:(id)sender;
@property(nonatomic, retain) UIPickerView *picker;
@property(nonatomic, retain) NSArray *column1;
@property(nonatomic, retain) NSArray *column2;
@property(nonatomic, retain) NSArray *column3;
@property(nonatomic, retain) NSArray *column4;
@property(nonatomic, retain) NSArray *column5;
@property(nonatomic, retain) UILabel *winLabel;
@property(nonatomic, retain) UIButton *button;
@end
