package org.chef.honeywood.commands;

import org.chef.honeywood.Honeywood;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NoPluginsCommand implements CommandExecutor {

    public NoPluginsCommand(Honeywood plugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pl") || cmd.getName().equalsIgnoreCase("plugins")) {
            sender.sendMessage("Plugins (1): §aHoneywood");
            return true;
        }
        return false;
    }

}
