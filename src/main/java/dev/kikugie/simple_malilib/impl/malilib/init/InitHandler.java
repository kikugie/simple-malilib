package dev.kikugie.simple_malilib.impl.malilib.init;

import dev.kikugie.simple_malilib.impl.malilib.config.ConfigHandler;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;

public class InitHandler implements IInitializationHandler {
    private final String modId;
    private final ConfigHandler config;

    public InitHandler(String modId, ConfigHandler config) {
        this.modId = modId;
        this.config = config;
    }

    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance().registerConfigHandler(this.modId, this.config);
        InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider(this.modId, this.config));
    }
}
