package com.grimlytwisted.minecraft.explosivearrows.listeners;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileContactListener implements Listener {

    @EventHandler
    public void onProjectileMakesContact(ProjectileHitEvent e) {

        if(e.getEntity() instanceof Arrow && e.getEntity().getShooter() instanceof Player) {

            Player shooter = (Player) e.getEntity().getShooter();
            Arrow arrow = (Arrow) e.getEntity();

            if(shooter.hasPermission("grimlytwisted.explosivearrows.arrows-can-explode") || shooter.isOp()) {

                try {

                    if(ExplosiveArrows.config.getYaml().getBoolean(String.format("config.worlds.%s.explosive-arrows-enabled",
                            shooter.getWorld().getUID()))) {

                        boolean terrainDamage = ExplosiveArrows.config.getYaml().getBoolean(String.format(
                                "config.worlds.%s.can-damage-terrain", shooter.getWorld().getUID()
                        ));

                        float power = (float) ExplosiveArrows.config.getYaml().getDouble(String.format(
                                "config.worlds.%s.power", shooter.getWorld().getUID()
                        ));

                        Location loc = arrow.getLocation();
                        arrow.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, false, terrainDamage);

                        arrow.remove();

                    }

                } catch (Exception ex) {
                    Bukkit.getServer().getLogger().warning("There was an error attempting to explode an arrow.\n" +
                            "Please make sure to check and see if the config is correctly validated!");
                }
            }
        }
    }
}
