package me.hapyl.mmu3.feature;

import me.hapyl.mmu3.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class Feature {

    private final Main mmu3plugin;
    private String name;
    private String description;

    private boolean enabled;

    public Feature(Main mmu3plugin) {
        this.mmu3plugin = mmu3plugin;
        if (isListener()) {
            mmu3plugin.getServer().getPluginManager().registerEvents((Listener) this, mmu3plugin);
        }

        this.enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    protected void registerRunnable(long delay) {
        if (this instanceof Runnable runnable) {
            Bukkit.getScheduler().runTaskTimer(mmu3plugin, runnable, 0L, delay);
        }
    }

    protected final void runTaskLater(Runnable runnable, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskLater(mmu3plugin, delay);
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean isListener() {
        return this instanceof Listener;
    }

    public final Main getPlugin() {
        return mmu3plugin;
    }
}
