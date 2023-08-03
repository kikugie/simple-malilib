package dev.kikugie.simple_malilib.impl.malilib.init;

import dev.kikugie.simple_malilib.impl.malilib.config.ConfigCategory;
import dev.kikugie.simple_malilib.impl.malilib.config.ConfigHandler;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

public class KeybindProvider implements IKeybindProvider {
    private final String modId;
    private final ConfigHandler config;

    public KeybindProvider(String modId, ConfigHandler config) {
        this.modId = modId;
        this.config = config;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (ConfigCategory category : this.config.categories)
            category.keybinds().forEach(manager::addKeybindToMap);
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        for (ConfigCategory category : this.config.categories)
            manager.addHotkeysForCategory(this.modId, "%s.hotkeys.category.%s".formatted(this.modId, category.id()), category.hotkeys());
    }
}
