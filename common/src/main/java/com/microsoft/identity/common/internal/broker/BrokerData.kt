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
package com.microsoft.identity.common.internal.broker

import android.content.Context
import com.microsoft.identity.common.BuildConfig
import com.microsoft.identity.common.adal.internal.AuthenticationConstants
import com.microsoft.identity.common.internal.activebrokerdiscovery.AccountManagerBrokerDiscoveryUtil
import com.microsoft.identity.common.internal.util.PackageUtils
import com.microsoft.identity.common.java.logging.Logger
import java.security.cert.X509Certificate
import java.util.*

/**
 * Represents Package Name and Signature Hash (hash of the app's signing certificate)
 * of a broker app.
 */
data class BrokerData(val packageName : String,
                      val signatureHash : String) {

    override fun equals(other: Any?): Boolean {
        if (other !is BrokerData){
            return false
        }

        return packageName.equals(other.packageName, ignoreCase = true) &&
                signatureHash == other.signatureHash
    }

    // Auto generated by IDE.
    override fun hashCode(): Int {
        var result = packageName.hashCode()
        result = 31 * result + signatureHash.hashCode()
        return result
    }

    override fun toString(): String {
        return "$packageName::$signatureHash"
    }

    companion object {
        val TAG = BrokerData::class.simpleName

        /**
         * Determines if the debug brokers should be trusted or not.
         * This should only be set to true only during testing.
         */
        val sShouldTrustDebugBrokers = BuildConfig.DEBUG

        /**
         * Returns the list of known broker apps (which SDK should make requests to).
         * see [sShouldTrustDebugBrokers] for more info regarding testing.
         **/
        fun getKnownBrokerApps() : Set<BrokerData> {
            return if (sShouldTrustDebugBrokers) allBrokers else prodBrokers
        }

        /**
         * Validate that the installed counterpart of the given [BrokerData] is signed by known keys.
         * @return true if the validation succeeds. False otherwise.
         **/
        fun isSignedByKnownKeys(brokerData: BrokerData, context: Context): Boolean {
            val methodTag = "$TAG:isSignedByKnownKeys"
            return try {
                // Read all the certificates associated with the package name. In higher version of
                // android sdk, package manager will only returned the cert that is used to sign the
                // APK. Even a cert is claimed to be issued by another certificates, sdk will return
                // the signing cert. However, for the lower version of android, it will return all the
                // certs in the chain. We need to verify that the cert chain is correctly chained up.
                val certs: List<X509Certificate> =
                    PackageUtils.readCertDataForApp(brokerData.packageName, context)

                // Verify the cert list contains the cert we trust.
                PackageUtils.verifySignatureHash(certs, setOf(brokerData.signatureHash).iterator())

                // Perform the certificate chain validation. If there is only one cert returned,
                // no need to perform certificate chain validation.
                if (certs.size > 1) {
                    PackageUtils.verifyCertificateChain(certs)
                }

                true
            } catch (t: Throwable) {
                Logger.error(methodTag, "Failed to validate broker app $this", t)
                return false
            }
        }

        @JvmStatic
        val debugMicrosoftAuthenticator = BrokerData(
            AuthenticationConstants.Broker.AZURE_AUTHENTICATOR_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.AZURE_AUTHENTICATOR_APP_DEBUG_SIGNATURE_SHA512
        )

        @JvmStatic
        val prodMicrosoftAuthenticator = BrokerData(
            AuthenticationConstants.Broker.AZURE_AUTHENTICATOR_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.AZURE_AUTHENTICATOR_APP_RELEASE_SIGNATURE_SHA512
        )

        @JvmStatic
        val prodCompanyPortal = BrokerData(
            AuthenticationConstants.Broker.COMPANY_PORTAL_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.COMPANY_PORTAL_APP_RELEASE_SIGNATURE_SHA512
        )

        @JvmStatic
        val debugBrokerHost = BrokerData(
            AuthenticationConstants.Broker.BROKER_HOST_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.BROKER_HOST_APP_SIGNATURE_SHA512
        )

        @JvmStatic
        val debugMockCp = BrokerData(
            AuthenticationConstants.Broker.MOCK_CP_PACKAGE_NAME,
            AuthenticationConstants.Broker.MOCK_CP_SIGNATURE_SHA512
        )

        @JvmStatic
        val debugMockAuthApp = BrokerData(
            AuthenticationConstants.Broker.MOCK_AUTH_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.MOCK_AUTH_APP_SIGNATURE_SHA512
        )

        @JvmStatic
        val debugMockLtw = BrokerData(
            AuthenticationConstants.Broker.MOCK_LTW_PACKAGE_NAME,
            AuthenticationConstants.Broker.MOCK_LTW_SIGNATURE_SHA512
        )

        @JvmStatic
        val prodLTW = BrokerData(
            AuthenticationConstants.Broker.LTW_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.LTW_APP_SHA512_RELEASE_SIGNATURE
        )

        @JvmStatic
        val debugLTW = BrokerData(
            AuthenticationConstants.Broker.LTW_APP_PACKAGE_NAME,
            AuthenticationConstants.Broker.LTW_APP_SHA512_DEBUG_SIGNATURE
        )

        @JvmStatic
        val debugBrokers: Set<BrokerData> =
            Collections.unmodifiableSet(object : HashSet<BrokerData>() {
                init {
                    add(debugMicrosoftAuthenticator)
                    add(debugLTW)
                    add(debugBrokerHost)
                    add(debugMockCp)
                    add(debugMockAuthApp)
                    add(debugMockLtw)
                }
            })

        @JvmStatic
        val prodBrokers: Set<BrokerData> =
            Collections.unmodifiableSet(object : HashSet<BrokerData>() {
                init {
                    add(prodMicrosoftAuthenticator)
                    add(prodCompanyPortal)
                }
            })

        @JvmStatic
        val allBrokers: Set<BrokerData> =
            Collections.unmodifiableSet(object : HashSet<BrokerData>() {
                init {
                    addAll(debugBrokers)
                    addAll(prodBrokers)
                }
            })
    }
}
