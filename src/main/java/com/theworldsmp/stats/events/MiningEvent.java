package com.theworldsmp.stats.events;

import com.theworldsmp.stats.Stats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;

public class MiningEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) throws SQLException {
        Player p = e.getPlayer();

        switch (e.getBlock().getType()) {
            case DIAMOND_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET DIAMONDS_MINED = DIAMONDS_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case EMERALD_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET EMERALDS_MINED = EMERALDS_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case GOLD_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET GOLD_MINED = GOLD_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case IRON_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET IRON_MINED = IRON_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case REDSTONE_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET REDSTONE_MINED = REDSTONE_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case LAPIS_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET LAPIS_MINED = LAPIS_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
            case COAL_ORE:
                Stats.prepareStatement("UPDATE PLAYER_STATS SET COAL_MINED = COAL_MINED + 1 WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                break;
        }
    }
}
