package de.wolkenrat.neinnichtdoch.specialitems.item;

import de.wolkenrat.neinnichtdoch.specialitems.Specialitems;
import de.wolkenrat.neinnichtdoch.specialitems.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class LockFlute implements Listener {

    // HashMap für den Cooldown jedes Spielers
    private final HashMap<Player, Long> cooldowns = new HashMap<>();
    private final HashMap<Player, BukkitRunnable> cooldownTasks = new HashMap<>();

    public static ItemStack create() {
        return ItemCreator.createItem(Material.STICK, "Lockflöte", "Spielt zufällige Mobgeräusche");
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();


        if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Lockflöte")) {
            if (event.getAction().toString().contains("RIGHT_CLICK") && event.getClickedBlock() != null) {

                return;
            }

            long currentTime = System.currentTimeMillis();

            if (cooldowns.containsKey(player) && currentTime - cooldowns.get(player) < 5000) {
                long remainingTime = 5 - (currentTime - cooldowns.get(player)) / 1000;
                player.sendMessage("Du musst noch " + remainingTime + " Sekunden warten, bevor du die Lockflöte wieder benutzen kannst.");
                return;
            }

            Random random = new Random();
            Sound[] mobSounds = {
                    Sound.ENTITY_COW_AMBIENT,
                    Sound.ENTITY_SHEEP_AMBIENT,
                    Sound.ENTITY_ZOMBIE_AMBIENT,
                    Sound.ENTITY_SKELETON_AMBIENT,
                    Sound.ENTITY_SPIDER_AMBIENT,
                    Sound.ENTITY_CREEPER_PRIMED,
                    Sound.ENTITY_ENDERMAN_AMBIENT,
                    Sound.ENTITY_WOLF_AMBIENT,
                    Sound.ENTITY_CAT_AMBIENT,
                    Sound.ENTITY_CHICKEN_AMBIENT,
                    Sound.ENTITY_PIG_AMBIENT,
                    Sound.ENTITY_RABBIT_AMBIENT,
                    Sound.ENTITY_HORSE_AMBIENT,
                    Sound.ENTITY_LLAMA_AMBIENT,
                    Sound.ENTITY_PANDA_AMBIENT
            };

            player.playSound(player.getLocation(), mobSounds[random.nextInt(mobSounds.length)], 1.0f, 1.0f);

            cooldowns.put(player, currentTime);

            if (cooldownTasks.containsKey(player)) {
                cooldownTasks.get(player).cancel();
            }

            BukkitRunnable cooldownTask = new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("Die Lockflöte kann wieder benutzt werden!");
                }
            };

            cooldownTasks.put(player, cooldownTask);
            cooldownTask.runTaskLater(Specialitems.getPlugin(Specialitems.class), 100L); // 5 Sekunden Cooldown
        }
    }
}
