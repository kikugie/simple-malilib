package dev.kikugie.simple_malilib.test;

import dev.kikugie.simple_malilib.api.config.MalilibConfig;
import net.fabricmc.api.ClientModInitializer;

public class Entrypoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MalilibConfig.builder("testmod", "0.0.0")
                .register(Config.class)
                .build();
    }
}
