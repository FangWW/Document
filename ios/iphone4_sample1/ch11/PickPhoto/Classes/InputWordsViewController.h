//
//  InputWordsViewController.h
//  PickPhoto
//
//  Created by svp on 8/14/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol InputWordsViewControllerDelegate;



@interface InputWordsViewController : UIViewController {

	UITextField *textField;	
	id<InputWordsViewControllerDelegate> delegate;	
}

@property (retain) IBOutlet UITextField *textField;
@property (assign) id<InputWordsViewControllerDelegate> delegate;

- (IBAction)doneInput:(id)sender;

@end

@protocol InputWordsViewControllerDelegate <NSObject>

@optional
- (void)inputWordsViewController:(InputWordsViewController *)controller didInputWords:(NSString *)text;

@end

