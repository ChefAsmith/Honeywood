package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.HoneywoodCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TrashCommand implements CommandExecutor, Listener {

    private final Inventory trashInventory;

    public TrashCommand(HoneywoodCore plugin) {

        // Create the trash inventory
        trashInventory = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Trash");

        // Register this listener with the Bukkit event manager
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.GREEN + "Opening trash inventory...");
        player.openInventory(trashInventory);
        return true;
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Check if the closed inventory is the trash inventory
        if (event.getInventory().equals(trashInventory)) {
            // Delete all items in the inventory
            for (ItemStack item : trashInventory.getContents()) {
                if (item != null && item.getType() != Material.AIR) {
                    trashInventory.remove(item);
                }
            }
            event.getPlayer().sendMessage(ChatColor.GREEN + "All items in the trash inventory have been deleted.");
        }
    }
}
