//
//  CommunalTouchBoard.h
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright 2010 Apple. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GameKit/GameKit.h>


@protocol CommunalTouchBoardDelegate;

@interface CommunalTouchBoard : UIViewController <GKSessionDelegate, GKPeerPickerControllerDelegate > {

	GKSession	*gameSession;
	IBOutlet UIView *touchView;
	
	NSString	*localUser;
	NSMutableDictionary	*touchDict;
}

- (IBAction)findPeers;

@property (nonatomic, retain) GKSession *gameSession;

@end
