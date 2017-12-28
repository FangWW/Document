//
//  mapDemoViewController.h
//  mapDemo
//
//  Created by wkso on 7/30/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//
	
#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import <CoreLocation/CoreLocation.h>

@interface mapDemoViewController : UIViewController <MKMapViewDelegate, MKReverseGeocoderDelegate,
																		CLLocationManagerDelegate> {
	MKReverseGeocoder *geo;
	IBOutlet MKMapView *mv;
	CLLocationManager    *locmgr;
}

@property (nonatomic, retain) IBOutlet MKMapView *mv;
@property (nonatomic, retain) CLLocationManager *locmgr;
-(IBAction)changeMapType:(id)segcontrol;
-(IBAction)addPin;
@end

