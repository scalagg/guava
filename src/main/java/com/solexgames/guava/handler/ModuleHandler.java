package com.solexgames.guava.handler;

import com.solexgames.core.util.BukkitUtil;
import com.solexgames.guava.GuavaSpigotPlugin;
import com.solexgames.guava.module.GuavaModule;
import com.solexgames.kotlin.GuavaKotlinModule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GrowlyX
 * @since 8/17/2021
 */

@RequiredArgsConstructor
public class ModuleHandler {

    private final GuavaSpigotPlugin plugin;

    private final Map<Class<? extends GuavaModule>, GuavaModule> moduleMap = new HashMap<>();

    public void scanAndRegisterModules(String packageName) {
        BukkitUtil.getClassesInPackage(this.plugin, packageName).forEach(aClass -> {
            if (aClass.isAssignableFrom(GuavaModule.class)) {
                this.attemptToRegister(aClass);
            }
        });
    }

    @SneakyThrows
    private void attemptToRegister(Class<?> aClass) {
        final Object object = aClass.newInstance();

        this.registerModule((GuavaModule) object);
    }

    public void registerModule(GuavaModule module) {
        module.onEnable(this.plugin);
        module.getCommands().forEach(this.plugin.getCommandManager()::registerCommand);

        this.moduleMap.put(module.getClass(), module);
    }

    public GuavaModule getModule(Class<? extends GuavaModule> clazz) {
        return this.moduleMap.getOrDefault(clazz, null);
    }

    public List<GuavaModule> getModules() {
        return new ArrayList<>(this.moduleMap.values());
    }
}
