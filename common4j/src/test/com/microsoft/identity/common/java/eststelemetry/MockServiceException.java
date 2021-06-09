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

package com.microsoft.identity.common.java.eststelemetry;

import com.microsoft.identity.common.java.exception.IServiceException;

import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
class MockServiceException implements IServiceException {

    @Builder.Default
    String errorCode = "ERROR_CODE";

    @Builder.Default
    String oAuthSubErrorCode = "OAUTH_SUBERROR_CODE";

    @Builder.Default
    int httpStatusCode = 0;

    @Builder.Default
    HashMap<String, String> httpResponseBody = new HashMap<>();

    @Builder.Default
    HashMap<String, List<String>> httpResponseHeaders = new HashMap<>();

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getOAuthSubErrorCode() {
        return oAuthSubErrorCode;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public HashMap<String, String> getHttpResponseBody() {
        return httpResponseBody;
    }

    @Override
    public HashMap<String, List<String>> getHttpResponseHeaders() {
        return httpResponseHeaders;
    }
}