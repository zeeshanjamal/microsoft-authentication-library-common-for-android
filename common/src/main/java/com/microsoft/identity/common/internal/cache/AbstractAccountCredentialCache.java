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
package com.microsoft.identity.common.internal.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.dto.AccessTokenRecord;
import com.microsoft.identity.common.internal.dto.AccountRecord;
import com.microsoft.identity.common.internal.dto.Credential;
import com.microsoft.identity.common.internal.dto.CredentialType;
import com.microsoft.identity.common.internal.dto.IdTokenRecord;
import com.microsoft.identity.common.internal.dto.RefreshTokenRecord;
import com.microsoft.identity.common.internal.logging.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.microsoft.identity.common.internal.controllers.BaseController.DEFAULT_SCOPES;

public abstract class AbstractAccountCredentialCache implements IAccountCredentialCache {

    private static final String TAG = AbstractAccountCredentialCache.class.getSimpleName();
    private static final String NEW_LINE = "\n";

    @Nullable
    protected Class<? extends Credential> getTargetClassForCredentialType(@Nullable String cacheKey,
                                                                          @NonNull CredentialType targetType) {
        Class<? extends Credential> credentialClass = null;

        switch (targetType) {
            case AccessToken:
            case AccessToken_With_AuthScheme:
                credentialClass = AccessTokenRecord.class;
                break;
            case RefreshToken:
                credentialClass = RefreshTokenRecord.class;
                break;
            case IdToken:
            case V1IdToken:
                credentialClass = IdTokenRecord.class;
                break;
            default:
                Logger.warn(TAG, "Could not match CredentialType to class. "
                        + "Did you forget to update this method with a new type?");
                if (null != cacheKey) {
                    Logger.warnPII(TAG, "Sought key was: [" + cacheKey + "]");
                }
        }

        return credentialClass;
    }

    @NonNull
    protected List<AccountRecord> getAccountsFilteredByInternal(@Nullable String homeAccountId,
                                                                @Nullable String environment,
                                                                @Nullable String realm,
                                                                @NonNull List<AccountRecord> allAccounts) {
        final boolean mustMatchOnHomeAccountId = !StringExtensions.isNullOrBlank(homeAccountId);
        final boolean mustMatchOnEnvironment = !StringExtensions.isNullOrBlank(environment);
        final boolean mustMatchOnRealm = !StringExtensions.isNullOrBlank(realm);

        Logger.verbose(
                TAG,
                "Account lookup filtered by home_account_id? [" + mustMatchOnHomeAccountId + "]"
                        + NEW_LINE
                        + "Account lookup filtered by realm? [" + mustMatchOnRealm + "]"
        );

        final List<AccountRecord> matchingAccounts = new ArrayList<>();

        for (final AccountRecord account : allAccounts) {
            boolean matches = true;

            if (mustMatchOnHomeAccountId) {
                matches = homeAccountId.equalsIgnoreCase(account.getHomeAccountId().trim());
            }

            if (mustMatchOnEnvironment) {
                matches = matches && environment.equalsIgnoreCase(account.getEnvironment().trim());
            }

            if (mustMatchOnRealm) {
                matches = matches && realm.equalsIgnoreCase(account.getRealm().trim());
            }

            if (matches) {
                matchingAccounts.add(account);
            }
        }

        Logger.verbose(
                TAG,
                "Found [" + matchingAccounts.size() + "] matching accounts"
        );

        return matchingAccounts;
    }

    protected List<Credential> getCredentialsFilteredByInternal(@Nullable String homeAccountId,
                                                                @Nullable String environment,
                                                                @Nullable CredentialType credentialType,
                                                                @Nullable String clientId,
                                                                @Nullable String realm,
                                                                @Nullable String target,
                                                                @Nullable String authScheme,
                                                                @NonNull List<Credential> allCredentials) {
        final boolean mustMatchOnEnvironment = !StringExtensions.isNullOrBlank(environment);
        final boolean mustMatchOnHomeAccountId = !StringExtensions.isNullOrBlank(homeAccountId);
        final boolean mustMatchOnRealm = !StringExtensions.isNullOrBlank(realm);
        final boolean mustMatchOnTarget = !StringExtensions.isNullOrBlank(target);
        final boolean mustMatchOnClientId = !StringExtensions.isNullOrBlank(clientId);
        final boolean mustMatchOnCredentialType = null != credentialType;
        final boolean mustMatchOnAuthScheme = mustMatchOnCredentialType
                && !StringExtensions.isNullOrBlank(authScheme)
                && credentialType == CredentialType.AccessToken_With_AuthScheme;

        Logger.verbose(
                TAG,
                "Credential lookup filtered by home_account_id? [" + mustMatchOnHomeAccountId + "]"
                        + NEW_LINE
                        + "Credential lookup filtered by realm? [" + mustMatchOnRealm + "]"
                        + NEW_LINE
                        + "Credential lookup filtered by target? [" + mustMatchOnTarget + "]"
                        + NEW_LINE
                        + "Credential lookup filtered by clientId? [" + mustMatchOnClientId + "]"
                        + NEW_LINE
                        + "Credential lookup filtered by credential type? [" + mustMatchOnCredentialType + "]"
                        + NEW_LINE
                        + "Credential lookup filtered by auth scheme? [" + mustMatchOnAuthScheme + "]"
        );

        final List<Credential> matchingCredentials = new ArrayList<>();

        for (final Credential credential : allCredentials) {
            boolean matches = true;

            if (mustMatchOnHomeAccountId) {
                matches = homeAccountId.equalsIgnoreCase(credential.getHomeAccountId().trim());
            }

            if (mustMatchOnEnvironment) {
                matches = matches && environment.equalsIgnoreCase(credential.getEnvironment().trim());
            }

            if (mustMatchOnCredentialType) {
                matches = matches && credentialType.name().equalsIgnoreCase(credential.getCredentialType().trim());
            }

            if (mustMatchOnClientId) {
                matches = matches && clientId.equalsIgnoreCase(credential.getClientId().trim());
            }

            if (mustMatchOnRealm && credential instanceof AccessTokenRecord) {
                final AccessTokenRecord accessToken = (AccessTokenRecord) credential;
                matches = matches && realm.equalsIgnoreCase(accessToken.getRealm().trim());
            }

            if (mustMatchOnRealm && credential instanceof IdTokenRecord) {
                final IdTokenRecord idToken = (IdTokenRecord) credential;
                matches = matches && realm.equalsIgnoreCase(idToken.getRealm().trim());
            }

            if (mustMatchOnTarget) {
                if (credential instanceof AccessTokenRecord) {
                    final AccessTokenRecord accessToken = (AccessTokenRecord) credential;
                    matches = matches && targetsIntersect(target, accessToken.getTarget().trim(), true);
                } else if (credential instanceof RefreshTokenRecord) {
                    final RefreshTokenRecord refreshToken = (RefreshTokenRecord) credential;
                    matches = matches && targetsIntersect(target, refreshToken.getTarget().trim(), true);
                } else {
                    Logger.verbose(TAG, "Query specified target-match, but no target to match.");
                }
            }

            if (mustMatchOnAuthScheme && credential instanceof AccessTokenRecord) {
                final AccessTokenRecord accessToken = (AccessTokenRecord) credential;
                String atType = accessToken.getAccessTokenType();

                if (null != atType) {
                    atType = atType.trim();
                }

                matches = matches && authScheme.equalsIgnoreCase(atType);
            }

            if (matches) {
                matchingCredentials.add(credential);
            }
        }

        return matchingCredentials;
    }

    /**
     * Examines the intersections of the provided targets (scopes).
     *
     * @param targetToMatch     The target value[s] our cache-query is looking for.
     * @param credentialTarget  The target against which our sought value will be compared.
     * @param omitDefaultScopes True if MSAL's default scopes should be considered in this lookup.
     *                          False otherwise.
     * @return True, if the credentialTarget contains all of the targets (scopes) declared by
     * targetToMatch. False otherwise.
     */
    static boolean targetsIntersect(@NonNull final String targetToMatch,
                                    @NonNull final String credentialTarget,
                                    boolean omitDefaultScopes) {
        // The credentialTarget must contain all of the scopes in the targetToMatch
        // It may contain more, but it must contain minimally those
        // Matching is case-insensitive
        final String splitCriteria = "\\s+";
        final String[] targetToMatchArray = targetToMatch.split(splitCriteria);
        final String[] credentialTargetArray = credentialTarget.split(splitCriteria);

        // Declare Sets to contain these scopes
        final Set<String> soughtTargetSet = new HashSet<>();
        final Set<String> credentialTargetSet = new HashSet<>();

        // Add the array values to these sets, lowercasing them
        for (final String target : targetToMatchArray) {
            soughtTargetSet.add(target.toLowerCase(Locale.ROOT));
        }

        for (final String target : credentialTargetArray) {
            credentialTargetSet.add(target.toLowerCase(Locale.ROOT));
        }

        if (omitDefaultScopes) {
            soughtTargetSet.removeAll(DEFAULT_SCOPES);
            credentialTargetSet.removeAll(DEFAULT_SCOPES);
        }

        return credentialTargetSet.containsAll(soughtTargetSet);
    }
}
