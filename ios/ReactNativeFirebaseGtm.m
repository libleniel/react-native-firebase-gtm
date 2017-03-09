#import "ReactNativeFirebaseGtm.h"
#import "Firebase.h"

@implementation ReactNativeFirebaseGtm

RCT_EXPORT_MODULE(ReactNativeFirebaseGtm)

@synthesize bridge = _bridge;

RCT_EXPORT_METHOD(push: (NSString*)name property: (NSDictionary*)parameters)
{
    [FIRAnalytics logEventWithName:name parameters:parameters];
}

@end
