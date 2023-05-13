package org.chef.honeywood.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.chef.honeywood.Honeywood;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Necklaces implements Listener, CommandExecutor {
    private String invName = "Necklaces";

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final int cooldownSeconds = 2;

    public Necklaces(Honeywood plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack getCustomItem(Material material, String name, String... lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> loreList = new ArrayList<>();
        for (String line : lore) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        if (loreList != null && !loreList.isEmpty()) {
            meta.setLore(loreList);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) {
            return;
        }

        String itemName = ChatColor.stripColor(item.getItemMeta().getDisplayName());

        if (itemName.equalsIgnoreCase("Topaz Necklace")) {
            handleNecklaceClick(player, "topaz", "yellow");
        } else if (itemName.equalsIgnoreCase("Amethyst Necklace")) {
            handleNecklaceClick(player, "amethyst", "pink");
        } else if (itemName.equalsIgnoreCase("Ruby Necklace")) {
            handleNecklaceClick(player, "ruby", "red");
        } else if (itemName.equalsIgnoreCase("Turquoise Necklace")) {
            handleNecklaceClick(player, "turquoise", "aqua");
        } else if (itemName.equalsIgnoreCase("Peridot Necklace")) {
            handleNecklaceClick(player, "peridot", "green");
        } else if (itemName.equalsIgnoreCase("Sapphire Necklace")) {
            handleNecklaceClick(player, "sapphire", "blue");
        } else if (itemName.equalsIgnoreCase("Golden Necklace")) {
            handleNecklaceClick(player, "golden", "gold");
        } else if (itemName.equalsIgnoreCase("Tanzanite Necklace")) {
            handleNecklaceClick(player, "tanzanite", "purple");
        } else if (itemName.equalsIgnoreCase("Garnet Necklace")) {
            handleNecklaceClick(player, "garnet", "darkred");
        } else if (itemName.equalsIgnoreCase("Indian Sapphire Necklace")) {
            handleNecklaceClick(player, "indiansapphire", "darkaqua");
        } else if (itemName.equalsIgnoreCase("Emerald Necklace")) {
            handleNecklaceClick(player, "emerald", "darkgreen");
        } else if (itemName.equalsIgnoreCase("Dark Indigo Necklace")) {
            handleNecklaceClick(player, "darkindigo", "darkblue");
        } else if (itemName.equalsIgnoreCase("Jet Necklace")) {
            handleNecklaceClick(player, "jet", "black");
        }
    }

    private void handleNecklaceClick(Player player, String necklaceName, String color) {
        if (player.hasPermission("necklace." + necklaceName)) {
            boolean enabled = toggleCommand(player, color); // Pass the color to toggleCommand
            String status = enabled ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED";
        }
    }

    private boolean toggleCommand(Player player, String color) {
        boolean isEnabled = false;

        // Get the player's name
        String playerName = player.getName();

        // Check if the player is on cooldown
        if (cooldowns.containsKey(player) && System.currentTimeMillis() - cooldowns.get(player) < cooldownSeconds * 1000) {
            player.sendMessage(ChatColor.RED + "You are on cooldown. Please wait " + (cooldownSeconds - (System.currentTimeMillis() - cooldowns.get(player)) / 1000) + " seconds.");
            return false;
        }

        // Set the cooldown
        cooldowns.put(player, System.currentTimeMillis());

        // Check if the player has the "glowing" effect
        if (player.hasPotionEffect(PotionEffectType.GLOWING)) {
            // Remove the effect
            player.removePotionEffect(PotionEffectType.GLOWING);

            // Unset the eGlow color for the player
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eglow unset " + playerName);

            isEnabled = false;
        } else {
            // Add the effect
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
            // Set the eGlow color to yellow for the player
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eglow set " + playerName + " " + color);

            isEnabled = true;
        }

        return isEnabled;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String [] argss) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§6§lHoneywood: §cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("honeywood.jewelry")) {
            player.sendMessage(ChatColor.RED + "§6§lHoneywood: §cYou do not have permission to use this command. §rPlease retry when you have this permission: honeywood.jewelry");
            return true;
        }

        player = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 9 * 5, invName);

        inv.setItem(10, getCustomItem(Material.CHAIN, "&e&lTopaz Necklace", "&fMakes you glow &e&lYellow"));
        inv.setItem(11, getCustomItem(Material.CHAIN, "§d§lAmethyst Necklace", "§fMakes you glow §d§lPink"));
        inv.setItem(12, getCustomItem(Material.CHAIN, "§c§lRuby Necklace", "§fMakes you glow §c§lRed"));
        inv.setItem(13, getCustomItem(Material.CHAIN, "§b§lTurquoise Necklace", "§fMakes you glow §b§lAqua"));
        inv.setItem(14, getCustomItem(Material.CHAIN, "§a§lPeridot Necklace", "§fMakes you glow §a§lGreen"));
        inv.setItem(15, getCustomItem(Material.CHAIN, "§9§lSapphire Necklace", "§fMakes you glow §9§lBlue"));
        inv.setItem(16, getCustomItem(Material.CHAIN, "§6§lGolden Necklace", "§fMakes you glow §6§lGold"));
        inv.setItem(20, getCustomItem(Material.CHAIN, "§5§lTanzanite Necklace", "§fMakes you glow §5§lPurple"));
        inv.setItem(21, getCustomItem(Material.CHAIN, "§4§lGarnet Necklace", "§fMakes you glow §4§lDark Red"));
        inv.setItem(22, getCustomItem(Material.CHAIN, "§3§lIndian Sapphire Necklace", "§fMakes you glow §3§lDark Aqua"));
        inv.setItem(23, getCustomItem(Material.CHAIN, "§2§lEmerald Necklace", "§fMakes you glow §2§lDark Green"));
        inv.setItem(24, getCustomItem(Material.CHAIN, "§1§lDark Indigo Necklace", "§fMakes you glow §1§lDark Blue"));
        inv.setItem(31, getCustomItem(Material.CHAIN, "§7§lJet Necklace", "§fMakes you glow §7§lBlack"));


        ItemStack orangePane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemStack yellowPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);

        inv.setItem(0, orangePane);
        inv.setItem(1, orangePane);
        inv.setItem(2, orangePane);
        inv.setItem(3, orangePane);
        inv.setItem(4, orangePane);
        inv.setItem(5, orangePane);
        inv.setItem(6, orangePane);
        inv.setItem(7, orangePane);
        inv.setItem(8, orangePane);
        inv.setItem(9, orangePane);
        inv.setItem(17, orangePane);
        inv.setItem(18, orangePane);
        inv.setItem(26, orangePane);
        inv.setItem(27, orangePane);
        inv.setItem(35, orangePane);
        inv.setItem(36, orangePane);
        inv.setItem(37, orangePane);
        inv.setItem(38, orangePane);
        inv.setItem(39, orangePane);
        inv.setItem(40, orangePane);
        inv.setItem(41, orangePane);
        inv.setItem(42, orangePane);
        inv.setItem(43, orangePane);
        inv.setItem(44, orangePane);

        inv.setItem(19, yellowPane);
        inv.setItem(25, yellowPane);
        inv.setItem(28, yellowPane);
        inv.setItem(29, yellowPane);
        inv.setItem(30, yellowPane);
        inv.setItem(32, yellowPane);
        inv.setItem(33, yellowPane);
        inv.setItem(34, yellowPane);

        player.openInventory(inv);

        return true;
    }

}