package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by coco33910 on 02/03/2016.
 * Ban
 */
public class Ban {

    private Player player;
    private String reason;


    public Ban(Player player, String reason) {
        this.player = player;
        this.reason = reason;
        try {
            if (BungeeBan.getInstance().getDataBase().getConnection() == null)
                return;




            if(BanUtils.getInstance().isBanned(player)) return;
            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO ban " + "(UUID, NAME, TIME, REASON) VALUES (?, ?, ?, ?) ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());

            sql.setString(3, "permanent");
            sql.setString(4, reason);
            sql.execute();
            sql.close();
            World w = player.getWorld();
            w.spawnEntity(player.getLocation(), EntityType.LIGHTNING);
            w.spawnEntity(player.getLocation(), EntityType.LIGHTNING);
            w.spawnEntity(player.getLocation(), EntityType.LIGHTNING);
            for(Player pla : Bukkit.getServer().getOnlinePlayers()){
                pla.playSound(pla.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 10);
            }
            player.kickPlayer("\u00a7cVous avez \u00e9t\u00e9 banni d\u00e9finitivement du serveur \u00A72 vous pensez que vous ne devriez pas voir ce message  \u00A7bforum.advancedfight.fr \u00a7c Raison " + BanUtils.getInstance().getReasonPlayerBan(player));
            Bukkit.broadcastMessage("§f(§cModération§f) §e" + player.getName() + "§c a été bannis définitivement pour " + reason.replace("&", "§"));



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public String getReason() {
        return reason;
    }
}
