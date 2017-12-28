//
//  TapTapsViewController.h
//  TapTaps
//
//  Created by jeff on 4/28/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TapTapsViewController : UIViewController {
      UILabel *singleLabel;
      UILabel *doubleLabel;
      UILabel *tripleLabel;
      UILabel *quadrupleLabel;    
}
@property (nonatomic, retain) IBOutlet UILabel *singleLabel;
@property (nonatomic, retain) IBOutlet UILabel *doubleLabel;
@property (nonatomic, retain) IBOutlet UILabel *tripleLabel;
@property (nonatomic, retain) IBOutlet UILabel *quadrupleLabel;
- (void)singleTap;
- (void)doubleTap;
- (void)tripleTap;
- (void)quadrupleTap;
- (void)eraseMe:(UITextField *)textField;
@end

