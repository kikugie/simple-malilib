package dev.kikugie.simple_malilib.test;

import dev.kikugie.simple_malilib.api.annotation.Category;
import dev.kikugie.simple_malilib.api.option.Options;
import fi.dy.masa.malilib.config.options.ConfigBoolean;

@Category(id = "test")
public class Config {
    public static final ConfigBoolean testBool = Options.bool("testOption", true).build();
}
