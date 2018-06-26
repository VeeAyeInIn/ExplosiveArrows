package com.grimlytwisted.minecraft.explosivearrows.commands;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Explosions extends CommandSuccess implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        YamlConfiguration yaml = ExplosiveArrows.config.getYaml();

        if(args.length == 1) { // Player World

            if(sender instanceof Player  && (sender.hasPermission(
                    "grimlytwisted.explosivearrows.commands.explosions") || sender.isOp()
            )) {

                Player p = (Player) sender;
                if(args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.explosive-arrows-enabled", p.getWorld().getUID()
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
                                "config.worlds.%s.explosive-arrows-enabled", p.getWorld().getUID()
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
                    "grimlytwisted.explosivearrows.commands.explosions") || sender.isOp()
            )) {

                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.explosive-arrows-enabled", p.getServer().getWorld(args[1]).getUID()
                        ), true);
                        success(p, "explosions", args[1]);
                    } catch (Exception e) {
                        error(p, "explosions", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;

                } else if (args[0].equalsIgnoreCase("false")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.explosive-arrows-enabled", p.getServer().getWorld(args[1]).getUID()
                        ), false);
                        success(p, "explosions", args[1]);
                    } catch (Exception e) {
                        error(p, "explosions", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
            } else {

                if (args[0].equalsIgnoreCase("true")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.explosive-arrows-enabled", sender.getServer().getWorld(args[1]).getUID()
                        ), true);
                        success(sender, "explosions", args[1]);
                    } catch (Exception e) {
                        error(sender, "explosions", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;

                } else if (args[0].equalsIgnoreCase("false")) {

                    try {
                        yaml.set(String.format(
                                "config.worlds.%s.explosive-arrows-enabled", sender.getServer().getWorld(args[1]).getUID()
                        ), false);
                        success(sender, "explosions", args[1]);
                    } catch (Exception e) {
                        error(sender, "explosions", args[1]);
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
            }
        }
        return false;
    }
}
