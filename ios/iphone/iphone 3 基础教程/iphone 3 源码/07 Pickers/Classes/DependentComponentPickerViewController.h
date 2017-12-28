//
//  DependentComponentPickerViewController.h
//  Pickers
//
//  Created by Jeff LaMarche on 7/7/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

#define kStateComponent 0
#define kZipComponent 1

@interface DependentComponentPickerViewController : UIViewController  <UIPickerViewDelegate, UIPickerViewDataSource> {
	IBOutlet	UIPickerView *picker;
	
	NSDictionary *stateZips;
	NSArray	*states;
	NSArray *zips;

}
@property (retain, nonatomic) UIPickerView *picker;
@property (retain, nonatomic) NSDictionary *stateZips;
@property (retain, nonatomic) NSArray *states;
@property (retain, nonatomic) NSArray *zips;
- (IBAction)butonPressed:(id)sender;
@end
