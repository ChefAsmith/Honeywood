package org.chef.honeywood.commands;

import org.chef.honeywood.Honeywood;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCommand implements CommandExecutor {

    public ClearChatCommand(Honeywood plugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("staff.clearchat")) {
                player.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: staff.clearchat");
                return true;
            }
        }

        // Clear the chat for all players on the server
        for (int i = 0; i < 500; i++) {
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                onlinePlayer.sendMessage("");
            }
        }

        // Notify players that the chat has been cleared
        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "The chat has been cleared by a staff member.");

        return true;
    }
}
