package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by colin on 05/04/2017.
 */
public class Config {

    private File file;
    private FileConfiguration fileConfig;
    private File folder;

    public Config(String name){

        folder  = BungeeBan.getInstance().getDataFolder();
        if(!folder.exists()){
            folder.mkdir();
        }

        file = new File(folder, name+".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileConfig = YamlConfiguration.loadConfiguration(file);

    }


    public void save(){
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(){
        return fileConfig;
    }


}
