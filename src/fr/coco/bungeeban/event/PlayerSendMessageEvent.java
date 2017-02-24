package fr.coco.bungeeban.event;

import fr.coco.bungeeban.sql.utils.MuteTempUtils;
import fr.coco.bungeeban.sql.utils.MuteUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by coco33910 on 23/04/2016.
 * PlayerSendMessageEvent
 */
public class PlayerSendMessageEvent implements Listener {

    @EventHandler
    public void playerSendMessageEvent(AsyncPlayerChatEvent e) {

        String msg1 = "§cModération §8» §a§oImpossible de parler vous avez été muté.";
        String msg2 = "§cRaison : ";
        Player player = e.getPlayer();

            if(MuteUtils.getInstance().isMuted(player)){
                e.setCancelled(true);
                player.sendMessage(msg1);
                player.sendMessage(msg2 + MuteUtils.getInstance().getReasonPlayerMute(e.getPlayer()) +  " Fin : PERMANENT");
            }

        if(MuteTempUtils.getInstance().isTimeMuted(player)){
            if(MuteTempUtils.getInstance().isUnMuted(player)){
                MuteTempUtils.getInstance().unMutePlayer(player);
            }else{
                ItemStack stack = new ItemStack(Material.DIAMOND);
                ItemMeta s = stack.getItemMeta();
                e.setCancelled(true);
                player.sendMessage(msg1);
                player.sendMessage(msg2 + MuteTempUtils.getInstance().getReasonPlayerMuteTemp(player) + " Fin : " + MuteTempUtils.getInstance().getTimeBanned(player));
            }
        }



    }


}
