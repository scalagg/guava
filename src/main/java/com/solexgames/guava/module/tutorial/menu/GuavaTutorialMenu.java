package com.solexgames.guava.module.tutorial.menu;

import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

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

        return buttonMap;
    }
}
