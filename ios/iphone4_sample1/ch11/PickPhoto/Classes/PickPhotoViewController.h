//
//  PickPhotoViewController.h
//  PickPhoto
//
//  Created by svp on 8/14/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "InputWordsViewController.h"


@interface PickPhotoViewController : UIViewController  <UIImagePickerControllerDelegate, UINavigationControllerDelegate, InputWordsViewControllerDelegate>
{
    UIImageView *imageView;
    UILabel *wordsLabel;
}

@property (retain) IBOutlet UIImageView *imageView;
@property (retain) IBOutlet UILabel *wordsLabel;

- (IBAction)showImagePicker:(id)sender;
- (IBAction)inputWords:(id)sender;

@end

