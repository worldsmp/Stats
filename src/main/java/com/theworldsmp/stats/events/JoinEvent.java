package com.theworldsmp.stats.events;

import com.theworldsmp.stats.Stats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;


public class JoinEvent implements Listener {

    @EventHandler
    public void onAdminJoin(PlayerJoinEvent e) {
        if (Stats.connected == false && e.getPlayer().isOp()) {
            e.getPlayer().sendMessage(ChatColor.RED + "[Stats] Database has failed to initialize.");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();

        try {
            ResultSet res = Stats.prepareStatement("SELECT COUNT(UUID) FROM PLAYER_STATS WHERE UUID = '" + p.getUniqueId().toString() + "';").executeQuery();
            res.next();

            if (res.getInt(1) == 0) { // if not in db
               Stats.prepareStatement("INSERT INTO PLAYER_STATS(UUID, JOIN_DATE, JOINS) VALUES ('" + p.getUniqueId().toString() + "', DEFAULT, 1);").executeUpdate();
            } else {
                Stats.prepareStatement("UPDATE PLAYER_STATS SET JOINS = JOINS + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
