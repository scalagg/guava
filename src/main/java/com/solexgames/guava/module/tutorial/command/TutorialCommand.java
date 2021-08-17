package com.solexgames.guava.module.tutorial.command;

import com.solexgames.guava.module.tutorial.menu.GuavaTutorialMenu;
import com.solexgames.lib.acf.BaseCommand;
import com.solexgames.lib.acf.annotation.CommandAlias;
import com.solexgames.lib.acf.annotation.Default;
import org.bukkit.entity.Player;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@CommandAlias("tutorial|howtoplay")
public class TutorialCommand extends BaseCommand {

    @Default
    public void onDefault(Player player) {
        new GuavaTutorialMenu().openMenu(player);
    }
}
