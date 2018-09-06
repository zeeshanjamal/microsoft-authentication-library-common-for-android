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
package com.microsoft.identity.common.internal.providers.oauth2;

import android.content.Intent;

import com.microsoft.identity.common.exception.ClientException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/**
 * Abstracts the behavior associated with gathering a user authorization for an access token (oAuth)
 * and/or authentication information (OIDC)
 * Possible implementations include: EmbeddedWebViewAuthorizationStrategy, SystemWebViewAuthorizationStrategy, Device Code, etc...
 */
public abstract class AuthorizationStrategy<GenericOAuth2Strategy extends OAuth2Strategy,
        GenericAuthorizationRequest extends AuthorizationRequest> {
    public static final int BROWSER_FLOW = 1001;

    public static final String REQUEST_URL_KEY = "com.microsoft.identity.request.url.key";

    public static final String REQUEST_ID = "com.microsoft.identity.request.id";

    public static final String CUSTOM_TAB_REDIRECT = "com.microsoft.identity.customtab.redirect";

    public static final String AUTHORIZATION_FINAL_URL = "com.microsoft.identity.client.final.url";

    public static final class UIResponse {
        /**
         * Represents that user cancelled the flow.
         */
        public static final int AUTH_CODE_CANCEL = 2001;

        /**
         * Represents that browser error is returned.
         */
        public static final int AUTH_CODE_ERROR = 2002;

        /**
         * Represents that the authorization code is returned successfully.
         */
        public static final int AUTH_CODE_COMPLETE = 2003;

        /**
         * Represents that broker successfully returns the response.
         */
        public static final int TOKEN_BROKER_RESPONSE = 2004;

        /**
         * Webview throws Authentication exception. It needs to be send to callback.
         */
        public static final int BROWSER_CODE_AUTHENTICATION_EXCEPTION = 2005;

        /**
         * CA flow, device doesn't have company portal or azure authenticator installed.
         * Waiting for broker package to be installed, and resume request in broker.
         */
        public static final int BROKER_REQUEST_RESUME = 2006;

        /**
         * Device registration in broker apps.
         */
        public static final int BROWSER_CODE_DEVICE_REGISTER = 2007;

        public static final String ERROR_CODE = "error_code";

        public static final String ERROR_DESCRIPTION = "error_description";
    }

    /**
     * Perform the authorization request.
     */
    public abstract Future<AuthorizationResult> requestAuthorization(GenericAuthorizationRequest authorizationRequest,
                                                                     GenericOAuth2Strategy oAuth2Strategy)
            throws ClientException, UnsupportedEncodingException;

    public abstract void completeAuthorization(int requestCode, int resultCode, final Intent data);
}
