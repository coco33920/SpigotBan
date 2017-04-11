package fr.coco.bungeeban.sql.utils;

import com.avaje.ebean.annotation.Sql;
import fr.coco.bungeeban.sql.SqlUtils;
import org.bukkit.Bukkit;

/**
 * Created by coco33910 on 25/04/2016.
 * Utils
 */
public class Utils {


    public static void setupConfig(Config conf){
        if(!conf.getConfig().getKeys(false).contains("activate_database")){
            conf.getConfig().set("activate_database", false);
            conf.getConfig().set("first_launching", true);
            conf.getConfig().set("db.host", "default");
            conf.getConfig().set("db.username", "default");
            conf.getConfig().set("db.password", "default");
            conf.getConfig().set("db.database_name", "default");
            conf.save();
            Configuration.activate_database = false;
            Bukkit.getConsoleSender().sendMessage("§c[Ban] Veuillez paramettrer la base de donnée dans le fichier dbConfig.yml");
            Bukkit.getServer().shutdown();

        }
        if(conf.getConfig().getBoolean("first_launching")){
            if(conf.getConfig().getString("db.host").equals("default") || conf.getConfig().getString("db.username").equals("default")
                    || conf.getConfig().getString("db.password").equals("default") || conf.getConfig().getString("db.database_name").equals("default")){
                Bukkit.getConsoleSender().sendMessage("§c[Ban] Veuillez paramettrer la base de donnée dans le fichier dbConfig.yml");
                Bukkit.getServer().shutdown();
            }else{


                conf.getConfig().set("activate_database", true);
                conf.getConfig().set("first_launching", false);
                conf.save();
                String host = conf.getConfig().getString("db.host");
                String usrname = conf.getConfig().getString("db.username");
                String password = conf.getConfig().getString("db.password");
                String database_name = conf.getConfig().getString("db.database_name");

                Configuration.activate_database = true;
                Configuration.db_name = database_name;
                Configuration.host = host;
                Configuration.password = password;
                Configuration.username = usrname;



            }

        }else{
            String host = conf.getConfig().getString("db.host");
            String usrname = conf.getConfig().getString("db.username");
            String password = conf.getConfig().getString("db.password");
            String database_name = conf.getConfig().getString("db.database_name");

            Configuration.activate_database = true;
            Configuration.db_name = database_name;
            Configuration.host = host;
            Configuration.password = password;
            Configuration.username = usrname;
        }
    }

    public static void createTable(){

    }




}
