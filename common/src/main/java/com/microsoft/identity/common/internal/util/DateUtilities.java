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
package com.microsoft.identity.common.internal.util;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class DateUtilities {

    private static final String LOCALE_PREFIX_ARABIC = "ar";
    private static final String LOCALE_PREFIX_ASSAMESE = "as";
    private static final String LOCALE_PREFIX_BENGALI = "bn";
    private static final String LOCALE_PREFIX_ALGERIAN = "dz";
    private static final String LOCALE_PREFIX_PERSIAN = "fa";
    private static final String LOCALE_PREFIX_KASHMIRI = "ks";
    private static final String LOCALE_PREFIX_MARATHI = "mr";
    private static final String LOCALE_PREFIX_BURMESE = "my";
    private static final String LOCALE_PREFIX_NEPALI = "ne";
    private static final String LOCALE_PREFIX_PUNJABI = "pa";
    private static final String LOCALE_PREFIX_PASHTO = "ps";
    private static final String LOCALE_PREFIX_URDU = "ur";
    private static final String LOCALE_PREFIX_UZBEK = "uz";

    // This list may not be exhaustive, but is represents the set of non-Gregorian locales
    // available as of AOSP API 24
    private static final Set<String> NON_GREGORIAN_CALENDAR_LOCALES = new HashSet<>();

    static {
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_ARABIC);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_ASSAMESE);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_BENGALI);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_ALGERIAN);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_PERSIAN);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_KASHMIRI);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_MARATHI);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_BURMESE);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_NEPALI);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_PUNJABI);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_PASHTO);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_URDU);
        NON_GREGORIAN_CALENDAR_LOCALES.add(LOCALE_PREFIX_UZBEK);
    }

    private DateUtilities() {
    }

    /**
     * Create a copy of a date to avoid exposing the internal references.
     *
     * @param date represents a specific instant in time, with millisecond precision.
     * @return Date
     */
    public static Date createCopy(final Date date) {
        if (date != null) {
            return new Date(date.getTime());
        }

        return date;
    }

    public static long getExpiresOn(long expiresIn) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long currentTimeSecs = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);
        return currentTimeSecs + expiresIn;
    }

    /**
     * Checks if the provided locale has a default calendar format that is non-Gregorian.
     *
     * @param inputLocale A locale to inspect.
     * @return True, if the provided locale's default calendar format is non-Gregorian. False otherwise.
     */
    public static boolean isLocaleCalendarNonGregorian(@NonNull final Locale inputLocale) {
        final String localeStr = inputLocale.toString();
        final String localePrefix = localeStr.split("_")[0];
        return NON_GREGORIAN_CALENDAR_LOCALES.contains(localePrefix);
    }
}

