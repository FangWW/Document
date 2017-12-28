//
//  DatePickerViewController.h
//  Pickers
//
//  Created by Jeff LaMarche on 7/7/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DatePickerViewController : UIViewController {
	IBOutlet	UIDatePicker	*datePicker;
}
@property (nonatomic, retain) UIDatePicker *datePicker;
-(IBAction)buttonPressed:(id)sender;
@end
