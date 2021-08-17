package com.solexgames.guava.module;

import com.solexgames.guava.GuavaSpigotPlugin;
import com.solexgames.lib.acf.BaseCommand;

import java.util.List;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

public interface GuavaModule {

    void onEnable(GuavaSpigotPlugin plugin);

    void onDisable();

    List<BaseCommand> getCommands();

}
