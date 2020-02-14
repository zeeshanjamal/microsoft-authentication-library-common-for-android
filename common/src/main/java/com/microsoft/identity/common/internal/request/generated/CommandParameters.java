package com.microsoft.identity.common.internal.request.generated;

public abstract class CommandParameters {

    public abstract String clientId();

    public abstract String redirectUri();

    public abstract static class Builder<B extends CommandParameters.Builder<B>> {
        public abstract B setClientId(String value);

        public abstract B setRedirectUri(String value);

        public abstract CommandParameters build();
    }
}
