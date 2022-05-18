//  Copyright (c) Microsoft Corporation.
//  All rights reserved.
//
//  This code is licensed under the MIT License.
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files(the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions :
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
package com.microsoft.identity.client.ui.automation.ui;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import com.microsoft.identity.client.ui.automation.keyboard.IKeyboard;
import com.microsoft.identity.client.ui.automation.keyboard.Keyboard;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public enum UiAutomatorInteractionHandler implements IUiObjectInteractionHandler<UiObject2, BySelector, Void> {
    INSTANCE;

    private final static String TAG = UiAutomatorInteractionHandler.class.getSimpleName();

    public final static long FIND_UI_ELEMENT_TIMEOUT_DEFAULT = TimeUnit.SECONDS.toMillis(30);

    private final UiDevice mUiDevice = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
    );

    private final IKeyboard mKeyboard = new Keyboard();

    @Override
    public UiObject2 findObject(@NonNull final BySelector query) {
        return mUiDevice.wait(Until.findObject(query), FIND_UI_ELEMENT_TIMEOUT_DEFAULT);
    }

    @Override
    public Void performInteraction(@NonNull final BySelector query,
                                   @NonNull final Function<UiObject2, Void> function) {
        final UiObject2 uiObject = findObject(query);
        return function.apply(uiObject);
    }

    @Override
    public Void typeText(@NonNull final BySelector query, @NonNull final String text) {
        return performInteraction(query, uiObject2 -> {
            uiObject2.setText(text);
            mKeyboard.close();
            return null;
        });
    }

    @Override
    public Void click(@NonNull final BySelector query) {
        return performInteraction(query, uiObject2 -> {
            uiObject2.click();
            return null;
        });
    }
}
