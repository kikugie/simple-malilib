package dev.kikugie.simple_malilib.impl.malilib.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class ConfigHandler implements IConfigHandler {
    public final String configFile;
    public final List<ConfigCategory> categories;
    private final String modId;


    public ConfigHandler(String modId, List<ConfigCategory> categories) {
        this.modId = modId;
        this.categories = categories;

        this.configFile = modId + ".json";
    }

    @Override
    public void load() {
        File configFile = new File(FileUtils.getConfigDirectory(), this.configFile);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();

                for (ConfigCategory category : this.categories)
                    ConfigUtils.readConfigBase(root, category.id(), category.options());
            }
        }
    }

    @Override
    public void save() {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();

            for (ConfigCategory category : this.categories)
                ConfigUtils.writeConfigBase(root, category.id(), category.options());

            JsonUtils.writeJsonToFile(root, new File(dir, this.configFile));
        }
    }
}
