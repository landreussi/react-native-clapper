#import "RCTReactNativeNoiseRecognizr.h"

@implementation ReactNativeNoiseRecognizr

RCT_EXPORT_MODULE();

RCT_REMAP_METHOD(reactNativeNoiseRecognizr,
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(@"Hello World!");
}

@end
