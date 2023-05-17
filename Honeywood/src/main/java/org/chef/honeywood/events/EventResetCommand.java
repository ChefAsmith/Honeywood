package org.chef.honeywood.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EventResetCommand implements CommandExecutor {
    private final EventSignTeleports eventSignTeleports;

    public EventResetCommand(EventSignTeleports eventSignTeleports) {
        this.eventSignTeleports = eventSignTeleports;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("honeywood.eventreset")) {
            eventSignTeleports.resetSigns();
            sender.sendMessage("§6§lHoneywood: §aEvent signs have been reset!");
        } else {
            sender.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: honeywood.eventreset");
        }
        return true;
    }
}