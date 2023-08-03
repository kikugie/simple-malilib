package dev.kikugie.simple_malilib.api.option;

import com.google.common.collect.ImmutableList;
import dev.kikugie.simple_malilib.impl.option.*;
import dev.kikugie.simple_malilib.impl.util.ColorUtil;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.util.Formatting;

@SuppressWarnings("unused")
public class Options {
    public static IntOption integer(String name, int value) {
        return new IntOption(name, value);
    }

    public static DoubleOption doubleOpt(String name, int value) {
        return new DoubleOption(name, value);
    }

    public static GenericOption<ConfigBoolean, Boolean> bool(String name, boolean value) {
        return new GenericOption<>(name, value, ConfigBoolean::new);
    }

    public static GenericOption<ConfigString, String> string(String name, String value) {
        return new GenericOption<>(name, value, ConfigString::new);
    }

    public static <C, T> GenericOption<ConfigStringList, ImmutableList<String>> stringList(String name, ImmutableList<String> values) {
        return new GenericOption<>(name, values, ConfigStringList::new);
    }

    public static GenericOption<ConfigColor, String> color(String name, int value) {
        return new GenericOption<>(name, ColorUtil.getHex(value), ConfigColor::new);
    }

    public static GenericOption<ConfigColor, String> color(String name, Color4f value) {
        return new GenericOption<>(name, value.toHexString(), ConfigColor::new);
    }

    public static GenericOption<ConfigColor, String> color(String name, Formatting value) {
        return new GenericOption<>(name, ColorUtil.getHex(value), ConfigColor::new);
    }

    public static GenericOption<ConfigColorList, ImmutableList<Color4f>> colorIntList(String name, Iterable<Integer> values) {
        return new GenericOption<>(name, ColorUtil.getColors(values), ConfigColorList::new);
    }

    public static GenericOption<ConfigColorList, ImmutableList<Color4f>> colorList(String name, ImmutableList<Color4f> values) {
        return new GenericOption<>(name, values, ConfigColorList::new);
    }

    public static GenericOption<ConfigColorList, ImmutableList<Color4f>> colorFormattingList(String name, Iterable<Formatting> values) {
        return new GenericOption<>(name, ColorUtil.getFormattingColors(values), ConfigColorList::new);
    }

    public static GenericOption<ConfigOptionList, IConfigOptionListEntry> optionList(String name, IConfigOptionListEntry value) {
        return new GenericOption<>(name, value, ConfigOptionList::new);
    }

    public static KeybindOption keybind(String name, String defaultValue) {
        return new KeybindOption(name, defaultValue);
    }

    public static KeybindBooleanOption keybindToggle(String name, boolean defaultValue, String keybind) {
        return new KeybindBooleanOption(name, defaultValue, keybind);
    }
}
