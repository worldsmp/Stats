package com.theworldsmp.stats.commands;

import com.theworldsmp.stats.Stats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsCommand implements CommandExecutor {

    private String playerError = "There was an error collecting all of your stats. Please let an administrator know.";
    private String playerUsage = "Usage: /stats <general | mining | exploration | pvp>";

    public Object getStat(Player p, String stat) throws SQLException {
            ResultSet data = Stats.prepareStatement("SELECT " + stat + " FROM PLAYER_STATS WHERE UUID ='" + p.getUniqueId().toString() + "';").executeQuery();
            data.next();

            return data.getObject(stat);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to run the stats command.");
            return false;

        } else {
            Player p = (Player) sender;

            if (args.length == 0 || args[0].equalsIgnoreCase("general")) {

                p.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "------------" + ChatColor.AQUA + ChatColor.BOLD + " Stats (General) " + ChatColor.AQUA + ChatColor.STRIKETHROUGH + "------------");
                try {
                    p.sendMessage(ChatColor.AQUA + "Join Date: " + ChatColor.DARK_AQUA + getStat(p, "JOIN_DATE"));
                    p.sendMessage(ChatColor.AQUA + "Logins: " + ChatColor.DARK_AQUA + getStat(p, "JOINS"));
                } catch (SQLException e) {
                    p.sendMessage(ChatColor.RED + playerError);
                }
                p.sendMessage(ChatColor.GRAY + playerUsage);
                p.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------");

                } else if (args[0].equalsIgnoreCase("mining")) {

                p.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "-------------" + ChatColor.AQUA + ChatColor.BOLD + " Stats (Mining) " + ChatColor.AQUA + ChatColor.STRIKETHROUGH + "-------------");
                try {
                    p.sendMessage(ChatColor.AQUA + "Coal Mined: " + ChatColor.DARK_GRAY + getStat(p, "COAL_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Iron Mined: " + ChatColor.GRAY + getStat(p, "IRON_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Lapis Mined: " + ChatColor.BLUE + getStat(p, "LAPIS_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Gold Mined: " + ChatColor.GOLD + getStat(p, "GOLD_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Redstone Mined: " + ChatColor.RED + getStat(p, "REDSTONE_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Emeralds Mined: " + ChatColor.GREEN + getStat(p, "EMERALDS_MINED"));
                    p.sendMessage(ChatColor.AQUA + "Diamonds Mined: " + ChatColor.AQUA + getStat(p, "DIAMONDS_MINED"));
                } catch (SQLException e) {
                    p.sendMessage(ChatColor.RED + playerError);
                }
                p.sendMessage(ChatColor.GRAY + playerUsage);
                p.sendMessage(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------");

            }

        }
        return false;
    }
}
