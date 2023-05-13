package org.chef.honeywood.commands;

import org.chef.honeywood.Honeywood;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleClickLimitCommand implements CommandExecutor {
    private final Honeywood plugin;

    public ToggleClickLimitCommand(Honeywood plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("honeywood.toggleclicklimit")) {
            player.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: honeywood.toggleclicklimit");
            return true;
        }

        plugin.toggleclicklimit();

        if (plugin.isClickLimitEnabled()) {
            player.sendMessage("§6§lHoneywood: §rClick limit §2enabled.");
        } else {
            player.sendMessage("§6§lHoneywood: §rClick limit §4disabled.");
        }
        return true;
    }
}
