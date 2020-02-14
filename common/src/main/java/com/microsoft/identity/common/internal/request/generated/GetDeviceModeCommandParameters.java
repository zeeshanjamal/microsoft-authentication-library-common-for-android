package com.microsoft.identity.common.internal.request.generated;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetDeviceModeCommandParameters extends CommandParameters {

    public static GetDeviceModeCommandParameters.Builder builder() {
        return new AutoValue_GetDeviceModeCommandParameters.Builder();
    }

    public abstract GetDeviceModeCommandParameters.Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder extends CommandParameters.Builder<GetDeviceModeCommandParameters.Builder> {

        public abstract GetDeviceModeCommandParameters build();
    }

}

