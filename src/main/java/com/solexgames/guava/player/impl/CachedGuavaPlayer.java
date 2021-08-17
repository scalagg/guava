package com.solexgames.guava.player.impl;

import com.solexgames.guava.player.GuavaPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@Getter
@RequiredArgsConstructor
public class CachedGuavaPlayer extends GuavaPlayer {

    private final String name;
    private final UUID uniqueId;

}
