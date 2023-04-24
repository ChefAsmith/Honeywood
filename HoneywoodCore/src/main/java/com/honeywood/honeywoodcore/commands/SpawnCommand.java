package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.HoneywoodCore;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SpawnCommand implements CommandExecutor {

    private final HoneywoodCore plugin;

    public SpawnCommand(HoneywoodCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (!sender.isOp()) {
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be used by a player.");
                return true;
            }
            Player player = (Player) sender;
            Location location = player.getLocation();
            FileConfiguration config = plugin.getConfig();
            config.set("spawn.world", Objects.requireNonNull(location.getWorld()).getName());
            config.set("spawn.x", location.getX());
            config.set("spawn.y", location.getY());
            config.set("spawn.z", location.getZ());
            config.set("spawn.yaw", location.getYaw());
            config.set("spawn.pitch", location.getPitch());
            plugin.saveConfig();
            player.sendMessage("Spawn location set.");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be used by a player.");
                return true;
            }
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();
            if (config.contains("spawn.world")) {
                String worldName = config.getString("spawn.world");
                double x = config.getDouble("spawn.x");
                double y = config.getDouble("spawn.y");
                double z = config.getDouble("spawn.z");
                float yaw = (float) config.getDouble("spawn.yaw");
                float pitch = (float) config.getDouble("spawn.pitch");
                assert worldName != null;
                Location location = new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);
                player.teleport(location);
                player.sendMessage("Teleported to spawn location.");
            } else {
                player.sendMessage("Spawn location has not been set.");
            }
            return true;
        }
        return false;
    }

}
