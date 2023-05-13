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

public class Armbands implements Listener, CommandExecutor {
    private String invName = "Armbands";

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private final int cooldownSeconds = 2;

    public Armbands(Honeywood plugin) {
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

        if (itemName.equalsIgnoreCase("BeeKeeper")) {
            handleArmbandClick(player, "beekeeper", "§e§lBee§0§lkeeper");
        } else if (itemName.equalsIgnoreCase("Honeycomb")) {
            handleArmbandClick(player, "honeycomb", "§6§lHoneycomb");
        } else if (itemName.equalsIgnoreCase("ThePollinator")) {
            handleArmbandClick(player, "thepollinator", "§0§lThe§e§lPollinator");
        } else if (itemName.equalsIgnoreCase("SweetAsNectar")) {
            handleArmbandClick(player, "sweetasnectar", "§d§lSweetAsNectar");
        } else if (itemName.equalsIgnoreCase("TheQueenBee")) {
            handleArmbandClick(player, "thequeenbee", "§5§lTheQueenBee");
        } else if (itemName.equalsIgnoreCase("#GrindingBee")) {
            handleArmbandClick(player, "grindingbee", "§b§l#Grinding§eBee");
        } else if (itemName.equalsIgnoreCase("Buzzing")) {
            handleArmbandClick(player, "buzzing", "§9§lBuzzing");
        }
    }

    private void handleArmbandClick(Player player, String armbandName, String tag) {
        if (player.hasPermission("armband." + armbandName)) {
            boolean enabled = toggleCommand(player, tag); // Pass the color to toggleCommand
            String status = enabled ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED";
        }
    }


    private boolean toggleCommand(Player player, String tag) {
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
        if (player.hasPotionEffect(PotionEffectType.LUCK)) {
            // Remove the effect
            player.removePotionEffect(PotionEffectType.LUCK);

            // Unset the eGlow color for the player
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + playerName + " meta removesuffix 100 ");

            isEnabled = false;
        } else {
            // Add the effect
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 0));
            // Set the eGlow color to yellow for the player
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + playerName + " meta setsuffix 100 " + tag);

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

        inv.setItem(12, getCustomItem(Material.LEAD, "§f§lBeekeeper", "§fGives you the appropriate chat tag: §e§lBee§0§lkeeper."));
        inv.setItem(13, getCustomItem(Material.LEAD, "§f§lHoneycomb", "§fGives you the appropriate chat tag: §6§lHoneycomb."));
        inv.setItem(14, getCustomItem(Material.LEAD, "§f§lThePollinator", "§fGives you the appropriate chat tag: §0§lThe§e§lPollinator."));
        inv.setItem(21, getCustomItem(Material.LEAD, "§f§lSweetAsNectar", "§fGives you the appropriate chat tag: §d§lSweetAsNectar."));
        inv.setItem(22, getCustomItem(Material.LEAD, "§f§lTheQueenBee", "§fGives you the appropriate chat tag: §5§lTheQueenBee."));
        inv.setItem(23, getCustomItem(Material.LEAD, "§f§l#GrindingBee", "§fGives you the appropriate chat tag: §b§l#Grinding§eBee."));
        inv.setItem(31, getCustomItem(Material.LEAD, "§f§lBuzzing", "§fGives you the appropriate chat tag: §9§lBuzzing."));


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

        inv.setItem(10, yellowPane);
        inv.setItem(11, yellowPane);
        inv.setItem(15, yellowPane);
        inv.setItem(16, yellowPane);
        inv.setItem(19, yellowPane);
        inv.setItem(20, yellowPane);
        inv.setItem(24, yellowPane);
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