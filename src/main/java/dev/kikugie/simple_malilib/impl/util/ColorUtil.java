package dev.kikugie.simple_malilib.impl.util;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.util.Color4f;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.Objects;

public class ColorUtil {
    private static int toInt(Formatting formatting) {
        Validate.validState(formatting.isColor(), "Formatting must be a color");
        return Objects.requireNonNull(formatting.getColorValue());
    }

    public static String getHex(int color) {
        if (color < 0)
            throw new IllegalArgumentException("Color must not be negative");
        return String.format("#%08X", color);
    }

    public static String getHex(Formatting formatting) {
        return getHex(toInt(formatting));
    }

    public static ImmutableList<Color4f> getColors(Iterable<Integer> values) {
        ImmutableList.Builder<Color4f> builder = new ImmutableList.Builder<>();
        for (int color : values)
            builder.add(Color4f.fromColor(color));
        return builder.build();
    }

    public static ImmutableList<Color4f> getFormattingColors(Iterable<Formatting> values) {
        ImmutableList.Builder<Color4f> builder = new ImmutableList.Builder<>();
        for (Formatting color : values)
            builder.add(Color4f.fromColor(toInt(color)));
        return builder.build();
    }
}
