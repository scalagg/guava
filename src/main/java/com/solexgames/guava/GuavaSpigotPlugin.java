package com.solexgames.guava;

import com.solexgames.guava.handler.ModuleHandler;
import com.solexgames.guava.module.GuavaModule;
import com.solexgames.lib.acf.PaperCommandManager;
import com.solexgames.lib.commons.processor.AcfCommandProcessor;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@Getter
public final class GuavaSpigotPlugin extends ExtendedJavaPlugin {

    @Getter
    private static GuavaSpigotPlugin instance;

    private ModuleHandler moduleHandler;
    private PaperCommandManager commandManager;

    @Override
    public void enable() {
        instance = this;

        this.commandManager = new PaperCommandManager(this);
        this.commandManager.enableUnstableAPI("help");

        this.moduleHandler = new ModuleHandler(this);
        this.moduleHandler.scanAndRegisterModules("com.solexgames.guava.module");
    }

    @Override
    public void disable() {
        this.moduleHandler.getModules().forEach(GuavaModule::onDisable);
    }
}
