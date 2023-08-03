package dev.kikugie.simple_malilib.impl.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import java.util.HashMap;
import java.util.Map;

public class ModMenuPlugin implements ModMenuApi {
    private static final Map<String, ConfigScreenFactory<?>> factories = new HashMap<>();

    public static void register(String modId, ConfigScreenFactory<?> factory) {
        if (factories.put(modId, factory) != null)
            throw new IllegalArgumentException("Mod menu entry for %s already exists".formatted(modId));
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return factories;
    }
}
