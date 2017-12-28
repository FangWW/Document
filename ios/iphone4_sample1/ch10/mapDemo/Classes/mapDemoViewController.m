//
//  mapDemoViewController.m
//  mapDemo
//
//  Created by wkso on 7/30/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "mapDemoViewController.h"
#import <MapKit/MapKit.h>

@implementation mapDemoViewController

@synthesize mv;
@synthesize locmgr;
/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	self.locmgr = [[CLLocationManager alloc] init];
	locmgr.delegate = self;
	locmgr.desiredAccuracy = kCLLocationAccuracyBest;
	[locmgr startUpdatingLocation];
	
}


- (void)locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation {
    NSLog([[NSString alloc] initWithFormat:@"%f°", newLocation.coordinate.latitude]);
    NSLog([[NSString alloc] initWithFormat:@"%f°", newLocation.coordinate.longitude]);
    [locmgr stopUpdatingLocation];
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
	NSLog([@"LocationManager didFailWithError" stringByAppendingString:[error localizedDescription]]);
}



/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDSidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[mv release];
    [super dealloc];
}


-(IBAction)changeMapType:(id)segcontrol {
	UISegmentedControl *ctrl = (UISegmentedControl*) segcontrol;
	NSInteger temp = ctrl.selectedSegmentIndex;
	mv.mapType=temp;
}



-(IBAction)addPin{
	CLLocationCoordinate2D c = {39.908605,116.398019};
	geo=[[MKReverseGeocoder alloc] initWithCoordinate:c];
	geo.delegate=self;
	[geo start];
}

-(void) reverseGeocoder:(MKReverseGeocoder*)geocoder didFailwithError:(NSError*)error{
	NSLog(@"reverseGeoCoder error");
}

-(void)reverseGeocoder:(MKReverseGeocoder*)geocoder didFindPlacemark:(MKPlacemark*)placemark{
	MKPlacemark *mysteryspot = [[MKPlacemark alloc] initWithCoordinate:placemark.coordinate addressDictionary:placemark.addressDictionary];
	[mv addAnnotation:mysteryspot];
	[mysteryspot release];
	[mv setCenterCoordinate:placemark.coordinate animated:YES];
}

- (void)reverseGeocoder:(MKReverseGeocoder *)geocoder didFailWithError:(NSError *)error{
	NSLog(@"reverseGeocoder didFailWithError");
}


@end











