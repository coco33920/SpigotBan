package fr.coco.bungeeban.event;

import fr.coco.bungeeban.sql.utils.BanTemp;
import fr.coco.bungeeban.sql.utils.BanTempUtils;
import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by coco33910 on 22/04/2016.
        * PlayerPreJoined
        */
public class PlayerPreJoined implements Listener {

    @EventHandler
    public void playerPreJoined(PlayerLoginEvent e){
        Player player = e.getPlayer();
        System.out.println("DEBUG 1");
        if(BanUtils.getInstance().isBanned(player)){
            System.out.println("DEBUG 2");
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            e.setKickMessage("§cVous avez été bannis définitivement pour "+BanUtils.getInstance().getReasonPlayerBan(player));
        }
        if(BanTempUtils.getInstance().isTempBanned(player)){
            if(BanTempUtils.getInstance().isUnbanned(player)){
                BanTempUtils.getInstance().unBanPlayer(player);
            }else{
                e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
                e.setKickMessage("§cVous avez été bannis temporairement pour "+BanTempUtils.getInstance().getReasonPlayerBan(player) + " §cjusqu'au " + BanTempUtils.getInstance().getTimeBanned(player));

            }
        }
    }


}
