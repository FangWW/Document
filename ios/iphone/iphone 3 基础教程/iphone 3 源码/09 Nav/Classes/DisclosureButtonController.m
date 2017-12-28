//
//  DisclosureButtonController.m
//  Nav
//
//  Created by jeff on 4/22/09.
//  Copyright 2009 Jeff LaMarche. All rights reserved.
//

#import "DisclosureButtonController.h"
#import "NavAppDelegate.h"
#import "DisclosureDetailController.h"
@implementation DisclosureButtonController
@synthesize list;
- (void)viewDidLoad {
    NSArray *array = [[NSArray alloc] initWithObjects:@"Toy Story",
                      @"A Bug's Life", @"Toy Story 2", @"Monsters, Inc.", 
                      @"Finding Nemo", @"The Incredibles", @"Cars", 
                      @"Ratatouille", @"WALL-E", @"Up", @"Toy Story 3",
                      @"Cars 2", @"The Bear and the Bow", @"Newt", nil];
    self.list = array;
    [array release];
    [super viewDidLoad];
}
- (void)viewDidUnload {
    self.list = nil;
    [childController release];
    childController = nil;
}
- (void)dealloc {
    [list release];
    [childController release];
    [super dealloc];
}
#pragma mark -
#pragma mark Table Data Source Methods
- (NSInteger)tableView:(UITableView *)tableView 
 numberOfRowsInSection:(NSInteger)section {
    return [list count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView 
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString * DisclosureButtonCellIdentifier = 
    @"DisclosureButtonCellIdentifier";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: 
                             DisclosureButtonCellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault 
                                       reuseIdentifier: DisclosureButtonCellIdentifier]
                autorelease];
    }
    NSUInteger row = [indexPath row];
    NSString *rowString = [list objectAtIndex:row];
    cell.textLabel.text = rowString;
    cell.accessoryType = UITableViewCellAccessoryDetailDisclosureButton;
    [rowString release];
    return cell;
}

#pragma mark -
#pragma mark Table Delegate Methods


- (void)tableView:(UITableView *)tableView 
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:
                          @"Hey, do you see the disclosure button?" 
                                                    message:@"If you're trying to drill down, touch that instead"
                                                   delegate:nil 
                                          cancelButtonTitle:@"Won't happen again" 
                                          otherButtonTitles:nil];
    [alert show];
    [alert release];
    
}
- (void)tableView:(UITableView *)tableView 
accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    if (childController == nil)
        childController = [[DisclosureDetailController alloc] 
                           initWithNibName:@"DisclosureDetail" bundle:nil];
    
    childController.title = @"Disclosure Button Pressed";
    NSUInteger row = [indexPath row];
    
    NSString *selectedMovie = [list objectAtIndex:row];
    NSString *detailMessage  = [[NSString alloc] 
                                initWithFormat:@"You pressed the disclosure button for %@.", 
                                selectedMovie];
    childController.message = detailMessage;
    childController.title = selectedMovie;
    [detailMessage release];

    [self.navigationController pushViewController:childController
     animated:YES];
}
@end
