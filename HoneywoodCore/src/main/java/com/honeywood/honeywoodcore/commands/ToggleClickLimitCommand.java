package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.HoneywoodCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleClickLimitCommand implements CommandExecutor {
    private final HoneywoodCore plugin;

    public ToggleClickLimitCommand(HoneywoodCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;
        plugin.toggleclicklimit();

        if (plugin.isClickLimitEnabled()) {
            player.sendMessage("§6§lClick limit §2enabled.");
        } else {
            player.sendMessage("§6§lClick limit §4disabled.");
        }
        return true;
    }
}
