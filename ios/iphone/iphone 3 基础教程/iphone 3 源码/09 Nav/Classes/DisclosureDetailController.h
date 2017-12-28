//
//  DisclosureDetailController.h
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DisclosureDetailController : UIViewController {
    UILabel    *label;
    NSString    *message;

}
@property (nonatomic, retain) IBOutlet UILabel *label;
@property (nonatomic, retain) NSString *message;
@end
