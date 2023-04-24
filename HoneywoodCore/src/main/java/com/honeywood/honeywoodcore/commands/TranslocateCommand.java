package com.honeywood.honeywoodcore.commands;

import com.honeywood.honeywoodcore.HoneywoodCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TranslocateCommand implements CommandExecutor {

    public TranslocateCommand(HoneywoodCore plugin) {
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (!player.getName().equals("ii_Bnx")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        int radius = 5; // change this to adjust the radius of the effect

        // Get all players within the radius of the command sender
        List<Player> nearbyPlayers = player.getWorld().getPlayers().stream()
                .filter(p -> p.getLocation().distance(player.getLocation()) <= radius)
                .collect(Collectors.toList());

        // If no players are nearby, do nothing
        if (nearbyPlayers.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No players found within " + radius + " blocks!");
            return true;
        }

        // Teleport all nearby players to a random dimension
        for (Player nearbyPlayer : nearbyPlayers) {
            // Skip the command sender
            if (nearbyPlayer == player) {
                continue;
            }
            org.bukkit.World.Environment[] dimensions = {org.bukkit.World.Environment.NORMAL, org.bukkit.World.Environment.NETHER, org.bukkit.World.Environment.THE_END};
            org.bukkit.World.Environment dimension = dimensions[new Random().nextInt(dimensions.length)];
            Location newLocation = nearbyPlayer.getWorld().getHighestBlockAt(nearbyPlayer.getLocation()).getLocation();
            newLocation.setWorld(nearbyPlayer.getServer().getWorlds().stream().filter(world -> world.getEnvironment() == dimension).findFirst().orElse(null));
            nearbyPlayer.teleport(newLocation);
            nearbyPlayer.sendMessage(ChatColor.GREEN + "You have been translocated to " + dimension.name() + "!");

            // Apply the slow falling effect to the player
            nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 3));
            nearbyPlayer.getWorld().spawnParticle(Particle.CLOUD, nearbyPlayer.getLocation(), 50, 1, 1, 1, 0.1);
        }

        player.sendMessage(ChatColor.GREEN + "Translocated " + nearbyPlayers.size() + " players to random dimensions!");
        return true;
    }
}
