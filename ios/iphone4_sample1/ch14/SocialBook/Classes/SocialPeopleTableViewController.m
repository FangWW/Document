#import "SocialPeopleTableViewController.h"

#import "SocialBookWebService.h"
#import "WebPerson.h"

#import <AddressBookUI/AddressBookUI.h>

@implementation SocialPeopleTableViewController

- (id)initWithStyle:(UITableViewStyle)style {
	if (self = [super initWithStyle:style]) {
        addressBook = ABAddressBookCreate();
        webService = [[SocialBookWebService alloc] init];
        
        self.title = @"新老师－网上教育平台";
	}
	return self;
}

- (void)dealloc {
    [webService release];
    [people release];
    CFRelease(addressBook);

	[super dealloc];
}

- (NSArray*)people
{
    if (people == nil) {
        people = [[NSMutableArray alloc] init];
        
        NSArray *webPeople = [webService webPeople];
        for (WebPerson *webPerson in webPeople) {
            NSString *fullName = [NSString stringWithFormat:@"%@ %@", [webPerson firstName], [webPerson lastName]];
            CFArrayRef matches = ABAddressBookCopyPeopleWithName(addressBook, (CFStringRef)fullName);
            ABRecordRef person = NULL;
            
            if (matches && CFArrayGetCount(matches)) {
                person = (id)CFArrayGetValueAtIndex(matches, 0);
                ABMultiValueRef urls = ABRecordCopyValue(person, kABPersonURLProperty);
                ABMutableMultiValueRef mutableURLs = NULL;
                if (urls) {
                    mutableURLs = ABMultiValueCreateMutableCopy(urls);
                    CFRelease(urls);
                } else {
                    mutableURLs = ABMultiValueCreateMutable(kABStringPropertyType);
                }
                ABMultiValueAddValueAndLabel(mutableURLs, [webPerson urlString], CFSTR("新老师"), NULL);
                ABRecordSetValue(person, kABPersonURLProperty, mutableURLs, NULL);
                CFRelease(mutableURLs);
            } else {
                person = ABPersonCreate();
                ABRecordSetValue(person, kABPersonFirstNameProperty, [webPerson firstName], NULL);
                ABRecordSetValue(person, kABPersonLastNameProperty, [webPerson lastName], NULL);
                
                ABMutableMultiValueRef urls = ABMultiValueCreateMutable(kABMultiStringPropertyType);
                ABMultiValueAddValueAndLabel(urls, [webPerson urlString], CFSTR("新老师"), NULL);
                ABRecordSetValue(person, kABPersonURLProperty, urls, NULL);
                CFRelease(urls);
                [(id)person autorelease];
            }
            if (person) {
                [people addObject:(id)person];
            }
            
            if (matches) CFRelease(matches);
        }
        [people sortUsingFunction:(NSInteger (*)(id, id, void *))ABPersonComparePeopleByName context:(void*)ABPersonGetSortOrdering()];
    }
    
    return people;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [[self people] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	static NSString *MyIdentifier = @"MyIdentifier";
	
	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:MyIdentifier];
	if (cell == nil) {
		cell = [[[UITableViewCell alloc] initWithFrame:CGRectZero reuseIdentifier:MyIdentifier] autorelease];
	}
	// Configure the cell
    ABRecordRef person = (ABRecordRef)[[self people] objectAtIndex:[indexPath row]];
    NSString *name = (NSString*)ABRecordCopyCompositeName(person);
    cell.textLabel.text = name;
    [name release];
    
	return cell;
}

- (UITableViewCellAccessoryType)tableView:(UITableView *)tableView accessoryTypeForRowWithIndexPath:(NSIndexPath *)indexPath
{
    return UITableViewCellAccessoryDetailDisclosureButton;
}

- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    ABRecordRef person = (ABRecordRef)[[self people] objectAtIndex:[indexPath row]];
    if (ABRecordGetRecordID(person) != kABRecordInvalidID) {
        ABPersonViewController *personViewController = [[ABPersonViewController alloc] init];
        personViewController.displayedPerson = person;
        personViewController.allowsEditing = YES;
        [self.navigationController pushViewController:personViewController animated:YES];
        [personViewController release];
    } else {
        ABUnknownPersonViewController *unknownPersonViewController = [[ABUnknownPersonViewController alloc] init];
        unknownPersonViewController.displayedPerson = person;
        unknownPersonViewController.allowsAddingToAddressBook = YES;
        [self.navigationController pushViewController:unknownPersonViewController animated:YES];
        [unknownPersonViewController release];
    }
}

@end

