package com.microsoft.identity.common.internal.request.generated;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class LoadAccountCommandParameters extends CommandParameters implements IAccountCommandParameters {


    public static Builder builder() {
        return new AutoValue_LoadAccountCommandParameters.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder extends CommandParameters.Builder<LoadAccountCommandParameters.Builder> {
        public abstract LoadAccountCommandParameters build();
    }
}
