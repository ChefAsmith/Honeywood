package com.honeywood.honeywoodcore;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Pollen implements Listener {

    private final Random random = new Random();
    public int pollenChance;

    private final Material material;
    private final int chance;

    public Pollen(Material material, int chance) {
        this.material = material;
        this.chance = chance;
    }

    public Material getMaterial() {
        return material;
    }

    public int getChance() {
        return chance;
    }

    public void registerCustomItem(JavaPlugin plugin) {
        // Get the pollen chance from the config.yml file
        FileConfiguration config = plugin.getConfig();
        pollenChance = config.getInt("pollen_chance", 1000);

        // Create a new ItemStack for your custom item
        ItemStack item = new ItemStack(Material.SUNFLOWER);

        // Set the display name of the item to "Pollen" with an invisible Unicode character
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6§l✴" + "Pollen" + "✴");
            // Set the CustomItemModelData value to a unique integer
            meta.setCustomModelData(1);
            item.setItemMeta(meta);
        }

        // Add the custom item to the game's registry
        NamespacedKey key = new NamespacedKey(plugin, "pollen");
        plugin.getServer().addRecipe(new ShapedRecipe(key, item).shape("Y").setIngredient('Y', Material.SUNFLOWER));

        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Print a message to the console to confirm that the item has been registered
        plugin.getLogger().info("Registered custom item: " + key);

        // Reload the pollenChance value from the config when it is changed
        plugin.getConfig().addDefault("pollen_chance", 1000);
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        pollenChance = plugin.getConfig().getInt("pollen_chance");

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Check if the entity is a bee
        if (event.getEntityType() == EntityType.BEE) {
            // Validate the pollenChance value
            if (pollenChance <= 0) {
            } else if (pollenChance <= 1) {
                // Always drop pollen if pollenChance is less than or equal to 1
                event.getDrops().add(getPollenItem());
            } else {
                // Roll a chance to drop pollen based on the pollenChance value
                if (random.nextInt(pollenChance) == 0) {
                    event.getDrops().add(getPollenItem());
                }
            }
        }
    }



    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        // Check if the entity is a bee
        if (event.getRightClicked().getType() == EntityType.BEE) {
            // Validate the pollenChance value
            if (pollenChance <= 0) {
            } else if (pollenChance <= 1) {
                // Always drop pollen if pollenChance is less than or equal to 1
                ItemStack pollen = getPollenItem();
                pollen.setAmount(1);
                event.getPlayer().getInventory().addItem(pollen);
            } else {
                // Roll a chance to drop pollen based on the pollenChance value
                if (random.nextInt(pollenChance) == 0) {
                    ItemStack pollen = getPollenItem();
                    pollen.setAmount(1);
                    event.getPlayer().getInventory().addItem(pollen);
                }
            }
        }
    }

    public ItemStack getPollenItem() {
        ItemStack item = new ItemStack(Material.SUNFLOWER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6§l✴" + "Pollen" + "✴");
            meta.setCustomModelData(1);
            item.setItemMeta(meta);
        }
        return item;
    }
}
