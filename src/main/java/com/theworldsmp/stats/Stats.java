package com.theworldsmp.stats;

import com.theworldsmp.stats.commands.StatsCommand;
import com.theworldsmp.stats.events.MiningEvent;
import com.theworldsmp.stats.events.JoinEvent;
import com.theworldsmp.stats.events.ProjectileLaunchEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Stats extends JavaPlugin implements Listener {

    private static Connection connection;
    public static boolean connected = false;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MiningEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileLaunchEvent(), this);
        getCommand("stats").setExecutor(new StatsCommand());

        try {
            initDatabase();
            this.getServer().getLogger().info("[Stats] Database has successfully connected and initialized.");
            connected = true;
        } catch (SQLException e) {
            this.getServer().getLogger().severe("[Stats] Database has failed to initialize.");
            e.printStackTrace();
        }
    }

    private void initDatabase() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        connection = DriverManager.getConnection("jdbc:mysql://" +
                DBInfo.host + ":" +
                DBInfo.port + "/" +
                DBInfo.database,
                DBInfo.username,
                DBInfo.password);
    }

    public static PreparedStatement prepareStatement(String query) {
       PreparedStatement ps = null;
       try {
            ps = connection.prepareStatement(query);
       } catch (SQLException e) {
            e.printStackTrace();
       }

       return ps;
    }

}
