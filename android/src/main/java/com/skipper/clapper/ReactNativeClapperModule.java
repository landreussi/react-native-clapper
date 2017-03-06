package com.skipper.clapper;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.onsets.PercussionOnsetDetector;
import be.tarsos.dsp.onsets.OnsetHandler;
class ReactNativeClapperModule extends ReactContextBaseJavaModule {
    private Context context;
    private int threshold;
    private int sensitivity;
    public Callback callback;
    public ReactNativeClapperModule(ReactApplicationContext reactContext, int threshold, int sensitivity) {
        super(reactContext);
        this.context = reactContext;
        this.sensitivity = sensitivity;
        this.threshold = threshold;
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return "ReactNativeClapper";
    }

    @ReactMethod
    public void listen(Callback onResponse, Double threshold, Double sensitivity) {
      final int audioBufferSize = 8192;
      final int bufferOverlap   = 512;
      final int sampleRate      = 44100;
      callback = onResponse;
      AudioDispatcherFactory factory = new AudioDispatcherFactory();
      AudioDispatcher mDispatcher = factory.fromDefaultMicrophone(sampleRate, audioBufferSize, bufferOverlap);
      PercussionOnsetDetector mPercussionDetector = new PercussionOnsetDetector(44100, 8192,
      new OnsetHandler() {
          @Override
          public void handleOnset(double time, double salience) {
            callback.invoke("Clap");
          }
      }, this.sensitivity, this.threshold);
      mDispatcher.addAudioProcessor(mPercussionDetector);
      new Thread(mDispatcher).start();
    }
}
