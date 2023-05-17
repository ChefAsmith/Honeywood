package org.chef.honeywood.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventButtonListener implements Listener, CommandExecutor {

    private JavaPlugin plugin;
    private Map<Player, Long> cooldowns;

    public EventButtonListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.cooldowns = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eventgiveitems") && sender.hasPermission("honeywood.eventgiveitems")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.");
                return true;
            }

            Player player = (Player) sender;
            giveItems(player);

            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && clickedBlock.getType() == Material.POLISHED_BLACKSTONE_BUTTON) {
                Player player = event.getPlayer();
                long currentTime = System.currentTimeMillis();
                long lastItemTime = cooldowns.getOrDefault(player, 0L);
                long cooldownTime = 2 * 60 * 1000; // 2 minutes in milliseconds

                if (currentTime - lastItemTime >= cooldownTime) {
                    giveItems(player);
                    cooldowns.put(player, currentTime);
                } else {
                    long remainingTime = cooldownTime - (currentTime - lastItemTime);
                    int seconds = (int) (remainingTime / 1000) % 60;
                    int minutes = (int) (remainingTime / 1000) / 60;

                    player.sendMessage("§6§lHoneywood: §cYou need to wait " + minutes + " minutes and " + seconds + " seconds before receiving more items.");
                }
            }
        }
    }

    private void giveItems(Player player) {
        FileConfiguration config = plugin.getConfig();

        if (!config.isList("event_resources")) {
            return;
        }

        List<String> itemStrings = config.getStringList("event_resources");

        for (String itemString : itemStrings) {
            String[] parts = itemString.split(":");
            if (parts.length != 2) {
                continue;
            }

            String materialName = parts[0];
            int amount = Integer.parseInt(parts[1]);

            Material material = Material.matchMaterial(materialName);
            if (material == null) {
                continue;
            }

            ItemStack item = new ItemStack(material, amount);
            player.getInventory().addItem(item);
        }

        player.sendMessage("§6§lHoneywood: §aYou have received the event items.");
    }
}
