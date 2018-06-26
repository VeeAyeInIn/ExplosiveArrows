package com.grimlytwisted.minecraft.explosivearrows.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class CommandSuccess {

    /**
     * @param cs the source of the command. Can be the console, a player, or some other executable source.
     * @param option what is being modified, i.e. explosive power of the bow.
     */
    void success(CommandSender cs, String option) {
        if(cs instanceof Player) {
            cs.sendMessage(ChatColor.GREEN + "Successfully changed the value of " + option + " in " + ((Player) cs).getWorld().getName());
        }
    }

    /**
     * @param cs the source of the command. Can be the console, a player, or some other executable source.
     * @param option what is being modified, i.e. explosive power of the bow.
     * @param world the world that has the options being modified.
     */
    void success(CommandSender cs, String option, String world) {

        if(cs instanceof Player) {
            cs.sendMessage(ChatColor.GREEN + "Successfully changed the value of " + option + " in " + world);
        } else {
            cs.getServer().getLogger().info(ChatColor.GREEN + "Successfully changed the value of " + option + " in " + world);
        }
    }

    /**
     * @param cs the source of the command. Can be the console, a player, or some other executable source.
     * @param option what is being modified, i.e. explosive power of the bow.
     */
    void error(CommandSender cs, String option) {
        if(cs instanceof Player) {
            cs.sendMessage(ChatColor.RED + "Error chaning the value of " + option + " in " + ((Player) cs).getWorld().getName());
        }
    }

    /**
     * @param cs the source of the command. Can be the console, a player, or some other executable source.
     * @param option what is being modified, i.e. explosive power of the bow.
     * @param world the world that has the options being modified.
     */
    void error(CommandSender cs, String option, String world) {

        if(cs instanceof Player) {
            cs.sendMessage(ChatColor.RED + "Error changing the value of " + option + " in " + world);
        } else {
            cs.getServer().getLogger().info(ChatColor.RED + "Error changing the value of " + option + " in " + world);
        }
    }
}
