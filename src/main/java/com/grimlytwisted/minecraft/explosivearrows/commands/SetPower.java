package com.grimlytwisted.minecraft.explosivearrows.commands;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetPower extends CommandSuccess implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        YamlConfiguration yaml = ExplosiveArrows.config.getYaml();

        if (args.length == 0 && sender instanceof Player) {

            sender.sendMessage(ChatColor.GREEN + "Power: " + yaml.getString(String.format(
                    "config.worlds.%s.power", ((Player) sender).getWorld().getUID()
            )));

            ExplosiveArrows.config.save();
            return true;

        } else if (args.length == 1) {

            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("grimlytwisted.explosivearrows.commands.setpower") || p.isOp()) {

                    try {
                        yaml.set(String.format("config.worlds.%s.power", p.getWorld().getUID()), Float.valueOf(args[0]));
                        success(p, "explosive power");
                    } catch (Exception e) {
                        error(p, "explosive power");
                        e.printStackTrace();
                    }

                    ExplosiveArrows.config.save();
                    return true;
                }
            }

            ExplosiveArrows.config.save();
            return false;

        } else if (args.length == 2) {

            try {

                World world = Bukkit.getWorld(args[1]);
                yaml.set(String.format("config.worlds.%s.power", world.getUID()), Float.valueOf(args[0]));

                if (sender instanceof Player) {
                    success(sender, "explosive power", args[1]);
                } else {
                    success(sender, "explosive power", args[1]);
                }

            } catch (Exception e) {

                if (sender instanceof Player) {
                    error(sender, "explosive power", args[1]);
                } else {
                    error(sender, "explosive power", args[1]);
                }

                e.printStackTrace();
            }

            ExplosiveArrows.config.save();
            return true;

        } else return false; // Only want 1 or 2 args
    }
}
