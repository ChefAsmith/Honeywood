package com.honeywood.honeywoodcore;

import com.honeywood.honeywoodcore.commands.*;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.Objects;

public class HoneywoodCore extends JavaPlugin implements Listener {
    private int clickCount = 0;
    private boolean clickLimitEnabled = true;

    public void toggleclicklimit() {
        clickLimitEnabled = !clickLimitEnabled;
    }

    public boolean isClickLimitEnabled() {
        return clickLimitEnabled;
    }

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false); // Do not replace existing file
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        getLogger().info("Config loaded: pollen_chance = " + getConfig().getDouble("pollen_chance"));

        Pollen pollenInstance = new Pollen(Material.YELLOW_DYE, 1); // Pass the required arguments
        pollenInstance.registerCustomItem(this);

        // Register custom items
        com.honeywood.honeywoodcore.jewelry.Jewelry jewelry = new com.honeywood.honeywoodcore.jewelry.Jewelry();
        jewelry.registerCustomItems(this);

        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("toggleclicklimit")).setExecutor(new ToggleClickLimitCommand(this));
        Objects.requireNonNull(getCommand("honeywoodreload")).setExecutor(new ReloadCommand(this, pollenInstance)); // Pass the required Pollen instance
        Objects.requireNonNull(getCommand("givenecklace")).setExecutor(new GiveNecklaceCommand());
        Objects.requireNonNull(getCommand("givearmband")).setExecutor(new GiveArmBandCommand());
        Objects.requireNonNull(getCommand("giveop")).setExecutor(new GiveOpCommand());



        PluginCommand givePollenCommand = getCommand("givepollen");
        if (givePollenCommand != null) {
            givePollenCommand.setExecutor(new GivePollenCommand(pollenInstance));
        } else {
            getLogger().warning("Failed to register GivePollenCommand");
        }

        PluginCommand giveNecklaceCommand = getCommand("givenecklace");
        if (giveNecklaceCommand != null) {
            giveNecklaceCommand.setExecutor((sender, command, label, args) -> {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§6§lHoneywood §r§7» §cThis command can only be run by players.");
                    return true;
                }

                Player player = (Player) sender;

                for (ItemStack necklace : com.honeywood.honeywoodcore.jewelry.Jewelry.getNecklaces()) {
                    player.getInventory().addItem(necklace);
                }

                player.sendMessage("§6§lHoneywood §r§7» §aYou have received all of the necklaces.");

                return true;
            });
        } else {
            getLogger().warning("Failed to register GiveNecklaceCommand");
        }

        PluginCommand giveArmBandCommand = getCommand("givearmband");
        if (giveArmBandCommand != null) {
            giveArmBandCommand.setExecutor((sender, command, label, args) -> {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§6§lHoneywood §r§7» §cThis command can only be run by players.");
                    return true;
                }

                Player player = (Player) sender;

                for (ItemStack armband : com.honeywood.honeywoodcore.jewelry.Jewelry.getArmBands()) {
                    player.getInventory().addItem(armband);
                }

                player.sendMessage("§6§lHoneywood §r§7» §aYou have received all of the arm bands.");

                return true;
            });
        } else {
            getLogger().warning("Failed to register GiveArmBandCommand");
        }

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> clickCount = 0, 0L, 20L);
    }


    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.BEE) {
            Player player = event.getPlayer();

            if (isClickLimitEnabled()) {
                clickCount++;
                if (clickCount > 12) {
                    player.sendMessage("§6§lHoneywood §r§7» §cSlow down your clicking!");
                    event.setCancelled(true);
                    getLogger().info("Player " + player.getName() + " clicked too fast! Current click count: " + clickCount);
                    clickCount = 0; // Reset the click count
                }
            }
        }
    }
}
