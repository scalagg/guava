package com.solexgames.guava.player.impl;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.solexgames.guava.GuavaSpigotPlugin;
import com.solexgames.guava.model.PersistentModel;
import com.solexgames.guava.player.GuavaPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author GrowlyX
 * @since 8/9/2021
 */

@Getter
@RequiredArgsConstructor
public class PersistentGuavaPlayer extends GuavaPlayer implements PersistentModel<Document> {

    private final String name;
    private final UUID uniqueId;

    @Override
    public void load(CompletableFuture<Document> documentCompletableFuture) {
        documentCompletableFuture.whenComplete((document, throwable) -> {
            if (document == null) {
                this.save().whenComplete((unused, throwable1) -> {
                    if (throwable1 != null) {
                        throwable1.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public CompletableFuture<Void> save() {
        return CompletableFuture.runAsync(() -> {
            GuavaSpigotPlugin.getInstance().getStorageHandler().getPlayerCollection().replaceOne(
                    Filters.eq("uuid", this.uniqueId.toString()),
                    this.getDocument(), new ReplaceOptions().upsert(true)
            );
        });
    }

    public Player getBukkitEntity() {
        return Bukkit.getPlayer(this.uniqueId);
    }

    public Document getDocument() {
        final Document document = new Document("_id", this.uniqueId);

        document.put("uuid", this.uniqueId.toString());
//        document.put("keys", this.crateKeys);

        return document;
    }
}
