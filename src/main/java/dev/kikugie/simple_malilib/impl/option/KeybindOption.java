package dev.kikugie.simple_malilib.impl.option;

import dev.kikugie.simple_malilib.api.util.KeyActionResult;
import dev.kikugie.simple_malilib.impl.malilib.HotkeyCallback;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@SuppressWarnings("unused")
public class KeybindOption extends OptionBuilder<IConfigBase, String> {
    @Nullable
    protected KeybindSettings settings = KeybindSettings.DEFAULT;
    @Nullable
    protected IHotkeyCallback callback;
    @Nullable
    protected Function<KeyAction, KeyActionResult> callbackBuilder;

    public KeybindOption(String name, String defaultValue) {
        super(name, defaultValue);
    }

    public KeybindOption settings(KeybindSettings settings) {
        this.settings = settings;
        return this;
    }

    public KeybindOption callback(Function<KeyAction, KeyActionResult> callback) {
        this.callbackBuilder = callback;
        return this;
    }

    public KeybindOption callback(IHotkeyCallback callback) {
        this.callback = callback;
        return this;
    }

    private IHotkeyCallback buildCallback(IKeybind keybind) {
        return this.callback == null
                ? new HotkeyCallback(keybind, this.callbackBuilder)
                : this.callback;
    }

    @Override
    public KeybindOption prettyName(String name) {
        return (KeybindOption) super.prettyName(name);
    }

    @Override
    public KeybindOption comment(String comment) {
        return (KeybindOption) super.comment(comment);
    }

    @Override
    public @NotNull IConfigBase build() {
        ConfigHotkey config = new ConfigHotkey(this.name, this.defaultValue, this.settings, this.comment, this.prettyName);

        IKeybind actualKeybind = config.getKeybind();
        actualKeybind.setCallback(buildCallback(actualKeybind));
        return config;
    }
}
