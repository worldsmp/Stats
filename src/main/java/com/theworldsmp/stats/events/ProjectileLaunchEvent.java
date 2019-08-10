package com.theworldsmp.stats.events;

import com.theworldsmp.stats.Stats;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.SQLException;

public class ProjectileLaunchEvent implements Listener {

    @EventHandler
    public void onPearlThrow(org.bukkit.event.entity.ProjectileLaunchEvent e) throws SQLException {
        Projectile projectile = e.getEntity();
        Entity thrower = (Entity) projectile.getShooter();

        if(thrower instanceof Player && projectile instanceof EnderPearl) {
            Player p = (Player) thrower;
            Stats.prepareStatement("UPDATE PLAYER_STATS SET ENDERPEARLS_THROWN = ENDERPEARLS_THROWN + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
        }
    }
}
