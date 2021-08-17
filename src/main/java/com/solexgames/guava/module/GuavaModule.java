package com.solexgames.guava.module;

import com.solexgames.guava.GuavaSpigotPlugin;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

public interface GuavaModule {

    void onEnable(GuavaSpigotPlugin plugin);

    void onDisable();

}
