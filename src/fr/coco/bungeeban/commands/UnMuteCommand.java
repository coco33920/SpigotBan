package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanUtils;
import fr.coco.bungeeban.sql.utils.MuteTempUtils;
import fr.coco.bungeeban.sql.utils.MuteUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by coco33910 on 23/04/2016.
 * UnMuteCommand
 */
public class UnMuteCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        if(player.hasPermission("mod.unmute")){
            Player pl = Bukkit.getPlayer(args[0]);
            if(pl == null){
                return false;
            }else{
                if(!MuteUtils.getInstance().isMuted(pl) && !MuteTempUtils.getInstance().isTimeMuted(pl)){
                    player.sendMessage("§aCe joueur n'est pas mute");
                    return false;
                }
                MuteUtils.getInstance().unMutePlayer(pl);
                MuteTempUtils.getInstance().unMutePlayer(pl);
                player.sendMessage("§aUnmute de §e" + pl.getName() + "§a envoyé !");
                pl.sendMessage("§aVous avez été demute !");
                for (Player pla : Bukkit.getServer().getOnlinePlayers()){
                    if(pla.isOp())
                        pla.sendMessage("§f[§cModération§f] §c[UNMUTE] §6" + player.getName() +  ": §a" + pl.getName());
                }

            }
        }

        return false;
    }
}
