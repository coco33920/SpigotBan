package fr.coco.bungeeban.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Admin on 16/10/2016.
 */
public class HelpBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        Player p = (Player) sender;

        if(p.hasPermission("mod.ban") || p.hasPermission("mod.mute")){

            String msg1 = "§7--------§cHelp Nouveau BAN§7--------";
            String msg2 = "§c--> Ban Permanent : /ban <Joueur> perm <Raison>";
            String msg3 = "§c--> Ban Temporaire : /ban <Joueur> <temps ( chiffre )> <multiplicateur de temps : en dessous> <Raison>";
            String msg4 = "§c----> Ex : /ban coco33920 1 m Cheat";
            String msg5 = "§cMultiplicateur de temps : m = Minutes ; h = Heures ; d = Jours ; mo = Mois; y = Ans";
            String msg6 = "§7--------§cHelp Nouveau BAN§7--------";

            String[] strings = {msg1, msg2, msg3, msg4, msg5, msg6};

            String msg7 = "§7--------§cHelp Nouveau Mute§7--------";
            String msg8 = "§c--> Mute Permanent : /mute <Joueur> perm <Raison>";
            String msg9 = "§c--> Mute Temporaire : /mute <Joueur> <temps ( chiffre )> <multiplicateur de temps : en dessous> <Raison>";
            String msg10 = "§c----> Ex : /mute coco33920 1 m Insulte";
            String msg11 = "§cMultiplicateur de temps : m = Minutes ; h = Heures ; d = Jours ; mo = Mois; y = Ans";
            String msg12 = "§7--------§cHelp Nouveau Mute§7--------";

            String[] strings1 = {msg7, msg8, msg9, msg10, msg11, msg12};



            if(!(args.length == 1)){
                p.sendMessage("§cError : /banhelp <ban/mute>");
            }else{
                switch (args[0]){
                    case "ban":
                        p.sendMessage(strings);
                        break;
                    case "mute":
                        p.sendMessage(strings1);
                        break;
                    default:
                        p.performCommand("banhelp");
                        break;
                }


            }


        }else{
            p.sendMessage("§cError : You do not have permission to perform this command");
        }


        return false;
    }
}
