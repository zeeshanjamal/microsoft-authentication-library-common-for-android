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
package com.microsoft.identity.client.ui.automation.adb;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.annotation.NonNull;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.UiDevice;

import com.microsoft.identity.client.ui.automation.error.ErrorCode;
import com.microsoft.identity.client.ui.automation.error.TestFailureError;
import com.microsoft.identity.client.ui.automation.logging.Logger;

import java.io.IOException;

/**
 * An implementation of {@link IAdbServer}.
 */
public enum AdbServer implements IAdbServer {
    INSTANCE;

    private final static String TAG = AdbServer.class.getSimpleName();

    @Override
    public String executeShellCommand(@NonNull final String command) {
        final String methodTag = TAG + ":executeShellCommand";
        Logger.i(TAG, "Execute the given command:" + command + " on the shell..");
        final UiDevice device = UiDevice.getInstance(getInstrumentation());
        try {
            return device.executeShellCommand(command);
        } catch (final IOException e) {
            Logger.e(methodTag, ErrorCode.ADB_SERVER_IO_ERROR.name(), e);
            throw new TestFailureError(ErrorCode.ADB_SERVER_IO_ERROR, e);
        }
    }

    @Override
    public String executeShellCommandAsCurrentPackage(@NonNull final String command) {
        final String pkg = ApplicationProvider.getApplicationContext().getPackageName();
        final String completeCmd = "run-as " + pkg + " " + command;
        return executeShellCommand(completeCmd);
    }

    @Override
    public String executeActivityManagerCommand(@NonNull final String command) {
        return executeShellCommand("am " + command);
    }

    @Override
    public String executePackageManagerCommand(@NonNull final String command) {
        return executeShellCommand("pm " + command);
    }
}
