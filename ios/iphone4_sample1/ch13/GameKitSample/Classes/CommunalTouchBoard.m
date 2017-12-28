//
//  CommunalTouchBoard.m
//  GameKitSample
//
//  Created by Alan Cannistraro on 3/2/10.
//  Copyright 2010 Apple. All rights reserved.
//

#import "CommunalTouchBoard.h"

#import "TouchValue.h"


@implementation CommunalTouchBoard

@synthesize gameSession;


- (void)viewWillAppear:(BOOL)animated
{
	touchDict = [[NSMutableDictionary alloc] init];
	
	localUser = [[UIDevice currentDevice].name retain];
}

- (void)viewWillDisappear:(BOOL)animated
{
	[touchDict release];
	touchDict = nil;
}

- (void)updateTouchViews
{
	for (UIView *subview in touchView.subviews)
	{
		[subview removeFromSuperview];
	}
	
	for (NSArray *touchesForUser in [touchDict allValues])
	{
		for (TouchValue *touchValue in touchesForUser)
		{
			UIView	*view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 50, 50)];
			view.backgroundColor = [UIColor redColor];
			view.center = touchValue.point;
			[touchView addSubview:view];
		}
	}
}


#pragma mark
#pragma mark Touch Tracking

- (TouchValue *)touchValueForTouch:(UITouch *)touch
{
	TouchValue	*touchValue = [[[TouchValue alloc] init] autorelease];
	touchValue.point = [touch locationInView:touch.view];
	
	return touchValue;
}

- (NSMutableArray *)touchesForUser:(NSString *)user
{
	NSMutableArray	*touchesForUser = [touchDict valueForKey:user];
	if (!touchesForUser)
	{
		touchesForUser = [[NSMutableArray alloc] init];
		[touchDict setValue:touchesForUser forKey:user];
	}
	
	return touchesForUser;
}

- (void)addTouches:(TouchValue *)touchValue forUser:(NSString *)user
{
	NSMutableArray	*touchesForUser = [self touchesForUser:user];
	
	[touchesForUser removeAllObjects];

	if (touchValue)
		[touchesForUser addObject:touchValue];
}

- (void)removeTouches:(TouchValue *)touchValue forUser:(NSString *)user
{
	NSMutableArray	*touchesForUser = [self touchesForUser:user];
	[touchesForUser removeAllObjects];
}

#pragma mark
#pragma mark Local Touches

- (void)sendLocalTouches
{
	NSMutableArray	*touchesForUser = [self touchesForUser:localUser];
	NSData	*data = [NSKeyedArchiver archivedDataWithRootObject:touchesForUser];
	[gameSession sendDataToAllPeers:data withDataMode:GKSendDataUnreliable error:nil];
}

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
	UITouch	*touch = [touches anyObject];
	TouchValue	*touchValue = [self touchValueForTouch:touch];

	[self addTouches:touchValue forUser:localUser];
	[self updateTouchViews];
	[self sendLocalTouches];
}

- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
{
	UITouch	*touch = [touches anyObject];
	TouchValue	*touchValue = [self touchValueForTouch:touch];
	
	[self addTouches:touchValue forUser:localUser];
	[self updateTouchViews];
	[self sendLocalTouches];
}

- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
{
	UITouch	*touch = [touches anyObject];
	TouchValue	*touchValue = [self touchValueForTouch:touch];
	
	[self removeTouches:touchValue forUser:localUser];
	[self updateTouchViews];
	[self sendLocalTouches];
}

- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event
{
	UITouch	*touch = [touches anyObject];
	TouchValue	*touchValue = [self touchValueForTouch:touch];
	
	[self removeTouches:touchValue forUser:localUser];
	[self updateTouchViews];
	[self sendLocalTouches];
}


#pragma mark GKSessionDelegate

- (void)session:(GKSession *)session didReceiveConnectionRequestFromPeer:(NSString *)peerID
{
	[session acceptConnectionFromPeer:peerID error:nil];
}

- (void) receiveData:(NSData *)data fromPeer:(NSString *)peer inSession: (GKSession *)session context:(void *)context
{
	NSMutableArray	*touchValues = [[NSKeyedUnarchiver unarchiveObjectWithData:data] mutableCopy];
	[touchDict setValue:touchValues forKey:peer];
	[self updateTouchViews];
}


#pragma mark GKPeerPickerControllerDelegate

- (void)invalidateSession:(GKSession *)session {
	if(session != nil) {
		[session disconnectFromAllPeers]; 
		session.available = NO; 
		[session setDataReceiveHandler:nil withContext: NULL]; 
		session.delegate = nil; 
	}
}

- (GKSession *)peerPickerController:(GKPeerPickerController *)picker sessionForConnectionType:(GKPeerPickerConnectionType)type
{
	GKSession	*session = [[GKSession alloc] initWithSessionID:nil displayName:localUser sessionMode:GKSessionModePeer];
	
	return [session autorelease];
}

- (void)peerPickerController:(GKPeerPickerController *)picker didConnectPeer:(NSString *)peerID toSession:(GKSession *)session
{
	session.delegate = self;
	[session setDataReceiveHandler:self withContext:nil];
	self.gameSession = session;
	
	NSData *data = [NSKeyedArchiver archivedDataWithRootObject:[NSArray array]];
	[self.gameSession sendDataToAllPeers:data withDataMode:GKSendDataReliable error:nil];
	
	
	// Done with the Peer Picker so dismiss it.
	[picker dismiss];
	[picker autorelease];
}

- (void)peerPickerController:(GKPeerPickerController *)picker didSelectConnectionType:(GKPeerPickerConnectionType)type
{
	if (type == GKPeerPickerConnectionTypeOnline)
	{
		picker.delegate = nil;
		[picker dismiss];
		[picker autorelease];
	}
}

- (void)peerPickerControllerDidCancel:(GKPeerPickerController *)picker
{
	[self invalidateSession:self.gameSession];
	self.gameSession = nil;
}

#pragma mark Actions

- (IBAction)findPeers
{
	GKPeerPickerController	*peerPicker = [[GKPeerPickerController alloc] init];
	peerPicker.delegate = self;
	peerPicker.connectionTypesMask = GKPeerPickerConnectionTypeOnline | GKPeerPickerConnectionTypeNearby;
	[peerPicker show];
}

@end
