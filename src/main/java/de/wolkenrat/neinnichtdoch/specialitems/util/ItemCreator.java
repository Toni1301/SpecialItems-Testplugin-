package de.wolkenrat.neinnichtdoch.specialitems.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreator {

    public static ItemStack createItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + name);
            List<String> loreList = new ArrayList<>();
            for (String line : lore) {
                loreList.add(ChatColor.GRAY + line);
            }
            meta.setLore(loreList);
            item.setItemMeta(meta);
        }

        return item;
    }
}
