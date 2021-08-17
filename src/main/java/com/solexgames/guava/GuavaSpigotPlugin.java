package com.solexgames.guava;

import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@Getter
public final class GuavaSpigotPlugin extends JavaPlugin {

    @Getter
    private static GuavaSpigotPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
