//
//  CityDetailViewController.h
//  lvyou
//
//  Created by svp on 7/28/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface CityDetailViewController : UIViewController {
	
	IBOutlet UILabel *cityName;
	NSString *city;
}

@property (copy) NSString *city;

@end
