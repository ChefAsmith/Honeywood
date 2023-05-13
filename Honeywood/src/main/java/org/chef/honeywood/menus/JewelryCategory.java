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
import org.chef.honeywood.Honeywood;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JewelryCategory implements Listener, CommandExecutor {
    private String invName = "Equip your Jewelry";

    public JewelryCategory(Honeywood plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
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

        switch (item.getType()) {
            case GOLDEN_HELMET:
                player.closeInventory();
                // Open the Crowns GUI
                player.performCommand("crownsandhats");
                break;
            case CHAIN:
                player.closeInventory();
                // Open the Necklaces GUI
                player.performCommand("necklaces");
                break;
            case LEAD:
                player.closeInventory();
                // Open the Armbands GUI
                player.performCommand("armbands");
                break;
            case DIAMOND:
                player.closeInventory();
                // Open the Hats GUI
                player.performCommand("rings");
                break;
            default:
                break;
        }
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

        inv.setItem(19, getItem(new ItemStack(Material.GOLDEN_HELMET), "&6Crowns &fand &6Hats", "&aClick to open the Crown and hats menu!"));
        inv.setItem(21, getItem(new ItemStack(Material.CHAIN), "&6Necklaces", "&aClick to open the Necklace menu!"));
        inv.setItem(23, getItem(new ItemStack(Material.LEAD), "&6Armbands", "&aClick to open the Armband menu!"));
        inv.setItem(25, getItem(new ItemStack(Material.DIAMOND), "&6Rings", "&aClick to open the Rings menu!"));

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
        inv.setItem(12, yellowPane);
        inv.setItem(13, yellowPane);
        inv.setItem(14, yellowPane);
        inv.setItem(15, yellowPane);
        inv.setItem(16, yellowPane);
        inv.setItem(20, yellowPane);
        inv.setItem(22, yellowPane);
        inv.setItem(24, yellowPane);
        inv.setItem(28, yellowPane);
        inv.setItem(29, yellowPane);
        inv.setItem(30, yellowPane);
        inv.setItem(31, yellowPane);
        inv.setItem(32, yellowPane);
        inv.setItem(33, yellowPane);
        inv.setItem(34, yellowPane);


        player.openInventory(inv);

        return true;
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;
    }
}