package org.chef.honeywood.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EventJoinCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public EventJoinCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World spawnWorld = plugin.getServer().getWorld("spawn"); // Replace "world" with the name of your spawn world
            if (spawnWorld != null) {
                Location spawnLocation = new Location(spawnWorld, -17.5, 76, -138.5);
                player.teleport(spawnLocation);
                player.sendMessage("§6§lHoneywood: §aYou have been teleported to the event location.");
            } else {
                player.sendMessage("The spawn world could not be found.");
            }
        } else {
            sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.This command can only be executed by players.");
        }
        return true;
    }
}
