package com.grimlytwisted.minecraft.explosivearrows.listeners;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldInitListener implements Listener {

    @EventHandler
    public void onWorldInit(WorldInitEvent e) {

        World world = e.getWorld();
        YamlConfiguration yaml = ExplosiveArrows.config.getYaml();

        if(ExplosiveArrows.debug.isRunning()) {
            Bukkit.getServer().getLogger().info(world.getName() + " loaded.");
        }

        if(yaml.contains("config.worlds." + world.getUID())) {

            yaml.set(String.format("config.worlds.%s.world-name", world.getUID()), world.getName());

            if(ExplosiveArrows.debug.isRunning()) {
                Bukkit.getServer().getLogger().info(world.getName() + " has been renamed in config.yml");
            }

        } else {

            String section = "config.worlds." + world.getUID();

            yaml.createSection(section);
            yaml.createSection(section + ".world-name");
            yaml.createSection(section + ".explosive-arrows-enabled");
            yaml.createSection(section + ".can-damage-terrain");
            yaml.createSection(section + ".power");

            yaml.set(section + ".world-name", world.getName());
            yaml.set(section + ".explosive-arrows-enabled", false);
            yaml.set(section + ".can-damage-terrain", false);
            yaml.set(section + ".power", 1.0f);

            ExplosiveArrows.config.save();

            if(ExplosiveArrows.debug.isRunning()) {
                Bukkit.getServer().getLogger().info(world.getName() + " has been added to [config.worlds]");
            }
        }
    }
}
