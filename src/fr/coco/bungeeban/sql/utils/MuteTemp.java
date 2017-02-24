package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by coco33910 on 24/04/2016.
 * MuteTemp
 */
public class MuteTemp {


    private Player player;
    private String reason;
    private long TimeMillis;

    public MuteTemp(Player player, String reason, long TimeMillis) {
        this.player = player;
        this.reason = reason;
        this.TimeMillis = TimeMillis;
        String date = new SimpleDateFormat("dd/MM/yy à kk:mm:ss").format(new Date(System.currentTimeMillis() + TimeMillis));

        try {
            if (BungeeBan.getInstance().getDataBase().getConnection() == null)
                return;



            if(fr.coco.bungeeban.sql.utils.MuteUtils.getInstance().isMuted(player)) return;
            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO muteTemp " + "(UUID, NAME, TIME, REASON) VALUES (?, ?, ?, ?) ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());

            sql.setLong(3,System.currentTimeMillis() + TimeMillis);
            sql.setString(4, reason);
            sql.execute();
            sql.close();

            player.sendMessage("§cVous avez été muté pour " + reason.replace("&", "§") + " votre mute prendra fin le " + date);


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
