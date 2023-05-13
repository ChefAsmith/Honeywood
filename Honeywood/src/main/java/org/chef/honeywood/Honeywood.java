package org.chef.honeywood;

import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.chef.honeywood.commands.*;
import org.chef.honeywood.discord.DiscordWebhook;
import org.chef.honeywood.listeners.DiscordEventListener;
import org.chef.honeywood.menus.Armbands;
import org.chef.honeywood.menus.JewelryCategory;
import org.chef.honeywood.menus.Necklaces;
import org.chef.honeywood.pollen.Pollen;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Honeywood extends JavaPlugin implements Listener {
    private int clickCount = 0;
    private boolean clickLimitEnabled = true;

    public void toggleclicklimit() {
        clickLimitEnabled = !clickLimitEnabled;
    }

    public boolean isClickLimitEnabled() {
        return clickLimitEnabled;
    }

    private static Honeywood instance;

    public static Honeywood getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false); // Do not replace existing file
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        getLogger().info("Config loaded: pollen_chance = " + getConfig().getDouble("pollen_chance"));

        Pollen pollenInstance = new Pollen(Material.SUNFLOWER, 1); // Pass the required arguments
        pollenInstance.registerCustomItem(this);

        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("toggleclicklimit")).setExecutor(new ToggleClickLimitCommand(this));
        Objects.requireNonNull(getCommand("honeywoodreload")).setExecutor(new ReloadCommand(this, pollenInstance)); // Pass the required Pollen instance
        Objects.requireNonNull(getCommand("giveop")).setExecutor(new GiveOpCommand());
        Objects.requireNonNull(getCommand("translocate")).setExecutor(new TranslocateCommand(this));
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new ClearChatCommand(this));
        Objects.requireNonNull(getCommand("chefprotection")).setExecutor(new ChefProtectionCommand(this));
        Objects.requireNonNull(getCommand("pl")).setExecutor(new NoPluginsCommand(this));
        Objects.requireNonNull(getCommand("plugins")).setExecutor(new NoPluginsCommand(this));

        Objects.requireNonNull(getCommand("jewelry")).setExecutor(new JewelryCategory(this));
        Objects.requireNonNull(getCommand("crownsandhats")).setExecutor(new JewelryCategory(this));
        Objects.requireNonNull(getCommand("necklaces")).setExecutor(new Necklaces(this));
        Objects.requireNonNull(getCommand("armbands")).setExecutor(new Armbands(this));
        Objects.requireNonNull(getCommand("rings")).setExecutor(new JewelryCategory(this));


        getServer().getPluginManager().registerEvents(new DiscordEventListener(getLogger()), this);
        String webhookURL = "";
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("Server is starting!"));
        try {
            webhook.execute();
        } catch (java.io.IOException e) {
            getLogger().severe(Arrays.toString(e.getStackTrace()));
        }

        PluginCommand givePollenCommand = getCommand("givepollen");
        if (givePollenCommand != null) {
            givePollenCommand.setExecutor(new GivePollenCommand(pollenInstance));
        } else {
            getLogger().warning("Failed to register GivePollenCommand");
        }

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> clickCount = 0, 0L, 20L);
    }


    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.BEE) {
            Player player = event.getPlayer();

            if (isClickLimitEnabled()) {
                clickCount++;
                if (clickCount > 12) {
                    player.sendMessage("§6§lHoneywood §r§7» §cSlow down your clicking!");
                    event.setCancelled(true);
                    getLogger().info("Player " + player.getName() + " clicked too fast! Current click count: " + clickCount);
                    clickCount = 0; // Reset the click count
                }
            }
        }
    }

    @Override
    public void onDisable() {
        String webhookURL = "";
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("Server is shutting down!"));
        try {
            webhook.execute();
        } catch (java.io.IOException e) {
            getLogger().severe(Arrays.toString(e.getStackTrace()));
        }
    }
}
