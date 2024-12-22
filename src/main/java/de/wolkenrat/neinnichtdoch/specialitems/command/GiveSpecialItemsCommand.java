package de.wolkenrat.neinnichtdoch.specialitems.command;

import de.wolkenrat.neinnichtdoch.specialitems.item.JumpSpring;
import de.wolkenrat.neinnichtdoch.specialitems.item.LockFlute;
import de.wolkenrat.neinnichtdoch.specialitems.item.TimeManipulationClock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveSpecialItemsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Command kann nur durch einen Spieler ausgeführt werden.");
            return true;
        }

        Player player = (Player) sender;

        player.getInventory().addItem(LockFlute.create());
        player.getInventory().addItem(TimeManipulationClock.create());
        player.getInventory().addItem(JumpSpring.create());

        player.sendMessage("Items erhalten!");
        return true;
    }
}
