//
//  SelectPersonViewController.h
//  SelectPerson
//
//  Created by svp on 8/14/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AddressBookUI/AddressBookUI.h>

@interface SelectPersonViewController : UIViewController <ABPeoplePickerNavigationControllerDelegate, UINavigationControllerDelegate>
{
    UITextField *personField;
}


@property (retain) IBOutlet UITextField *personField;

- (IBAction)selectPerson:(id)sender;


@end
