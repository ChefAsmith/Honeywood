package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.HoneywoodCore;
import com.honeywood.honeywoodcore.Pollen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final HoneywoodCore plugin;
    private final Pollen pollen;

    public ReloadCommand(HoneywoodCore plugin, Pollen pollen) {
        this.plugin = plugin;
        this.pollen = pollen;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("honeywoodcore.reload")) {
            player.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        plugin.reloadConfig();
        pollen.pollenChance = plugin.getConfig().getInt("pollen_chance", 1000);
        int pollenChance = pollen.getChance(); // get the value of pollenChance from the Pollen instance
        player.sendMessage("§aHoneywoodCore configuration reloaded!");

        return true;
    }
}
