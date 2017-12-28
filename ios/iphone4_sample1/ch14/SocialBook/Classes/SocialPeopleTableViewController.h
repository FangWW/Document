#import <UIKit/UIKit.h>
#import <AddressBook/AddressBook.h>

@class SocialBookWebService;

@interface SocialPeopleTableViewController : UITableViewController {
    ABAddressBookRef      addressBook;
    
    SocialBookWebService *webService;
    NSMutableArray       *people;
}

@end
