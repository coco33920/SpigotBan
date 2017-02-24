package fr.coco.bungeeban.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by coco33910 on 25/04/2016.
 * SonCommand
 */
public class SonCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("son")){




        }
        return false;
    }
}
