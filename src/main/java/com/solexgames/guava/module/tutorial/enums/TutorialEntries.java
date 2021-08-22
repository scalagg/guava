package com.solexgames.guava.module.tutorial.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.List;

/**
 * @author GrowlyX
 * @since 8/18/2021
 */

@Getter
@RequiredArgsConstructor
public enum TutorialEntries {

    ;

    private final String name;
    private final Material material;

    private final List<String> description;

}
