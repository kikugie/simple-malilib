package dev.kikugie.simple_malilib.impl.option;

import dev.kikugie.simple_malilib.impl.mixin.ConfigBaseAccessor;
import fi.dy.masa.malilib.config.IConfigBase;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;

public class GenericOption<C extends IConfigBase, T> extends OptionBuilder<C, T> {
    private final TriFunction<String, T, String, C> configBuilder;

    public GenericOption(String name, T defaultValue, TriFunction<String, T, String, C> configBuilder) {
        super(name, defaultValue);
        this.configBuilder = configBuilder;
    }

    @Override
    public @NotNull C build() {
        C config = this.configBuilder.apply(this.name, this.defaultValue, this.comment);
        if (!this.name.equals(this.prettyName))
            ((ConfigBaseAccessor) config).setPrettyName(this.prettyName);
        return config;
    }
}
