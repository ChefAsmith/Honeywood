package com.honeywood.honeywoodcore.jewelry;

import com.honeywood.honeywoodcore.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jewelry implements Listener {

    private static final List<ItemStack> necklaces = new ArrayList<>();

    private static final List<ItemStack> armbands = new ArrayList<>();

    public static List<ItemStack> getNecklaces() {
        return necklaces;
    }

    public static List<ItemStack> getArmBands() {
        return armbands;
    }

    public void registerCustomItems(JavaPlugin plugin) {

        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.DURABILITY, 100);

        // Register the Topaz Necklace
        ItemStack topazNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§e§lTopaz Necklace")
                .addLoreLine("§fMakes you glow §e§lYellow")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(topazNecklace);
        NamespacedKey key = new NamespacedKey(plugin, "topaz_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key);

        // Register the Amethyst Necklace
        ItemStack amethystNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§d§lAmethyst Necklace")
                .addLoreLine("§fMakes you glow §d§lLight Purple")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(amethystNecklace);
        NamespacedKey key2 = new NamespacedKey(plugin, "amethyst_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key2);

        // Register the Ruby Necklace
        ItemStack rubyNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§c§lRuby Necklace")
                .addLoreLine("§fMakes you glow §c§lRed")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(rubyNecklace);
        NamespacedKey key3 = new NamespacedKey(plugin, "ruby_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key3);

        // Register the Turquoise Necklace
        ItemStack turquoiseNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§b§lTurquoise Necklace")
                .addLoreLine("§fMakes you glow §b§lAqua")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(turquoiseNecklace);
        NamespacedKey key4 = new NamespacedKey(plugin, "turquoise_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key4);

        // Register the Peridot Necklace
        ItemStack peridotNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§a§lPeridot Necklace")
                .addLoreLine("§fMakes you glow §a§lGreen")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(peridotNecklace);
        NamespacedKey key5 = new NamespacedKey(plugin, "peridot_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key5);

        // Register the Sapphire Necklace
        ItemStack sapphireNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§9§lSapphire Necklace")
                .addLoreLine("§fMakes you glow §9§lBlue")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(sapphireNecklace);
        NamespacedKey key6 = new NamespacedKey(plugin, "sapphire_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key6);

        // Register the Golden Necklace
        ItemStack goldenNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§6§lGolden Necklace")
                .addLoreLine("§fMakes you glow §6§lGold")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(goldenNecklace);
        NamespacedKey key7 = new NamespacedKey(plugin, "golden_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key7);

        // Register the Tanzanite Necklace
        ItemStack tanzaniteNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§5§lTanzanite Necklace")
                .addLoreLine("§fMakes you glow §5§lDark Purple")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(tanzaniteNecklace);
        NamespacedKey key8 = new NamespacedKey(plugin, "tanzanite_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key8);

        // Register the Garnet Necklace
        ItemStack garnetNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§4§lGarnet Necklace")
                .addLoreLine("§fMakes you glow §4§lDark Red")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(garnetNecklace);
        NamespacedKey key9 = new NamespacedKey(plugin, "garnet_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key9);

        // Register the Indian Sapphire Necklace
        ItemStack indianSapphireNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§3§lIndian Sapphire Necklace")
                .addLoreLine("§fMakes you glow §3§lDark Aqua")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(indianSapphireNecklace);
        NamespacedKey key10 = new NamespacedKey(plugin, "indian_sapphire_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key10);

        // Register the Emerald Necklace
        ItemStack emeraldNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§2§lEmerald Necklace")
                .addLoreLine("§fMakes you glow §2§lDark Green")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(emeraldNecklace);
        NamespacedKey key11 = new NamespacedKey(plugin, "emerald_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key11);

        // Register the Dark Indigo Necklace
        ItemStack darkIndigoNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§1§lDark Indigo Necklace")
                .addLoreLine("§fMakes you glow §1§lDark Blue")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(darkIndigoNecklace);
        NamespacedKey key12 = new NamespacedKey(plugin, "dark_indigo_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key12);

        // Register the Jet Necklace
        ItemStack jetNecklace = new ItemCreator(Material.CHAIN)
                .setDisplayName("§0§lJet Necklace")
                .addLoreLine("§fMakes you glow §0§lBlack")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        necklaces.add(jetNecklace);
        NamespacedKey key13 = new NamespacedKey(plugin, "jet_necklace");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key13);

        // ---------------------------------------------------------------------------------------------------------------------------------

        // Register the Beekeeper Arm Band
        ItemStack beekeeperArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§e§lBee§0§lkeeper")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(beekeeperArmBand);
        NamespacedKey key14 = new NamespacedKey(plugin, "beekeeper_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key14);

        // Register the Honeycomb Arm Band
        ItemStack honeycombArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§6§lHoneycomb")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(honeycombArmBand);
        NamespacedKey key15 = new NamespacedKey(plugin, "honeycomb_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key15);

        // Register the ThePollinator Arm Band
        ItemStack thepollinatorArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§0§lThe§e§lPollinator")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(thepollinatorArmBand);
        NamespacedKey key16 = new NamespacedKey(plugin, "thepollinator_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key16);

        // Register the SweetAsNectar Arm Band
        ItemStack sweetasnectarArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§d§lSweetAsNectar")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(sweetasnectarArmBand);
        NamespacedKey key17 = new NamespacedKey(plugin, "sweetasnectar_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key17);

        // Register the TheQueenBee Arm Band
        ItemStack thequeenbeeArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§5§lTheQueenBee")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(thequeenbeeArmBand);
        NamespacedKey key18 = new NamespacedKey(plugin, "thequeenbee_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key18);

        // Register the #GrindingBee Arm Band
        ItemStack grindingbeeArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§b§l#Grinding§eBee")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(grindingbeeArmBand);
        NamespacedKey key19 = new NamespacedKey(plugin, "grindingbee_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key19);

        // Register the Buzzing Arm Band
        ItemStack buzzingArmBand = new ItemCreator(Material.LEAD)
                .setDisplayName("§9§lBuzzing")
                .addLoreLine("§fGives you the appropriate chat tag.")
                .setEnchantments(enchantments)
                .setUnbreakable(true)
                .setGlowing(true)
                .setHideAttributes(true)
                .build();
        armbands.add(buzzingArmBand);
        NamespacedKey key20 = new NamespacedKey(plugin, "buzzing_armband");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered custom item: " + key20);
    }
}
