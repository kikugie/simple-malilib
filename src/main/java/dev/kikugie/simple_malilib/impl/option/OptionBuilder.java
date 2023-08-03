package dev.kikugie.simple_malilib.impl.option;

import fi.dy.masa.malilib.config.IConfigBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public abstract class OptionBuilder<C extends IConfigBase, T> {
    protected String name;
    @Nullable
    protected String comment;
    protected String prettyName;
    protected T defaultValue;

    protected OptionBuilder(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.prettyName = name;
    }

    public OptionBuilder<C, T> comment(String comment) {
        this.comment = comment;
        return this;
    }

    public OptionBuilder<C, T> prettyName(String name) {
        this.prettyName = name;
        return this;
    }

    @NotNull
    public abstract C build();
}
