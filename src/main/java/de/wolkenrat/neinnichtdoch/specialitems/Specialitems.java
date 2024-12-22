package de.wolkenrat.neinnichtdoch.specialitems;

import de.wolkenrat.neinnichtdoch.specialitems.command.GiveSpecialItemsCommand;
import de.wolkenrat.neinnichtdoch.specialitems.item.JumpSpring;
import de.wolkenrat.neinnichtdoch.specialitems.item.LockFlute;
import de.wolkenrat.neinnichtdoch.specialitems.item.TimeManipulationClock;
import org.bukkit.plugin.java.JavaPlugin;

public final class Specialitems extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("givespecialitems").setExecutor(new GiveSpecialItemsCommand());

        getServer().getPluginManager().registerEvents(new LockFlute(), this);
        getServer().getPluginManager().registerEvents(new TimeManipulationClock(), this);
        getServer().getPluginManager().registerEvents(new JumpSpring(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
