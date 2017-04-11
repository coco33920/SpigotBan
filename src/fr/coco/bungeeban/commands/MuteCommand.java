package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by coco33910 on 23/04/2016.
 * MuteCommand
 */
public class MuteCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


                        if (commandSender instanceof Player) {

                            Player player = (Player) commandSender;

                            if (player.hasPermission("mod.mute")) {

                                if (strings.length < 3) {
                                    player.sendMessage("§cError : /mute <Player> <time or permanent> [s, m, h, d, mo, y] <Reason>");
                                } else {
                                    Player target = Bukkit.getPlayer(strings[0]);
                                    if (target == null) {
                                        player.sendMessage("§cError : This player is offline");
                                        return false;
                                    }
                                    if (target.hasPermission("mod.nomute")) {
                                        player.sendMessage("§cError : You can't mute this player");
                                        return false;
                                    }
                                    if (MuteUtils.getInstance().isMuted(player) || MuteTempUtils.getInstance().isTimeMuted(player)) {
                                        player.sendMessage("§cError : This player is already muted");
                                    } else {

                                        if (strings[1].equalsIgnoreCase("permanent") || strings[1].equalsIgnoreCase("perma") || strings[1].equalsIgnoreCase("perm")) {

                                            StringBuilder str = new StringBuilder();
                                            for (int i = 2; strings.length > i; i++) {
                                                str.append(strings[i] + " ");

                                            }

                                            new Mute(target, str.toString());
                                            player.sendMessage("§aMute de §e" + target.getName() + " §aenvoyé ! ");
                                            target.sendMessage("§cModération §8» §a§oVous avez été muté par §e" + player.getName());
                                            target.sendMessage("§cRaison : " + str.toString().replace("&", "§") + " §cDurée : PERMANENTE ");
                                            for (Player pla : Bukkit.getServer().getOnlinePlayers()) {
                                                if (pla.isOp()) {
                                                    pla.sendMessage("§f[§cModération§f] §c[MUTE-Permanent] §2" + player.getName() + ": §6" + target.getName() + " » " + str.toString().replace("&", "§"));
                                                }
                                            }


                                        } else {

                                            if (strings.length < 4) {
                                                player.performCommand("mute");

                                            } else {
                                                String str = strings[2];
                                                int i = Integer.parseInt(strings[1]);
                                                long temps = 0;
                                                switch (str) {
                                                    case "y":
                                                        temps = i * 1000 * 60 * 60 * 24 * 30 * 365;
                                                        break;
                                                    case "mo":
                                                        temps = i * 30 * 24 * 60 * 60 * 1000;
                                                        break;
                                                    case "d":
                                                        temps = i * 24 * 60 * 60 * 1000;
                                                        break;
                                                    case "h":
                                                        temps = i * 60 * 60 * 1000;
                                                        break;
                                                    case "m":
                                                        temps = i * 60 * 1000;
                                                        break;
                                                    default:
                                                        player.performCommand("mute");
                                                        break;

                                                }
                                                StringBuilder lol = new StringBuilder();
                                                for (int i2 = 3; strings.length > i2; i2++) {
                                                    lol.append(strings[i2] + " ");

                                                }

                                                MuteTemp muteTemp =  new MuteTemp(target, lol.toString().replace("&", "§"), temps);
                                                player.sendMessage("§aMute de §e" + target.getName() + " §aenvoyé ! ");
                                                for (Player pla : Bukkit.getServer().getOnlinePlayers()) {
                                                    if (pla.isOp()) {
                                                        pla.sendMessage("§f[§cModération§f] §c[MUTE-Temporaire] §2" + player.getName() + ": §6" + target.getName() + " » " + muteTemp.getReason().replace("&", "§"));
                                                    }
                                                }


                                            }


                                        }
                                    }
                                }


            } else {
                player.sendMessage("§cError : You do not have permission to perform this command");
            }
        }
        return false;

    }
}
