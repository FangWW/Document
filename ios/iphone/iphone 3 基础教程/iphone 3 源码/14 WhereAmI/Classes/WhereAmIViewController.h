#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>

@interface WhereAmIViewController :
UIViewController <CLLocationManagerDelegate> {
    CLLocationManager    *locationManager;
    
    CLLocation           *startingPoint;
    
    UILabel *latitudeLabel;
    UILabel *longitudeLabel;
    UILabel *horizontalAccuracyLabel;
    UILabel *altitudeLabel;
    UILabel *verticalAccuracyLabel;
    UILabel *distanceTraveledLabel;
}
@property (retain, nonatomic) CLLocationManager *locationManager;
@property (retain, nonatomic) CLLocation *startingPoint;
@property (retain, nonatomic) IBOutlet UILabel *latitudeLabel;
@property (retain, nonatomic) IBOutlet UILabel *longitudeLabel;
@property (retain, nonatomic) IBOutlet UILabel *horizontalAccuracyLabel;
@property (retain, nonatomic) IBOutlet UILabel *altitudeLabel;
@property (retain, nonatomic) IBOutlet UILabel *verticalAccuracyLabel;
@property (retain, nonatomic) IBOutlet UILabel *distanceTraveledLabel;
@end
