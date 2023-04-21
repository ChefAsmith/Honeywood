package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.Pollen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GivePollenCommand implements CommandExecutor {

    private final Pollen pollenInstance;

    public GivePollenCommand(Pollen pollenInstance) {
        this.pollenInstance = pollenInstance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        // Check if the sender has permission to use the command
        if (!sender.hasPermission("honeywoodcore.givepollen")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        // Get all online players
        for (Player player : Bukkit.getOnlinePlayers()) {
            // Give the pollen item to the player
            player.getInventory().addItem(pollenInstance.getPollenItem());
        }

        // Send a message to the sender to confirm that the command has been executed
        sender.sendMessage(ChatColor.GREEN + "Pollen item has been given to all online players.");

        return true;
    }
}
