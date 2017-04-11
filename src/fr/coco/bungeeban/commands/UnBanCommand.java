package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanTemp;
import fr.coco.bungeeban.sql.utils.BanTempUtils;
import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
* Created by coco33910 on 22/04/2016.
* UnBanCommand
        */
public class UnBanCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        if(player.hasPermission("mod.unban")){
            OfflinePlayer pl = Bukkit.getOfflinePlayer(args[0]);
            if(pl == null){
                return false;
            }else{
                if(!BanUtils.getInstance().isBanned(pl.getUniqueId()) && !BanTempUtils.getInstance().isTempBanned(pl.getUniqueId())){
                    player.sendMessage("§aCe joueur n'est pas banni");
                    return false;
                }
                BanUtils.getInstance().unBanPlayer(pl);
                player.sendMessage("§aUnban de §e" + pl.getName() + "§a envoyé !");
                BanTempUtils.getInstance().unBanPlayer(pl);
                for (Player pla : Bukkit.getOnlinePlayers()) {
                    if (pla.isOp())
                        pla.sendMessage("§f[§cModération§f] §c[UNBAN] §6" + player.getName() + ": §a" + pl.getName());

                }

            }
        }

        return false;
    }
}
