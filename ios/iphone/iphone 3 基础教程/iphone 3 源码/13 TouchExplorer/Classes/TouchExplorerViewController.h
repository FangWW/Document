//
//  TouchExplorerViewController.h
//  TouchExplorer
//
//  Created by jeff on 4/27/09.
//  Copyright Jeff LaMarche 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TouchExplorerViewController : UIViewController {
    UILabel    *messageLabel;
    UILabel    *tapsLabel;
    UILabel    *touchesLabel;    
}
@property (nonatomic, retain) IBOutlet UILabel *messageLabel;
@property (nonatomic, retain) IBOutlet UILabel *tapsLabel;
@property (nonatomic, retain) IBOutlet UILabel *touchesLabel;
- (void)updateLabelsFromTouches:(NSSet *)touches;

@end

