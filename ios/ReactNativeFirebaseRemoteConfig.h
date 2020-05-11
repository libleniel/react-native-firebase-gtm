#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <FirebaseRemoteConfig/FirebaseRemoteConfig.h>

@interface ReactNativeFirebaseRemoteConfig : NSObject <RCTBridgeModule>

@property (strong) FIRRemoteConfig *remoteConfig;

@end
