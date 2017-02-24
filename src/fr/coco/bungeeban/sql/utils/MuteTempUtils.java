package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by coco33910 on 24/04/2016.
 * MuteTempUtils
 */
public class MuteTempUtils {

    private static MuteTempUtils ourInstance = new MuteTempUtils();

    public static MuteTempUtils getInstance() {
        return ourInstance;
    }
    public int id;
    private PreparedStatement sts1;




    public void mutePlayerTime(Player player, String reason, int time) {
        try {
            if (BungeeBan.getInstance().getDataBase().isConnected() == false) {
                return;
            }

            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO 'muteTemp'" + "('UUID', 'NAME', 'TIME', 'REASON') VALUES (?, ?, ?, ?); ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());


            sql.setInt(3, time);
            sql.setString(4, reason);
            sql.execute();
            sql.close();


        } catch (SQLException e) {
            e.printStackTrace();
            BungeeBan.getInstance().getDataBase().disconnection();
        }
        BungeeBan.getInstance().getDataBase().disconnection();

    }


    public void unMutePlayer(OfflinePlayer player) {

        try {
            sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("DELETE FROM `advancedcoins`.`muteTemp` WHERE `muteTemp`.`UUID` = ?");

            sts1.setString(1, player.getUniqueId().toString());

            sts1.execute();







        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public boolean isTimeMuted(Player player) {
        if (BungeeBan.getInstance().getDataBase().getConnection() == null) {
            return false;
        }

        try {


            sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT * FROM muteTemp WHERE UUID = ?");


            sts1.setString(1, player.getUniqueId().toString());
            sts1.execute();



            ResultSet resultSet1 = sts1.getResultSet();


            while (resultSet1.next()) {
                sts1.close();
                return true;
            }
            sts1.close();
            return false;





        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            sts1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }

    public String getReasonPlayerMuteTemp(Player player) {
        String str = "";

        if (BungeeBan.getInstance().getDataBase().getConnection() == null) {
            return "";
        }

        try {
            sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT REASON FROM muteTemp WHERE UUID = ?");

            sts1.setString(1, player.getUniqueId().toString());

            sts1.execute();
            ResultSet resultSet1 = sts1.getResultSet();


            while (resultSet1.next())
                str = resultSet1.getString(1).replace("&", "§");



        }catch (SQLException e){
            e.printStackTrace();
        }



        return str;
    }
    public boolean isUnMuted(Player player) {
        boolean b = false;

        if (isTimeMuted(player)) {

            long lenombre = 0;
            long time = System.currentTimeMillis();
            try {
                sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT TIME FROM muteTemp WHERE UUID = ?");

                sts1.setString(1, player.getUniqueId().toString());

                sts1.execute();
                ResultSet resultSet1 = sts1.getResultSet();


                while (resultSet1.next()) {
                    lenombre = resultSet1.getLong(1);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (lenombre == time || lenombre < time) {
                return true;
            } else {
                return false;
            }


        } else {
            return false;
        }


    }
    public String getTimeBanned(Player player) {

        String str = "";
        if (isTimeMuted(player)) {

            long lenombre = 0;
            try {
                sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT TIME FROM muteTemp WHERE UUID = ?");

                sts1.setString(1, player.getUniqueId().toString());

                sts1.execute();
                ResultSet resultSet1 = sts1.getResultSet();


                while (resultSet1.next()) {
                    lenombre = resultSet1.getLong(1);
                }




            } catch (SQLException e) {
                e.printStackTrace();
            }

            str = new SimpleDateFormat("dd/MM/yy à hh:mm:ss").format(new Date(lenombre));

        }
        return str;
    }

    public MuteTempUtils(){

    }



}
