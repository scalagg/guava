package com.solexgames.guava;

import com.google.common.base.Supplier;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.mongodb.client.model.Filters;
import com.solexgames.guava.handler.ModuleHandler;
import com.solexgames.guava.handler.StorageHandler;
import com.solexgames.guava.module.GuavaModule;
import com.solexgames.guava.player.impl.CachedGuavaPlayer;
import com.solexgames.guava.player.impl.PersistentGuavaPlayer;
import com.solexgames.lib.acf.PaperCommandManager;
import com.solexgames.lib.commons.game.PlayerCache;
import com.solexgames.lib.commons.game.impl.BasicPlayerCache;
import me.lucko.helper.Events;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@Getter
public final class GuavaSpigotPlugin extends ExtendedJavaPlugin {

    @Getter
    private static GuavaSpigotPlugin instance;

    private ModuleHandler moduleHandler;
    private StorageHandler storageHandler;

    private PaperCommandManager commandManager;

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final PlayerCache<PersistentGuavaPlayer> persistentPlayerCache = new BasicPlayerCache<>();
    private final Cache<UUID, CachedGuavaPlayer> cachedPlayers = CacheBuilder.newBuilder()
            .expireAfterAccess(60L, TimeUnit.SECONDS)
            .removalListener(notification -> {
                this.getLogger().info("Removed " + notification.getKey() + " from the cache.");
            }).build(CacheLoader.asyncReloading(new CacheLoader<UUID, CachedGuavaPlayer>() {
                @Override
                public CachedGuavaPlayer load(@NotNull UUID key) {
                    return null;
                }
            }, this.executor));

    @Override
    public void enable() {
        instance = this;

        this.storageHandler = new StorageHandler();

        this.commandManager = new PaperCommandManager(this);
        this.commandManager.enableUnstableAPI("help");

        this.moduleHandler = new ModuleHandler(this);
        this.moduleHandler.scanAndRegisterModules("com.solexgames.guava.module");
    }

    @Override
    public void disable() {
        this.moduleHandler.getModules().forEach(GuavaModule::onDisable);
    }

    public void setupListeners() {
        Events.subscribe(AsyncPlayerPreLoginEvent.class).handler(event -> {
            final PersistentGuavaPlayer player = new PersistentGuavaPlayer(
                    event.getName(),
                    event.getUniqueId()
            );

            final CompletableFuture<Document> completableFuture = CompletableFuture.supplyAsync(() ->
                    this.storageHandler.getPlayerCollection().find(Filters.eq("uuid", event.getUniqueId().toString())).first()
            );

            player.load(completableFuture);

            this.persistentPlayerCache.getPlayerTypeMap().put(event.getUniqueId(), player);
        });

        Events.subscribe(PlayerQuitEvent.class).handler(playerQuitEvent -> {
            final PersistentGuavaPlayer player = this.persistentPlayerCache.getByPlayer(playerQuitEvent.getPlayer());

            if (player != null) {
                player.save();

                this.persistentPlayerCache.getPlayerTypeMap().remove(player.getUniqueId());
            }
        });
    }
}
