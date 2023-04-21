package com.honeywood.honeywoodcore.jewelery;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Jewelery implements Listener {

    private static final List<ItemStack> necklaces = new ArrayList<>();

    private static final List<ItemStack> armbands = new ArrayList<>();

    private static ItemStack createNecklace(String displayName, int customModelData) {
        ItemStack item = new ItemStack(Material.CHAIN);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setCustomModelData(customModelData);
            item.setItemMeta(meta);
        }
        return item;
    }

    private static ItemStack createArmBand(String displayName, int customModelData) {
        ItemStack item = new ItemStack(Material.LEAD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setCustomModelData(customModelData);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static List<ItemStack> getNecklaces() {
        return necklaces;
    }

    public static List<ItemStack> getArmBands() {
        return armbands;
    }

    public void registerCustomItems(JavaPlugin plugin) {

        // Register the Topaz Necklace
        ItemStack topazNecklace = createNecklace("§e§lTopaz Necklace", 10);
        NamespacedKey key = new NamespacedKey(plugin, "topaz_necklace");
        ShapedRecipe topazRecipe = new ShapedRecipe(key, topazNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(topazRecipe);
        necklaces.add(topazNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key);

        // Register the Amethyst Necklace
        ItemStack amethystNecklace = createNecklace("§d§lAmethyst Necklace", 11);
        NamespacedKey key2 = new NamespacedKey(plugin, "amethyst_necklace");
        ShapedRecipe amethystRecipe = new ShapedRecipe(key2, amethystNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(amethystRecipe);
        necklaces.add(amethystNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key2);

        // Register the Ruby Necklace
        ItemStack rubyNecklace = createNecklace("§c§lRuby Necklace", 12);
        NamespacedKey key3 = new NamespacedKey(plugin, "ruby_necklace");
        ShapedRecipe rubyRecipe = new ShapedRecipe(key3, rubyNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(rubyRecipe);
        necklaces.add(rubyNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key3);

        // Register the Turquoise Necklace
        ItemStack turquoiseNecklace = createNecklace("§b§lTurquoise Necklace", 13);
        NamespacedKey key4 = new NamespacedKey(plugin, "turquoise_necklace");
        ShapedRecipe turquoiseRecipe = new ShapedRecipe(key4, turquoiseNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(turquoiseRecipe);
        necklaces.add(turquoiseNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key4);

        // Register the Peridot Necklace
        ItemStack peridotNecklace = createNecklace("§a§lPeridot Necklace", 14);
        NamespacedKey key5 = new NamespacedKey(plugin, "peridot_necklace");
        ShapedRecipe peridotRecipe = new ShapedRecipe(key5, peridotNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(peridotRecipe);
        necklaces.add(peridotNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key5);

        // Register the Sapphire Necklace
        ItemStack sapphireNecklace = createNecklace("§9§lSapphire Necklace", 15);
        NamespacedKey key6 = new NamespacedKey(plugin, "sapphire_necklace");
        ShapedRecipe sapphireRecipe = new ShapedRecipe(key6, sapphireNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(sapphireRecipe);
        necklaces.add(sapphireNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key6);

        // Register the Golden Necklace
        ItemStack goldenNecklace = createNecklace("§6§lGolden Necklace", 16);
        NamespacedKey key7 = new NamespacedKey(plugin, "golden_necklace");
        ShapedRecipe goldenRecipe = new ShapedRecipe(key7, goldenNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(goldenRecipe);
        necklaces.add(goldenNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key7);

        // Register the Tanzanite Necklace
        ItemStack tanzaniteNecklace = createNecklace("§5§lTanzanite Necklace", 17);
        NamespacedKey key8 = new NamespacedKey(plugin, "tanzanite_necklace");
        ShapedRecipe tanzaniteRecipe = new ShapedRecipe(key8, tanzaniteNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(tanzaniteRecipe);
        necklaces.add(tanzaniteNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key8);

        // Register the Garnet Necklace
        ItemStack garnetNecklace = createNecklace("§4§lGarnet Necklace", 18);
        NamespacedKey key9 = new NamespacedKey(plugin, "garnet_necklace");
        ShapedRecipe garnetRecipe = new ShapedRecipe(key9, garnetNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(garnetRecipe);
        necklaces.add(garnetNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key9);

        // Register the Indian Sapphire Necklace
        ItemStack indianSapphireNecklace = createNecklace("§3§lIndian Sapphire Necklace", 19);
        NamespacedKey key10 = new NamespacedKey(plugin, "indian_sapphire_necklace");
        ShapedRecipe indianSapphireRecipe = new ShapedRecipe(key10, indianSapphireNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(indianSapphireRecipe);
        necklaces.add(indianSapphireNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key10);

        // Register the Emerald Necklace
        ItemStack emeraldNecklace = createNecklace("§2§lEmerald Necklace", 20);
        NamespacedKey key11 = new NamespacedKey(plugin, "emerald_necklace");
        ShapedRecipe emeraldRecipe = new ShapedRecipe(key11, emeraldNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(emeraldRecipe);
        necklaces.add(emeraldNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key11);

        // Register the Dark Indigo Necklace
        ItemStack darkIndigoNecklace = createNecklace("§1§lDark Indigo Necklace", 21);
        NamespacedKey key12 = new NamespacedKey(plugin, "dark_indigo_necklace");
        ShapedRecipe darkIndigoRecipe = new ShapedRecipe(key12, darkIndigoNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(darkIndigoRecipe);
        necklaces.add(darkIndigoNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key12);

        // Register the Jet Necklace
        ItemStack jetNecklace = createNecklace("§0§lJet Necklace", 22);
        NamespacedKey key13 = new NamespacedKey(plugin, "jet_necklace");
        ShapedRecipe jetRecipe = new ShapedRecipe(key13, jetNecklace).shape("Y").setIngredient('Y', Material.CHAIN);
        plugin.getServer().addRecipe(jetRecipe);
        necklaces.add(jetNecklace);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key13);

        // ---------------------------------------------------------------------------------------------------------------------------------

        // Register the Beekeeper Arm Band
        ItemStack beekeeperArmBand = createArmBand("§e§lBee§0§lkeeper", 23);
        NamespacedKey key14 = new NamespacedKey(plugin, "beekeeper_armband");
        ShapedRecipe beekeeperRecipe = new ShapedRecipe(key14, beekeeperArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(beekeeperRecipe);
        armbands.add(beekeeperArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key14);

        // Register the Honeycomb Arm Band
        ItemStack honeycombArmBand = createArmBand("§6§lHoneycomb", 24);
        NamespacedKey key15 = new NamespacedKey(plugin, "honeycomb_armband");
        ShapedRecipe honeycombRecipe = new ShapedRecipe(key15, honeycombArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(honeycombRecipe);
        armbands.add(honeycombArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key15);

        // Register the ThePollinator Arm Band
        ItemStack thepollinatorArmBand = createArmBand("§0§lThe§e§lPollinator", 25);
        NamespacedKey key16 = new NamespacedKey(plugin, "thepollinator_armband");
        ShapedRecipe thepollinatorRecipe = new ShapedRecipe(key16, thepollinatorArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(thepollinatorRecipe);
        armbands.add(thepollinatorArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key16);

        // Register the SweetAsNectar Arm Band
        ItemStack sweetasnectarArmBand = createArmBand("§d§lSweetAsNectar", 26);
        NamespacedKey key17 = new NamespacedKey(plugin, "sweetasnectar_armband");
        ShapedRecipe sweetasnectarRecipe = new ShapedRecipe(key17, sweetasnectarArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(sweetasnectarRecipe);
        armbands.add(sweetasnectarArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key17);

        // Register the TheQueenBee Arm Band
        ItemStack thequeenbeeArmBand = createArmBand("§5§lTheQueenBee", 27);
        NamespacedKey key18 = new NamespacedKey(plugin, "thequeenbee_armband");
        ShapedRecipe thequeenbeeRecipe = new ShapedRecipe(key18, thequeenbeeArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(thequeenbeeRecipe);
        armbands.add(thequeenbeeArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key18);

        // Register the #GrindingBee Arm Band
        ItemStack grindingbeeArmBand = createArmBand("§b§l#Grinding§eBee", 28);
        NamespacedKey key19 = new NamespacedKey(plugin, "grindingbee_armband");
        ShapedRecipe grindingbeeRecipe = new ShapedRecipe(key19, grindingbeeArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(grindingbeeRecipe);
        armbands.add(grindingbeeArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key19);

        // Register the Buzzing Arm Band
        ItemStack buzzingArmBand = createArmBand("§9§lBuzzing", 29);
        NamespacedKey key20 = new NamespacedKey(plugin, "buzzing_armband");
        ShapedRecipe buzzingRecipe = new ShapedRecipe(key20, buzzingArmBand).shape("Y").setIngredient('Y', Material.LEAD);
        plugin.getServer().addRecipe(buzzingRecipe);
        armbands.add(buzzingArmBand);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key20);
    }
}
