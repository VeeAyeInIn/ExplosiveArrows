package com.grimlytwisted.minecraft.explosivearrows.commands;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TerrainDamage extends CommandSuccess implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        YamlConfiguration yaml = ExplosiveArrows.config.getYaml();

        /* If length is equal to 1, the command will assume
         * the player wishes to change the value for their
         * world, rather than a specific world.
         */
        if(args.length == 1) {

            /* Checks if the sender is a player, and if so, checks
             * if they have the necessary permissions. This will
             * return false otherwise as you cannot get the world
             * from the console.
             */
            if(sender instanceof Player && (sender.hasPermission(
                    "grimlytwisted.explosivearrows.commands.tdamage") || sender.isOp()
            )) {

                // Confirmed player so cast
                Player p = (Player) sender;
                if(args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", p.getWorld().getUID()
                        ), true);
                        success(p, "explosions");
                    } catch (Exception e) {
                        error(p, "explosions");
                    }

                    ExplosiveArrows.config.save();
                    return true;

                } else if(args[0].equalsIgnoreCase("false")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", p.getWorld().getUID()
                        ), false);
                        success(p, "explosions");
                    } catch (Exception e) {
                        error(p, "explosions");
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
                return false;
            }

        } else if (args.length == 2) { // Specified World

            if(sender instanceof Player  && (sender.hasPermission(
                    "grimlytwisted.explosivearrows.commands.tdamage") || sender.isOp()
            )) {

                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", p.getServer().getWorld(args[1]).getUID()
                        ), true);
                        success(p, "damageable terrain", args[1]);
                    } catch (Exception e) {
                        error(p, "damageable terrain", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;

                } else if (args[0].equalsIgnoreCase("false")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", p.getServer().getWorld(args[1]).getUID()
                        ), false);
                        success(p, "damageable terrain", args[1]);
                    } catch (Exception e) {
                        error(p, "damageable terrain", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
            } else {

                if (args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", sender.getServer().getWorld(args[1]).getUID()
                        ), true);
                        success(sender, "damageable terrain", args[1]);
                    } catch (Exception e) {
                        error(sender, "damageable terrain", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;

                } else if (args[0].equalsIgnoreCase("false")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.can-damage-terrain", sender.getServer().getWorld(args[1]).getUID()
                        ), false);
                        success(sender, "damageable terrain", args[1]);
                    } catch (Exception e) {
                        error(sender, "damageable terrain", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
            }
        }
        return false;
    }
}
