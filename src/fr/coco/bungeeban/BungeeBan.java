package fr.coco.bungeeban;

import fr.coco.bungeeban.commands.*;
import fr.coco.bungeeban.event.PlayerPreJoined;
import fr.coco.bungeeban.event.PlayerSendMessageEvent;
import fr.coco.bungeeban.sql.SqlUtils;
import fr.coco.bungeeban.sql.utils.Config;
import fr.coco.bungeeban.sql.utils.Configuration;
import fr.coco.bungeeban.sql.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

/**
 * Created by coco33910 on 22/02/2016.
 * BungeeBan
 */
public class BungeeBan extends JavaPlugin {

    private static BungeeBan ourInstance;
    public static SqlUtils sqlUtils;
    private Connection connection;


    @Override
    public void onEnable() {
        //sqlUtils = new SqlUtils("localhost", "advancedcoins", "TtzXwUas5aXfXm9n", "advancedcoins");
        ourInstance = this;

        //sqlUtils.connection();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerPreJoined(), this);
        pm.registerEvents(new PlayerSendMessageEvent(), this);
        Config dbConfig = new Config("databaseConfiguration");
        Utils.setupConfig(dbConfig);
        sqlUtils = new SqlUtils(Configuration.host, Configuration.username, Configuration.password, Configuration.db_name);
        sqlUtils.connection();
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnBanCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new UnMuteCommand());
        getCommand("banhelp").setExecutor(new HelpBanCommand());


    }

    public static BungeeBan getInstance() {
        return ourInstance;
    }


    public SqlUtils getDataBase() {
        return sqlUtils;
    }


}
