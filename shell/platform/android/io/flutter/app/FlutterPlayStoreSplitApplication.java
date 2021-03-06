// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.app;

import androidx.annotation.CallSuper;
import com.google.android.play.core.splitcompat.SplitCompatApplication;
import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.dynamicfeatures.PlayStoreDynamicFeatureManager;

/**
 * Flutter's extension of {@link SplitCompatApplication} that injects a {@link
 * PlayStoreDynamicFeatureManager} with {@link FlutterInjector} to enable Split AOT Flutter apps.
 *
 * <p>To use this class, either have your custom application class extend
 * FlutterPlayStoreSplitApplication or use it directly in the app's AndroidManifest.xml by adding
 * the following line:
 *
 * <pre>{@code
 * <manifest
 *    ...
 *    <application
 *       android:name="io.flutter.app.FlutterPlayStoreSplitApplication"
 *       ...>
 *    </application>
 *  </manifest>
 * }</pre>
 *
 * This class is meant to be used with the Google Play store. Custom non-play store applications do
 * not need to extend SplitCompatApplication and should inject a custom {@link
 * io.flutter.embedding.engine.dynamicfeatures.DynamicFeatureManager} implementation like so:
 *
 * <pre>{@code
 * FlutterInjector.setInstance(
 *      new FlutterInjector.Builder().setDynamicFeatureManager(yourCustomManager).build());
 * }</pre>
 */
public class FlutterPlayStoreSplitApplication extends SplitCompatApplication {
  @Override
  @CallSuper
  public void onCreate() {
    super.onCreate();
    // Create and inject a PlayStoreDynamicFeatureManager, which is the default manager for
    // interacting with the Google Play Store.
    PlayStoreDynamicFeatureManager dynamicFeatureManager =
        new PlayStoreDynamicFeatureManager(this, null);
    FlutterInjector.setInstance(
        new FlutterInjector.Builder().setDynamicFeatureManager(dynamicFeatureManager).build());
  }
}
