package com.honeywood.honeywoodcore.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemCreator {

    private final Material material;
    private String displayName;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private boolean unbreakable;
    private boolean glowing;
    private boolean hideAttributes;

    public ItemCreator(Material material) {
        this.material = material;
        this.displayName = "";
        this.lore = new ArrayList<>();
        this.enchantments = null;
        this.unbreakable = false;
        this.glowing = false;
        this.hideAttributes = false;
    }

    public ItemCreator setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemCreator setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemCreator addLoreLine(String loreLine) {
        this.lore.add(loreLine);
        return this;
    }

    public ItemCreator setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemCreator setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemCreator setGlowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public ItemCreator setHideAttributes(boolean hideAttributes) {
        this.hideAttributes = hideAttributes;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        // Set display name
        if (!displayName.isEmpty()) {
            Objects.requireNonNull(meta).setDisplayName(displayName);
        }

        // Set lore
        if (!lore.isEmpty()) {
            Objects.requireNonNull(meta).setLore(lore);
        }

        // Set enchantments
        if (enchantments != null) {
            for (Enchantment enchantment : enchantments.keySet()) {
                Objects.requireNonNull(meta).addEnchant(enchantment, enchantments.get(enchantment), true);
            }
        }

        // Set unbreakable
        Objects.requireNonNull(meta).setUnbreakable(unbreakable);

        // Set item flags
        if (glowing) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (hideAttributes) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        item.setItemMeta(meta);
        return item;
    }
}
