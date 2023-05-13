package org.chef.honeywood.commands;

import org.chef.honeywood.Honeywood;
import org.chef.honeywood.pollen.Pollen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final Honeywood plugin;
    private final Pollen pollen;

    public ReloadCommand(Honeywood plugin, Pollen pollen) {
        this.plugin = plugin;
        this.pollen = pollen;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("honeywood.reload")) {
            player.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: honeywood.reload");
            return true;
        }

        plugin.reloadConfig();
        pollen.pollenChance = plugin.getConfig().getInt("pollen_chance", 1000);
        int pollenChance = pollen.getChance(); // get the value of pollenChance from the Pollen instance
        player.sendMessage("§6§lHoneywood: §rconfiguration reloaded!");

        return true;
    }
}
