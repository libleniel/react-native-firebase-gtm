#import "ReactNativeFirebaseRemoteConfig.h"

@implementation ReactNativeFirebaseRemoteConfig

RCT_EXPORT_MODULE(ReactNativeFirebaseRemoteConfig)

@synthesize bridge = _bridge;

- (id)init
{
    self = [super init];
    if (self) {
        self.remoteConfig = [FIRRemoteConfig remoteConfig];
    }
    return self;
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

RCT_EXPORT_METHOD(fetchRemoteConfig:(int)cacheTime)
{
    [self.remoteConfig fetchWithExpirationDuration:cacheTime completionHandler:^(FIRRemoteConfigFetchStatus status, NSError * _Nullable error) {
        if (status == FIRRemoteConfigFetchStatusSuccess) {
            [self.remoteConfig activateFetched];
        } else {
            NSLog(@"Fetch remote config: failed.");
        }
    }];
}

RCT_EXPORT_METHOD(setDefaults:(NSDictionary*)parameters)
{
    [self.remoteConfig setDefaults:parameters];
}

RCT_EXPORT_METHOD(getString:(NSString *)key withCallback:(RCTResponseSenderBlock)callback)
{
    NSString *value = self.remoteConfig[key].stringValue;
    callback(@[value]);
}

RCT_EXPORT_METHOD(getBoolean:(NSString *)key withCallback:(RCTResponseSenderBlock)callback)
{
    BOOL value = self.remoteConfig[key].boolValue;
    callback(@[@(value)]);
}

@end
