package dev.kikugie.simple_malilib.impl.malilib.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;

public record ConfigCategory(String id, ImmutableList<IConfigBase> options) {
    public ImmutableList<IHotkey> hotkeys() {
        ImmutableList.Builder<IHotkey> builder = new ImmutableList.Builder<>();
        for (IConfigBase configValue : this.options)
            if (configValue instanceof IHotkey hotkey)
                builder.add(hotkey);
        return builder.build();
    }

    public ImmutableList<IKeybind> keybinds() {
        ImmutableList.Builder<IKeybind> builder = new ImmutableList.Builder<>();
        for (IConfigBase configValue : this.options)
            if (configValue instanceof IHotkey hotkey)
                builder.add(hotkey.getKeybind());
        return builder.build();
    }
}
