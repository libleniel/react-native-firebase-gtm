#import "ReactNativeFirebaseGtm.h"

#import <FirebaseAnalytics/FirebaseAnalytics.h>

@implementation ReactNativeFirebaseGtm

RCT_EXPORT_MODULE(ReactNativeFirebaseGtm)

@synthesize bridge = _bridge;

RCT_EXPORT_METHOD(push: (NSString*)name property: (NSDictionary*)parameters)
{
    [FIRAnalytics logEventWithName:name parameters:parameters];
}

RCT_EXPORT_METHOD(setUserProperty: (NSString*)name value:(NSString*)value)
{
	[FIRAnalytics setUserPropertyString:value forName:name];
}

- (NSDictionary *)constantsToExport
{
    return @{ @"kFIRParameterCurrency": kFIRParameterCurrency,
              @"kFIREventViewItem": kFIREventViewItem,
              @"kFIREventBeginCheckout": kFIREventBeginCheckout,
              @"kFIRParameterCheckoutStep": kFIRParameterCheckoutStep,
              @"kFIREventEcommercePurchase": kFIREventEcommercePurchase,
              @"kFIREventAddToCart": kFIREventAddToCart,
              @"kFIREventRemoveFromCart": kFIREventRemoveFromCart,
              @"kFIREventViewSearchResults": kFIREventViewSearchResults,
              @"kFIREventSelectContent": kFIREventSelectContent,
              };
}

+ (BOOL)requiresMainQueueSetup {    
	return YES;
}

@end
