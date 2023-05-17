package org.chef.honeywood.events;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockProtection implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();

        if (world.getName().equals("spawn") && isProtectedBlock(block.getType()) && !player.isOp()) {
            event.setCancelled(true);
            player.sendMessage("§6§lHoneywood: §cYou are not allowed to break this block in the spawn world.");
        }
    }

    private boolean isProtectedBlock(Material material) {
        return material == Material.GLASS ||
                material == Material.QUARTZ_STAIRS ||
                material == Material.QUARTZ_PILLAR ||
                material == Material.SEA_LANTERN ||
                material == Material.WARPED_WALL_SIGN ||
                material == Material.POLISHED_BLACKSTONE_BUTTON;
    }
}
