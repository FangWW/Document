//
//  MapTestViewController.m
//  MapTest
//
//  Created by svp on 8/13/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "MapTestViewController.h"
#import <MapKit/MapKit.h>

@implementation MapTestViewController



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


/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/


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

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


-(IBAction)changeMapType:(id)segcontrol {
	UISegmentedControl *ctrl = (UISegmentedControl*) segcontrol;
	NSInteger temp = ctrl.selectedSegmentIndex;
	mv.mapType=temp;
}

- (IBAction)addPin
{
	
	CLLocationCoordinate2D coordinate1 = {
		31.240948,121.485958
	};
	NSDictionary *address = [NSDictionary dictionaryWithObjectsAndKeys:@"中国", @"Country",@"上海",@"Locality", nil];
	MKPlacemark *shanghai = [[MKPlacemark alloc] initWithCoordinate:coordinate1 addressDictionary:address];
	[mv addAnnotation:shanghai];
	[shanghai release];
	
	CLLocationCoordinate2D c = {39.908605,116.398019};
	address = [NSDictionary dictionaryWithObjectsAndKeys:@"中国", @"Country",@"北京",@"Locality", nil];
	MKPlacemark *mysteryspot = [[MKPlacemark alloc] initWithCoordinate:c addressDictionary:address];
	[mv addAnnotation:mysteryspot];
	[mysteryspot release];

}

-(IBAction)reverseGeoTest{
	CLLocationCoordinate2D c = {39.908605,116.398019};
	geo=[[MKReverseGeocoder alloc] initWithCoordinate:c];	
	geo.delegate=self;
	[geo start];
}

- (IBAction)currentLocation
{
	mv.showsUserLocation = YES;
	MKUserLocation	*userLocation = mv.userLocation;
	CLLocationCoordinate2D	coordinate = userLocation.location.coordinate;	
	//NSLog(@"called here");
	//NSLog([[NSString alloc] initWithFormat:@"%f",coordinate.latitude]);
	if (!geo)
	{
		geo = [[MKReverseGeocoder alloc] initWithCoordinate:coordinate];
		geo.delegate = self;
		[geo start];
	}
}

-(void) reverseGeocoder:(MKReverseGeocoder*)geocoder didFailwithError:(NSError*)error{
	NSLog(@"reverseGeoCoder error");
}



-(void)reverseGeocoder:(MKReverseGeocoder*)geocoder didFindPlacemark:(MKPlacemark*)placemark{
	
	//mv.userLocation.title = placemark.title;
	
	
	MKPlacemark *mysteryspot = [[MKPlacemark alloc] initWithCoordinate:placemark.coordinate addressDictionary:placemark.addressDictionary];
	[mv addAnnotation:mysteryspot];
	[mysteryspot release];
	[mv setCenterCoordinate:placemark.coordinate animated:YES];
	 
	 
}

- (void)dealloc {
	[mv release];
    [super dealloc];
}

@end
