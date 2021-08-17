package com.solexgames.guava.module.tutorial;

import com.solexgames.guava.GuavaSpigotPlugin;
import com.solexgames.guava.module.GuavaModule;
import com.solexgames.guava.module.tutorial.command.TutorialCommand;
import com.solexgames.lib.acf.BaseCommand;

import java.util.Collections;
import java.util.List;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

public class TutorialGuavaModule implements GuavaModule {

    @Override
    public void onEnable(GuavaSpigotPlugin plugin) {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public List<BaseCommand> getCommands() {
        return Collections.singletonList(
                new TutorialCommand()
        );
    }
}
