package com.microsoft.identity.common.internal.request.generated;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.microsoft.identity.common.internal.authorities.Authority;
import com.microsoft.identity.common.internal.dto.IAccountRecord;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@AutoValue
@AutoValue.CopyAnnotations
public abstract class SilentTokenCommandParameters
        extends CommandParameters
        implements IScopesAddable<SilentTokenCommandParameters>, ITokenRequestParameters {

    public abstract Set<String> scopes();

    public abstract IAccountRecord account();

    public abstract Authority authority();

    @Nullable
    public abstract String claimsRequestJson();

    public abstract boolean forceRefresh();

    public static Builder builder() {
        return new AutoValue_SilentTokenCommandParameters.Builder();
    }

    public abstract Builder toBuilder();

    public SilentTokenCommandParameters addDefaultScopes(List<String> defaultScopes) {
        SilentTokenCommandParameters.Builder builder = this.toBuilder();
        Set<String> requestedScopes = this.scopes();
        requestedScopes.addAll(defaultScopes);
        // sanitize empty and null scopes
        requestedScopes.removeAll(Arrays.asList("", null));
        builder.setScopes(requestedScopes);
        return builder.build();
    }

    @AutoValue.Builder
    public abstract static class Builder extends CommandParameters.Builder<SilentTokenCommandParameters.Builder> {
        public abstract Builder setScopes(Set<String> value);

        public abstract Builder setAccount(IAccountRecord value);

        public abstract Builder setClientId(String value);

        public abstract Builder setRedirectUri(String value);

        public abstract Builder setAuthority(Authority value);

        public abstract Builder setClaimsRequestJson(String value);

        public abstract Builder setForceRefresh(boolean value);

        public abstract SilentTokenCommandParameters build();
    }

}
