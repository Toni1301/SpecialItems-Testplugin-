package de.wolkenrat.neinnichtdoch.specialitems.item;

import de.wolkenrat.neinnichtdoch.specialitems.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class TimeManipulationClock implements Listener {

    public static ItemStack create() {
        return ItemCreator.createItem(Material.CLOCK, "Zeit-Manipulations-Uhr", "Verlangsamt Mobs im Umkreis");
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Zeit-Manipulations-Uhr")) {
            if (event.getAction().toString().contains("RIGHT_CLICK") && event.getClickedBlock() != null) {
                return;
            }
            List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
            for (Entity entity : nearbyEntities) {
                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1, false, false, true));
                }
            }
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 50);
            player.sendMessage("Alle Mobs wurden verlangsamt! (5 Sekunden)");
        }
    }
}
