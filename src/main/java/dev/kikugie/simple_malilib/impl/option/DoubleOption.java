package dev.kikugie.simple_malilib.impl.option;

import dev.kikugie.simple_malilib.impl.mixin.ConfigBaseAccessor;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DoubleOption extends OptionBuilder<ConfigDouble, Double> {
    protected double minVal = Double.MIN_VALUE;
    protected double maxVal = Double.MAX_VALUE;
    boolean slider;

    public DoubleOption(String name, double value) {
        super(name, value);
    }

    public DoubleOption min(double minVal) {
        this.minVal = minVal;
        return this;
    }

    public DoubleOption max(double maxVal) {
        this.maxVal = maxVal;
        return this;
    }

    public DoubleOption range(double minVal, double maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        return this;
    }

    public DoubleOption slider() {
        this.slider = true;
        return this;
    }

    @Override
    public DoubleOption prettyName(String name) {
        return (DoubleOption) super.prettyName(name);
    }

    @Override
    public DoubleOption comment(String comment) {
        return (DoubleOption) super.comment(comment);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull ConfigDouble build() {
        Validate.validState(this.minVal > this.maxVal,
                "minVal must be less than or equal to maxVal");
        Validate.validState(this.defaultValue < this.minVal || this.defaultValue > this.maxVal,
                "Default value must be between minVal and maxVal");
        Validate.validState(this.maxVal == Double.MAX_VALUE && this.slider,
                "Are you sure you want to use a slider with no max value?");

        ConfigDouble config = new ConfigDouble(this.name, this.defaultValue, this.minVal, this.maxVal, this.slider, this.comment);
        if (!this.name.equals(this.prettyName))
            ((ConfigBaseAccessor) config).setPrettyName(this.prettyName);
        return config;
    }
}
