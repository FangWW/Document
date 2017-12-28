//
//  PictureDetails.h
//  flickrLoader


#import <UIKit/UIKit.h>


@interface PictureDetails : UIViewController {
	NSString *photoID;
	IBOutlet UILabel *photoTitle;
	IBOutlet UITextView *description;
	IBOutlet UILabel *userName;
	IBOutlet UILabel *realName;
	IBOutlet UIImageView *imageView;
	
	NSOperationQueue *operationQueue;

	UIActivityIndicatorView *spinner;
    UILabel *loadingLabel;

}

@property (nonatomic, retain) NSString *photoID;
@property (nonatomic, retain) IBOutlet UILabel *photoTitle;
@property (nonatomic, retain) IBOutlet UITextView *description;
@property (nonatomic, retain) IBOutlet UILabel *userName;
@property (nonatomic, retain) IBOutlet UILabel *realName;
@property (nonatomic, retain) IBOutlet UIImageView *imageView;

@end
