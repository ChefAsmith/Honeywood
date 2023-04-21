package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.jewelery.Jewelery;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveArmBandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("honeywoodcore.givearmband")) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        for (ItemStack armband : Jewelery.getArmBands()) {
            player.getInventory().addItem(armband);
        }
        player.sendMessage("You have been given all the arm bands!");
        return true;
    }
}
