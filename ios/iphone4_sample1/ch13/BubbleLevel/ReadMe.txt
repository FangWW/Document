
### Bubble Level ###

===========================================================================
DESCRIPTION:

Bubble Level demonstrates how to receive and interpret acceleration information, animate a view, and display a utility view (used for calibration). Open Bubble Level in Xcode and build and run it. To run in the simulator, set the Active SDK to Simulator. To run on a device, set the Active SDK to the appropriate Device setting.

The bubble level presents a simple interface--an analog of a carpenter's level. The UI displays in landscape mode to make it clear to the user which side of the device is the reference side. The bubble animates to reflect the tilt of the device, and a numeric readout displays the tilt in degrees. Arrows indicate which end of the device to raise or lower to achieve level. The sound feedback lets the user know when the device is 'somewhat near', 'very near", and "dead-on' level.

As with most electronic levels, accurate measurements can only be achieved if the instrument is first calibrated. Calibration removes any inaccuracies that are the result of the internal accelerometer not being exactly aligned with the housing of the instrument. In this application, the calibration feature compensates for discrepancies between the alignment of the accelerometer and the reference side of the iPhone or iPod Touch case. Tapping the "i" button displays the calibration view, which provides instructions.

===========================================================================
SPECIAL CONSIDERATIONS:

Since this application relies on the device's accelerometer for orientation input, it must be run on a device for proper operation. It can be run in the simulator to view its user interface, but it will not operate as a bubble level in the simulator.

===========================================================================
BUILD REQUIREMENTS:

Mac OS X 10.6.3, iOS 4.0

===========================================================================
RUNTIME REQUIREMENTS:

iOS 4.0

===========================================================================
PACKAGING LIST:

LevelAppDelegate.h
LevelAppDelegate.m
NSApp's delegate. On start up, this object receives the applicationDidFinishLaunching:delegate message and creates an instance of LevelViewController, which in turn brings up the UI.

LevelViewController.h
LevelViewController.m
This object manages the different features of the application. It creates the Level view, the primary interface of the application. It is also the UIAccelerometer delegate, and so receives updates about changes in device orientation. LevelViewController processes the accelerometer data to determine the new tilt angle, and then sends a message to the LevelView to update the UI. 

LevelViewController also is the controller for the calibration interface. LevelViewController responds to taps on the "i" button to bring up the calibration view, and it is the receiver of messages sent from that view.

LevelView.h
LevelView.m
This view builds the primary application UI. In response to messages from LevelViewController about a change of tilt, LevelView animates the bubble, updates the arrows and degree display, and plays the appropriate sound.

CalibrationView.h
CalibrationView.m
This view builds and displays the calibration UI. Calibration is a simple two-step process, with a button to press at the conclusion of each step. A message is displayed when the steps are complete and the device is calibrated.

SoundEffect.h
SoundEffect.m
A simple Objective-C wrapper around Audio Services functions that allow the loading and playing of sound files. 

===========================================================================
CHANGES FROM PREVIOUS VERSIONS:

Version 1.9
- Updated for iOS 4.0.

Version 1.8
- Updated for and tested with iPhone OS 2.0. First public release.

Version 1.7
- Now use nib file to define window and the application delegate.

Version 1.6
- Updated for Beta 6.
- No longer copy calibration template image from Resource folder, since it is only needed as a layout guide.

Version 1.5
- Updated for Beta 5.

Version 1.4
- Updated for Beta 4.
- For sound playback, replaced AudioFX class with simpler SoundEffect class.

Version 1.3
- Updated for Beta 3. 
- Replaced the ReadMe file with a plain text version.

Version 1.2:
- Improved artwork
- Improved handling of user preferences
- Simplified loading of sound files
- General code clean-up

Version 1.1: 
Changed animation used to transition from front (level view) to back (calibration view) to a standard flip transition. Cleaned up code throughout.

===========================================================================
Copyright (C) 2008-2010 Apple Inc. All rights reserved.