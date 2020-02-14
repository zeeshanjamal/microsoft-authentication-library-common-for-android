package com.microsoft.identity.common.internal.request.generated;

import com.google.auto.value.AutoValue;
import com.microsoft.identity.common.internal.dto.IAccountRecord;

@AutoValue
public abstract class RemoveAccountCommandParameters extends CommandParameters implements IAccountCommandParameters {

    public abstract IAccountRecord accountRecord();

    public static Builder builder() {
        return new AutoValue_RemoveAccountCommandParameters.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder extends CommandParameters.Builder<RemoveAccountCommandParameters.Builder> {
        public abstract Builder setAccountRecord(IAccountRecord value);

        public abstract RemoveAccountCommandParameters build();
    }
}
