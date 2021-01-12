#import "ReactNativeCrashlytics.h"

#import <FirebaseCrashlytics/FirebaseCrashlytics.h>

@implementation ReactNativeCrashlytics

RCT_EXPORT_MODULE(ReactNativeCrashlytics)

@synthesize bridge = _bridge;

//MARK: - Setup
+ (BOOL)requiresMainQueueSetup {
  return YES;
}

//MARK: - Log
RCT_EXPORT_METHOD(logMessage: (NSString*)message withAttributes: (NSDictionary*)attributes)
{
  [[FIRCrashlytics crashlytics]logWithFormat:@"%@ - %@", message, attributes];
}

RCT_EXPORT_METHOD(logNonFatalError: (NSString*)message withCode: (NSInteger)code andDomain: (NSString*)domain)
{
  NSDictionary *userInfo;
  
  if (message.length > 0) {
    userInfo = @{
     NSLocalizedDescriptionKey: message,
   };
  }
   
  NSError *error = [NSError errorWithDomain:domain
                                       code:code
                                   userInfo:userInfo];
  
  [[FIRCrashlytics crashlytics]recordError:error];
}

RCT_EXPORT_METHOD(setUserId: (NSString*)userId)
{
  [[FIRCrashlytics crashlytics]setUserID:userId];
}

RCT_EXPORT_METHOD(setCustomValue: (NSDictionary*)value)
{
  for(NSString *key in value) {
    if ([value[key] isKindOfClass: [NSString class]]) {
      NSString *valueString = [NSString stringWithFormat:@"%@", value[key]];
      
      [[FIRCrashlytics crashlytics]setCustomValue:valueString forKey:key];
    } else {
      NSInteger valueInteger = [value[key]integerValue];
      
      [[FIRCrashlytics crashlytics]setCustomValue:@(valueInteger) forKey:key];
    }
  }
}

                  
@end
