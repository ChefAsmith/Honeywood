package org.chef.honeywood.events;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.chef.honeywood.Honeywood;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EventSignTeleports implements Listener {

    private final Honeywood plugin;

    private final Logger logger;

    private final Map<Location, Boolean> signStates = new HashMap<>();
    private final Map<Location, Pair<Location, String>> signTeleportLocations = new HashMap<>();

    public EventSignTeleports(Honeywood plugin) {
        this.logger = plugin.getLogger();
        this.plugin = plugin;

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -18, 76, -123), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 40, 64, -224), "1"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -23, 76, -125), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 100, 64, -223), "2"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -27, 76, -128), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 170, 65, -212), "3"));
        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -28, 76, -129), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 170, 65, -212), "3"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -31, 76, -133), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 237, 65, -144), "4"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -33, 76, -139), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 250, 64, -74), "5"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -31, 76, -144), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 250, 64, -14), "6"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -28, 76, -148), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 238, 65, 74), "7"));
        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -27, 76, -149), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 238, 65, 74), "7"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -23, 76, -152), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 186, 65, 125), "8"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -18, 76, -154), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 100, 64, 136), "9"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -13, 76, -152), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), 40, 64, 136), "10"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -9, 76, -149), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -30, 65, 124), "11"));
        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -8, 76, -148), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -30, 65, 124), "11"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -5, 76, -144), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -97, 65, 56), "12"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -3, 76, -139), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -109, 64, -14), "13"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -5, 76, -133), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -109, 64, -74), "14"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -7, 76, -129), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -98, 65, -161), "15"));
        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -8, 76, -128), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -98, 65, -161), "15"));

        signTeleportLocations.put(new Location(plugin.getServer().getWorld("spawn"), -13, 76, -125), ImmutablePair.of(new Location(plugin.getServer().getWorld("spawn"), -46, 65, -213), "16"));
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && clickedBlock != null && clickedBlock.getType() == Material.OAK_WALL_SIGN) {
            Sign sign = (Sign) clickedBlock.getState();
            Location signLocation = clickedBlock.getLocation();

            if (signTeleportLocations.containsKey(signLocation) && !signStates.getOrDefault(signLocation, false)) {
                // Teleport the player and update the sign state
                player.sendMessage("§6§lHoneywood: §aTeleporting you to your plot.");
                Pair<Location, String> teleportLocation = signTeleportLocations.get(signLocation);
                String plotNumber = teleportLocation.getRight();
                player.teleport(teleportLocation.getLeft());
                signStates.put(signLocation, true);

                sign.setLine(0, ChatColor.RED + "---------------");
                sign.setLine(1, ChatColor.RED + "PLOT: " + plotNumber);
                sign.setLine(2, ChatColor.RED + player.getName());
                sign.setLine(3, ChatColor.RED + "---------------");
                Bukkit.getScheduler().runTaskLater(plugin, () -> sign.update(), 4L);

                // Update the state of the other sign with the same plot number
                for (Map.Entry<Location, Pair<Location, String>> entry : signTeleportLocations.entrySet()) {
                    Location loc = entry.getKey();
                    if (loc.equals(signLocation)) {
                        continue; // Skip this sign since it's already been claimed
                    }
                    Pair<Location, String> pair = entry.getValue();
                    if (pair.getRight().equals(plotNumber)) {
                        signStates.put(loc, true);
                        Sign otherSign = (Sign) loc.getBlock().getState();
                        otherSign.setLine(0, ChatColor.RED + "---------------");
                        otherSign.setLine(1, ChatColor.RED + "PLOT: " + plotNumber);
                        otherSign.setLine(2, ChatColor.RED + player.getName());
                        otherSign.setLine(3, ChatColor.RED + "---------------");
                        Bukkit.getScheduler().runTaskLater(plugin, () -> otherSign.update(), 4L);
                    }
                }
            } else if (signTeleportLocations.containsKey(signLocation) && signStates.get(signLocation)) {
                player.sendMessage("§6§lHoneywood: §cThis plot has already been claimed by another player.");
            }
        }
    }


    public void resetSigns() {
        for (Map.Entry<Location, Pair<Location, String>> entry : signTeleportLocations.entrySet()) {
            Location loc = entry.getKey();
            if (loc.getWorld() == null) {
                logger.warning("Could not reset sign at location " + loc + " because the world is null.");
                continue;
            }
            Block block = loc.getBlock();
            if (block.getType() == Material.OAK_WALL_SIGN) {
                Sign sign = (Sign) block.getState();
                String plotNumber = entry.getValue().getRight();
                sign.setLine(0, ChatColor.GREEN + "---------------");
                sign.setLine(1, ChatColor.GREEN + "PLOT: " + plotNumber);
                sign.setLine(2, ChatColor.GREEN + "[Right Click]");
                sign.setLine(3, ChatColor.GREEN + "---------------");
                Bukkit.getScheduler().runTaskLater(plugin, () -> sign.update(), 4L);
                signStates.put(loc, false); // Set state to false
            } else {
                logger.warning("Could not reset sign at location " + loc + " because it is not a wall sign.");
            }
        }
    }
}
