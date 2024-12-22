package de.wolkenrat.neinnichtdoch.specialitems.item;

import de.wolkenrat.neinnichtdoch.specialitems.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class JumpSpring implements Listener {

    public static ItemStack create() {
        return ItemCreator.createItem(Material.SLIME_BALL, "Sprungfeder", "Katapultiert dich nach vorne");
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Sprungfeder")) {
            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
            if (item.getAmount() == 1) {
                player.getInventory().removeItem(item); // Entfernt nur das Item, wenn es nur 1 Stück gibt
            } else {
                item.setAmount(item.getAmount() - 1); // Reduziert die Menge um 1
            }
        }
    }
}