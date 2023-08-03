package dev.kikugie.simple_malilib.impl.option;

import dev.kikugie.simple_malilib.impl.mixin.ConfigBaseAccessor;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class IntOption extends OptionBuilder<ConfigInteger, Integer> {
    protected int minVal = 0;
    protected int maxVal = Integer.MAX_VALUE;
    boolean slider = false;

    public IntOption(String name, int value) {
        super(name, value);
    }

    public IntOption min(int minVal) {
        this.minVal = minVal;
        return this;
    }

    public IntOption max(int maxVal) {
        this.maxVal = maxVal;
        return this;
    }

    public IntOption range(int minVal, int maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        return this;
    }

    public IntOption slider() {
        this.slider = true;
        return this;
    }

    @Override
    public IntOption prettyName(String name) {
        return (IntOption) super.prettyName(name);
    }

    @Override
    public IntOption comment(String comment) {
        return (IntOption) super.comment(comment);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull ConfigInteger build() {
        Validate.validState(this.minVal > this.maxVal,
                "minVal must be less than or equal to maxVal");
        Validate.validState(this.defaultValue < this.minVal || this.defaultValue > this.maxVal,
                "Default value must be between minVal and maxVal");
        Validate.validState(this.maxVal == Integer.MAX_VALUE && this.slider,
                "Are you sure you want to use a slider with no max value?");


        ConfigInteger config = new ConfigInteger(this.name, this.defaultValue, this.minVal, this.maxVal, this.slider, this.comment);

        if (!this.name.equals(this.prettyName))
            ((ConfigBaseAccessor) config).setPrettyName(this.prettyName);
        return config;
    }
}
