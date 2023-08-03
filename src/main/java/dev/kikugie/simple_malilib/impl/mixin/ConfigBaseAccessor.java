package dev.kikugie.simple_malilib.impl.mixin;

import fi.dy.masa.malilib.config.options.ConfigBase;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//TODO: Probably has issues modifying `final` field
@Environment(EnvType.CLIENT)
@Mixin(value = ConfigBase.class, remap = false)
public interface ConfigBaseAccessor {
    @Accessor("prettyName")
    void setPrettyName(String prettyName);
}
