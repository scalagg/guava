package com.solexgames.guava.module.tutorial.menu;

import com.solexgames.core.util.builder.ItemBuilder;
import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import com.solexgames.guava.module.tutorial.enums.TutorialEntries;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

public class GuavaTutorialMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "How to play";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger();

        for (final TutorialEntries value : TutorialEntries.values()) {
            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(value.getMaterial())
                    .setDisplayName(ChatColor.GREEN + value.getName())
                    .addLore(value.getDescription())
                    .toButton()
            );
        }

        return buttonMap;
    }
}
