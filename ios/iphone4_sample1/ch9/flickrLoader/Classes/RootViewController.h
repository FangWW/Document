//
//  RootViewController.h
//  flickrLoader


#import <UIKit/UIKit.h>

@interface RootViewController : UITableViewController {
	NSMutableArray *photoNames;
    NSMutableArray *photoURLs;
	NSMutableArray *photoIDs;
	
    NSOperationQueue *operationQueue;
    
    NSMutableDictionary *cachedImages;
	
    UIActivityIndicatorView *spinner;
    UILabel *loadingLabel;
}

@end
