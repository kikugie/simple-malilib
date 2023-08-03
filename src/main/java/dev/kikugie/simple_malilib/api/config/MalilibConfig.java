package dev.kikugie.simple_malilib.api.config;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.kikugie.simple_malilib.SimpleMalilibMod;
import dev.kikugie.simple_malilib.api.annotation.Category;
import dev.kikugie.simple_malilib.api.annotation.Exclude;
import dev.kikugie.simple_malilib.impl.access.ConfigCommentAccessor;
import dev.kikugie.simple_malilib.impl.compat.ModMenuPlugin;
import dev.kikugie.simple_malilib.impl.malilib.config.ConfigCategory;
import dev.kikugie.simple_malilib.impl.malilib.config.ConfigHandler;
import dev.kikugie.simple_malilib.impl.malilib.gui.ConfigGui;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBase;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MalilibConfig {
    private final List<ConfigCategory> categories = new ArrayList<>();
    private final String modId;
    private final String modVersion;
    private ConfigHandler handler;
    private ConfigScreenFactory<ConfigGui> guiSupplier;

    private MalilibConfig(String modId, String modVersion) {
        this.modId = modId;
        this.modVersion = modVersion;
    }

    public static MalilibConfig builder(String modId, String modVersion) {
        return new MalilibConfig(modId, modVersion);
    }

    public MalilibConfig register(Class<?> category) {
        Validate.validState(category.isAnnotationPresent(Category.class), "Category class must be annotated with @Category");

        Category annotation = category.getAnnotation(Category.class);
        ImmutableList.Builder<IConfigBase> options = new ImmutableList.Builder<>();

        for (Field option : category.getDeclaredFields()) {
            if (option.isAnnotationPresent(Exclude.class))
                continue;

            Object temp;
            try {
                temp = option.get(category);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (!(temp instanceof ConfigBase<?> value)) {
                SimpleMalilibMod.LOGGER.warning("Field %s in class %s is not an instance of ConfigBase".formatted(option.getName(), category.getName()));
                continue;
            }

            addCommentPath(annotation.id(), value);
            options.add(value);
        }

        this.categories.add(new ConfigCategory(annotation.id(), options.build()));
        return this;
    }

    public void build() {
        this.handler = new ConfigHandler(this.modId, this.categories);
        this.guiSupplier = (parent) -> new ConfigGui(this.modId, this.modVersion, this.handler, parent);

        ModMenuPlugin.register(this.modId, this.guiSupplier);
    }

    private void addCommentPath(String category, ConfigBase<?> config) {
        ConfigCommentAccessor accessor = (ConfigCommentAccessor) config;
        if (accessor.simple_malilib$getRawComment() == null)
            accessor.simple_malilib$setCommentPath("%s.config.category.%s.%s".formatted(this.modId, category, config.getName()));
    }
}
