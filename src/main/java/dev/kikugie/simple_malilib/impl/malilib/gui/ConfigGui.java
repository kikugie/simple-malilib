package dev.kikugie.simple_malilib.impl.malilib.gui;

import dev.kikugie.simple_malilib.impl.malilib.config.ConfigCategory;
import dev.kikugie.simple_malilib.impl.malilib.config.ConfigHandler;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.client.gui.screen.Screen;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigGui extends GuiConfigsBase {
    private final List<Tab> tabs;
    private Tab selectedTab;

    public ConfigGui(String modId, String modVersion, ConfigHandler config, @Nullable Screen parent) {
        super(10, 50, modId, parent, "%s.config".formatted(modId), modVersion);
        Validate.validState(!config.categories.isEmpty(), "Config must have at least one category");

        this.tabs = initTabs(modId, config.categories);
        this.selectedTab = this.tabs.get(0);

        setParent(parent);
    }

    private static List<Tab> initTabs(String modId, List<ConfigCategory> categories) {
        ArrayList<Tab> tabs = new ArrayList<>(categories.size());
        for (ConfigCategory category : categories)
            tabs.add(new Tab(
                    StringUtils.translate("%s.config.category.%s".formatted(modId, category.id())),
                    category));
        return tabs;
    }

    private void setSelectedTab(Tab tab) {
        this.selectedTab = tab;
    }

    private int createButton(int x, int y, int width, Tab tab) {
        ButtonGeneric button = new ButtonGeneric(x, y, width, 20, tab.name());
        button.setEnabled(this.selectedTab != tab);
        this.addButton(button, new ButtonListener(tab, this));
        return button.getWidth() + 2;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.clearOptions();

        int x = 10;
        int y = 26;
        int rows = 1;

        for (Tab tab : this.tabs) {
            int width = getStringWidth(tab.name()) + 10;

            if (x >= this.width - width - 10) {
                x = 10;
                y += 22;
                rows++;
            }

            x += this.createButton(x, y, width, tab);
        }

        if (rows > 1) {
            int scrollbarPosition = Objects.requireNonNull(this.getListWidget()).getScrollbar().getValue();
            this.setListPosition(this.getListX(), 50 + (rows - 1) * 22);
            this.reCreateListWidget();
            this.getListWidget().getScrollbar().setValue(scrollbarPosition);
            this.getListWidget().refreshEntries();
        }
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        return ConfigOptionWrapper.createFor(this.selectedTab.category.options());
    }

    private record ButtonListener(Tab tab, ConfigGui parent) implements IButtonActionListener {
        @Override
        public void actionPerformedWithButton(ButtonBase button, int mouseButton) {
            this.parent.setSelectedTab(this.tab);
            this.parent.reCreateListWidget();
            Objects.requireNonNull(this.parent.getListWidget()).resetScrollbarPosition();
            this.parent.initGui();
        }
    }

    private record Tab(String name, ConfigCategory category) {}
}
