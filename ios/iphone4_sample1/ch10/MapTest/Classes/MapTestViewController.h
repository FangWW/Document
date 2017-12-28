//
//  MapTestViewController.h
//  MapTest
//
//  Created by svp on 8/13/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

@interface MapTestViewController : UIViewController <MKReverseGeocoderDelegate>{

	MKReverseGeocoder *geo;
	IBOutlet MKMapView *mv;
}



-(IBAction)changeMapType:(id)segcontrol;
-(IBAction)addPin;
-(IBAction)reverseGeoTest;
- (IBAction)currentLocation;

@end

