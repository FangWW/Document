//
//  BeijingController.h
//
//  Created by svp on 7/10/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

@interface BeijingController : NSObject {
	IBOutlet UISlider *slider;
	IBOutlet UILabel *number;
	IBOutlet UILabel *words;
	IBOutlet UIView *view;
}

-(IBAction) changeNumber : (id)sender;
-(IBAction) changeWords  : (id)sender;
-(IBAction) drawView     : (id)sender;



@end
