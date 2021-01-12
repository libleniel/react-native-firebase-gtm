#import "ReactNativeFirebaseGtm.h"

#import <FirebaseAnalytics/FirebaseAnalytics.h>

@implementation ReactNativeFirebaseGtm

RCT_EXPORT_MODULE(ReactNativeFirebaseGtm)

@synthesize bridge = _bridge;

//MARK: - Analytics
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
    return @{ @"kFIREventViewItem": kFIREventViewItem,
              @"kFIREventBeginCheckout": kFIREventBeginCheckout,
              @"kFIREventEcommercePurchase": kFIREventEcommercePurchase,
              @"kFIREventAddToCart": kFIREventAddToCart,
              @"kFIREventRemoveFromCart": kFIREventRemoveFromCart,
              @"kFIREventViewSearchResults": kFIREventViewSearchResults,
              @"kFIREventSelectContent": kFIREventSelectContent,
              @"kFIRParameterCurrency": kFIRParameterCurrency,
              @"kFIRParameterCheckoutStep": kFIRParameterCheckoutStep,
              @"kFIRParameterItemList": kFIRParameterItemList,
              @"kFIRParameterItemID": kFIRParameterItemID,
              @"kFIRParameterItemName": kFIRParameterItemName,
              @"kFIRParameterItemCategory": kFIRParameterItemCategory,
              @"kFIRParameterItemBrand": kFIRParameterItemBrand,
              @"kFIRParameterPrice": kFIRParameterPrice,
              @"kFIRParameterIndex": kFIRParameterIndex,
              @"kFIRParameterQuantity": kFIRParameterQuantity,
              @"kFIRParameterTransactionID": kFIRParameterTransactionID,
              @"kFIRParameterValue": kFIRParameterValue,
              @"kFIRParameterShipping": kFIRParameterShipping,
              @"kFIRParameterCoupon": kFIRParameterCoupon,
              };
}

//MARK: - Setup
+ (BOOL)requiresMainQueueSetup {    
	return YES;
}

@end
