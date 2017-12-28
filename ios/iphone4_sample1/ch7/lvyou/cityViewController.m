//
//  cityViewController.m
//  lvyou
//
//  Created by svp on 7/28/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "cityViewController.h"
#import "CityDetailViewController.h"

@implementation cityViewController

-(IBAction) selectCity : (id) sender{
	CityDetailViewController *cityDetailContrl 
			= [[CityDetailViewController alloc] init];
	cityDetailContrl.title = @ "北京欢迎您";
	cityDetailContrl.city = @"北京";
	[self.navigationController pushViewController:cityDetailContrl animated:YES];
	[cityDetailContrl release];
}

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
	UIBarButtonItem *discountButton = [[UIBarButtonItem alloc]
					initWithTitle:@"折扣信息" style:UIBarButtonItemStyleBordered 
								target:self action:@selector(discount:)];
	self.navigationItem.leftBarButtonItem = discountButton;
	[discountButton release];
	
	UITabBarItem *item = [[UITabBarItem alloc]
						  initWithTitle:@"旅游指南"
						  //initWithTabBarSystemItem:UITabBarSystemItemBookmarks
						  image:[UIImage imageNamed:@"GoldenGateBridge.png"]
						  tag:0];
	self.tabBarItem = item;
	[item release];
    
	[super viewDidLoad];
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

- (void)viewDidUnload {
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}


@end
