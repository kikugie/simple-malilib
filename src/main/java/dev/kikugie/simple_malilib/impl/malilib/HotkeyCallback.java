package dev.kikugie.simple_malilib.impl.malilib;

import dev.kikugie.simple_malilib.api.util.KeyActionResult;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

import java.util.function.Function;

public class HotkeyCallback implements IHotkeyCallback {
    private final IKeybind keybind;
    private final Function<KeyAction, KeyActionResult> action;

    public HotkeyCallback(IKeybind keybind, Function<KeyAction, KeyActionResult> action) {
        this.keybind = keybind;
        this.action = action;
    }

    @Override
    public boolean onKeyAction(KeyAction keyAction, IKeybind key) {
        if (key != this.keybind)
            return false;
        return this.action.apply(keyAction) != KeyActionResult.PASS;
    }
}
