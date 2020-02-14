package com.microsoft.identity.common.internal.request.generated;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2TokenCache;
import com.microsoft.identity.common.internal.request.SdkType;

import net.jcip.annotations.Immutable;

@Immutable
@AutoValue
@AutoValue.CopyAnnotations
public abstract class CommandContext {

    @Nullable
    public abstract String correlationId();

    @Nullable
    public abstract String applicationName();

    @Nullable
    public abstract String applicationVersion();

    @Nullable
    public abstract String requiredBrokerProtocolVersion();

    @NonNull
    public abstract SdkType sdkType(); //Need a default value

    @NonNull
    public abstract String sdkVersion();

    @NonNull
    public abstract Context androidApplicationContext();

    @NonNull
    public abstract OAuth2TokenCache tokenCache();

    public static CommandContext.Builder builder() {
        return new AutoValue_CommandContext.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @Nullable
        public abstract Builder setCorrelationId(final String correlationId);

        @Nullable
        public abstract Builder setApplicationName(final String applicationName);

        @Nullable
        public abstract Builder setApplicationVersion(final String applicationVersion);

        @Nullable
        public abstract Builder setRequiredBrokerProtocolVersion(final String requiredBrokerProtocolVersion);

        @NonNull
        public abstract Builder setSdkType(final SdkType sdkType); //Need a default value

        @NonNull
        public abstract Builder setSdkVersion(final String sdkVersion);

        @NonNull
        public abstract Builder setAndroidApplicationContext(final Context androidApplicationContext);

        @NonNull
        public abstract Builder setTokenCache(final OAuth2TokenCache tokenCache);

        public abstract CommandContext build();
    }
}
