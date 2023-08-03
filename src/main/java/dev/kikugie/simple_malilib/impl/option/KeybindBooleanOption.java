package dev.kikugie.simple_malilib.impl.option;

import dev.kikugie.simple_malilib.api.util.KeyActionResult;
import dev.kikugie.simple_malilib.impl.malilib.HotkeyCallback;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.hotkeys.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@SuppressWarnings("unused")
public class KeybindBooleanOption extends OptionBuilder<ConfigBooleanHotkeyed, Boolean> {
    protected String keybind;
    @Nullable
    protected KeybindSettings settings = KeybindSettings.DEFAULT;
    @Nullable
    protected IHotkeyCallback callback;
    @Nullable
    protected Function<KeyAction, KeyActionResult> callbackBuilder;

    public KeybindBooleanOption(String name, boolean value, String keybind) {
        super(name, value);
        this.keybind = keybind;
    }

    public KeybindBooleanOption settings(KeybindSettings settings) {
        this.settings = settings;
        return this;
    }

    public KeybindBooleanOption callback(Function<KeyAction, KeyActionResult> callback) {
        this.callbackBuilder = callback;
        return this;
    }

    public KeybindBooleanOption callback(IHotkeyCallback callback) {
        this.callback = callback;
        return this;
    }

    private IHotkeyCallback buildCallback(IKeybind keybind) {
        return this.callback == null
                ? new HotkeyCallback(keybind, this.callbackBuilder)
                : this.callback;
    }

    @Override
    public KeybindBooleanOption prettyName(String name) {
        return (KeybindBooleanOption) super.prettyName(name);
    }

    @Override
    public KeybindBooleanOption comment(String comment) {
        return (KeybindBooleanOption) super.comment(comment);
    }

    @NotNull
    @Override
    public ConfigBooleanHotkeyed build() {
        ConfigBooleanHotkeyed config = new ConfigBooleanHotkeyed(this.name, this.defaultValue, this.keybind, this.settings, this.comment, this.prettyName);

        IKeybind actualKeybind = config.getKeybind();
        actualKeybind.setCallback(buildCallback(actualKeybind));
        return config;
    }
}
