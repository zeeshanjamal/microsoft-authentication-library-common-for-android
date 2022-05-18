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

import androidx.annotation.NonNull;

public interface IAdbServer {

    /**
     * Issue a shell command to the device using ADB.
     *
     * @param command the command to be executed
     * @return the return value of the command
     */
    String executeShellCommand(@NonNull final String command);

    /**
     * Issue a shell command to the device as the current UNIX user using ADB.
     *
     * @param command the command to be executed
     * @return the return value of the command
     */
    String executeShellCommandAsCurrentPackage(@NonNull final String command);

    /**
     * Issue an Activity Manager (AM) command to the device using ADB.
     *
     * @param command the command to be executed
     * @return the return value of the command
     */
    String executeActivityManagerCommand(@NonNull final String command);

    /**
     * Issue a Package Manager (PM) command to the device using ADB.
     *
     * @param command the command to be executed
     * @return the return value of the command
     */
    String executePackageManagerCommand(@NonNull final String command);
}
