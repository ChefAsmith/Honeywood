package org.chef.honeywood.commands;

import org.chef.honeywood.Honeywood;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


public class ChefProtectionCommand implements CommandExecutor {

    public ChefProtectionCommand(Honeywood plugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("staff.chefprotection")) {
            player.sendMessage("§6§lHoneywood: §cYou do not have permission to use this command. §rPlease come retry when you get this permission: staff.chefprotection");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /chefprotection <on|off>");
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (Bukkit.getPlayerExact("Chef____") == null) {
                player.sendMessage(ChatColor.RED + "The player Chef____ is not online.");
                return true;
            }

            // Prevent Chef____ from being killed
            Bukkit.getPluginManager().registerEvents(new NoKillListener(), Honeywood.getInstance());

            // Prevent Chef____ from being deopped
            Bukkit.getPluginManager().registerEvents(new NoDeopListener(), Honeywood.getInstance());

            // Prevent Chef____ from being banned
            //Bukkit.getPluginManager().registerEvents(new NoBanListener(), Honeywood.getInstance());

            // Prevent Chef____ from being kicked
            Bukkit.getPluginManager().registerEvents(new NoKickListener(), Honeywood.getInstance());

            player.sendMessage(ChatColor.GREEN + "Chef____'s protection is now enabled.");
        } else if (args[0].equalsIgnoreCase("off")) {
            if (!player.getName().equalsIgnoreCase("Chef____")) {
                player.sendMessage(ChatColor.RED + "You cannot disable Chef____'s protection.");
                return true;
            }

            // Remove all protection listeners for Chef____
            HandlerList.unregisterAll((Plugin) Honeywood.getInstance());

            player.sendMessage(ChatColor.GREEN + "Chef____'s protection is now disabled.");
        } else {
            player.sendMessage(ChatColor.RED + "Usage: /chefprotection <on|off>");
        }

        return true;
    }

    private static class NoKillListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerDeath(PlayerDeathEvent event) {
            Player player = event.getEntity();
            if (player.getName().equalsIgnoreCase("Chef____")) {
                event.setDeathMessage(null);
                event.getEntity().spigot().respawn();
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onEntityDamage(EntityDamageEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.getName().equalsIgnoreCase("Chef____")) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private static class NoDeopListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
            if (event.getPlayer().getName().equalsIgnoreCase("Chef____")) {
                if (event.getMessage().toLowerCase().startsWith("/deop")) {
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot deop Chef____.");
                    event.setCancelled(true);
                }
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerQuitEvent(PlayerQuitEvent event) {
            if (event.getPlayer().getName().equalsIgnoreCase("Chef____")) {
                event.setQuitMessage(null);
            }
        }
    }

    //private static class NoBanListener implements Listener {

        //@EventHandler(priority = EventPriority.HIGHEST)
       // public void onPlayerBan(PlayerBanEvent event) {
           // Player player = event.getPlayer();
         //   if (player.getName().equalsIgnoreCase("Chef____")) {
        //        event.setCancelled(true);
        //    }
      //  }
    //}

    private static class NoKickListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerKick(PlayerKickEvent event) {
            Player player = event.getPlayer();
            if (player.getName().equalsIgnoreCase("Chef____")) {
                event.setLeaveMessage(null);
                event.setCancelled(true);
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerQuit(PlayerQuitEvent event) {
            Player player = event.getPlayer();
            if (player.getName().equalsIgnoreCase("Chef____")) {
                event.setQuitMessage(null);
            }
        }
    }
}
