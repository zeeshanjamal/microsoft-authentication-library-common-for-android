// Copyright (c) Microsoft Corporation.
// All rights reserved.
//
// This code is licensed under the MIT License.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files(the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions :
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
package com.microsoft.identity.common.internal.net;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Deprecated
 * <p>
 * Currently served as an adapter of {@link com.microsoft.identity.common.java.net.HttpResponse}
 */
public final class HttpResponse extends com.microsoft.identity.common.java.net.HttpResponse {
    public HttpResponse(@NonNull final com.microsoft.identity.common.java.net.HttpResponse responseToBeAdapted) {
        super(responseToBeAdapted.getDate(),
                responseToBeAdapted.getStatusCode(),
                responseToBeAdapted.getBody(),
                responseToBeAdapted.getHeaders());
    }

    public HttpResponse(final int statusCode,
                        final String responseBody,
                        final Map<String, List<String>> responseHeaders) {
        super(null, statusCode, responseBody, responseHeaders);
    }

    public HttpResponse(final Date date,
                        final int statusCode,
                        final String responseBody,
                        final Map<String, List<String>> headerFields) {
        super(date, statusCode, responseBody, headerFields);
    }
}
