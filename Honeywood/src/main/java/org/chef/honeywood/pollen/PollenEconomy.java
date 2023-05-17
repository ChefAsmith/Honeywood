package org.chef.honeywood.pollen;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class PollenEconomy implements Listener, CommandExecutor {
    private final Map<String, Integer> pollenMap;
    private final Random random;
    private final File dataFile;
    private final YamlConfiguration dataConfig;
    private final JavaPlugin plugin;

    public PollenEconomy(JavaPlugin plugin) {
        this.plugin = plugin;
        pollenMap = new HashMap<>();
        random = new Random();

        dataFile = new File(plugin.getDataFolder(), "pollen_data.yml");
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        loadPollenData();

        // Schedule automatic data saving every 5 minutes
        long saveIntervalTicks = 5 * 60 * 20; // 5 minutes in ticks (20 ticks per second)
        new BukkitRunnable() {
            @Override
            public void run() {
                savePollenData();
            }
        }.runTaskTimer(plugin, saveIntervalTicks, saveIntervalTicks);
    }

    public void loadPollenData() {
        if (dataConfig.contains("pollen")) {
            ConfigurationSection pollenSection = dataConfig.getConfigurationSection("pollen");
            for (String playerName : Objects.requireNonNull(pollenSection).getKeys(false)) {
                int pollenAmount = pollenSection.getInt(playerName);
                pollenMap.put(playerName, pollenAmount);
            }
        }
    }

    public void savePollenData() {
        ConfigurationSection pollenSection = dataConfig.createSection("pollen");
        for (Map.Entry<String, Integer> entry : pollenMap.entrySet()) {
            pollenSection.set(entry.getKey(), entry.getValue());
        }

        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pollen") || command.getName().equalsIgnoreCase("pollenbal")) {
            if (args.length == 0) {
                if (sender instanceof Player player) {
                    int pollenAmount = getPollenAmount(player);
                    player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Honeywood: " +
                            ChatColor.WHITE + "You currently have " + ChatColor.YELLOW + pollenAmount +
                            " pollen" + ChatColor.WHITE + ", you can use your pollen in the /jewelryshop");
                } else {
                    sender.sendMessage(ChatColor.RED + "§6§lHoneywood: §cOnly players can use this command.");
                }
                return true;
            } else if (args.length == 3) {
                String playerName = args[1];
                int amount;
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid amount specified.");
                    return true;
                }

                if (args[0].equalsIgnoreCase("give") && sender.hasPermission("honeywood.admin")) {
                    givePollen(playerName, amount);
                    sender.sendMessage(ChatColor.GREEN + "Successfully gave " + playerName + " " +
                            amount + " pollen.");
                    return true;
                } else if (args[0].equalsIgnoreCase("take") && sender.hasPermission("honeywood.admin")) {
                    takePollen(playerName, amount);
                    sender.sendMessage(ChatColor.GREEN + "Successfully took " + amount +
                            " pollen from " + playerName + ".");
                    return true;
                } else if (args[0].equalsIgnoreCase("set") && sender.hasPermission("honeywood.admin")) {
                    setPollen(playerName, amount);
                    sender.sendMessage(ChatColor.GREEN + "Successfully set " + playerName +
                            "'s pollen to " + amount + ".");
                    return true;
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("save")) {
                if (sender.hasPermission("honeywood.admin")) {
                    savePollenData();
                    sender.sendMessage(ChatColor.GREEN + "Pollen data saved.");
                } else {
                    sender.sendMessage(ChatColor.RED + "§6§lHoneywood: §cYou do not have permission to use this command. §rPlease retry when you have this permission: honeywood.admin");
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid command format. Usage:");
                sender.sendMessage(ChatColor.GRAY + "/pollen - View your pollen balance");
                sender.sendMessage(ChatColor.GRAY + "/pollen give <player> <amount> - Give pollen to a player");
                sender.sendMessage(ChatColor.GRAY + "/pollen take <player> <amount> - Take pollen from a player");
                sender.sendMessage(ChatColor.GRAY + "/pollen set <player> <amount> - Set a player's pollen amount");
                sender.sendMessage(ChatColor.GRAY + "/pollen save - Manually save the pollen data");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.BEE) {
            if (random.nextInt(100) == 0) {
                Player player = event.getPlayer();
                getServer().dispatchCommand(getServer().getConsoleSender(),
                        "ci give " + player.getName() + " pollenitem");
            }
        }
    }

    // Helper methods for managing pollen amounts

    private int getPollenAmount(Player player) {
        return pollenMap.getOrDefault(player.getName(), 0);
    }

    private void givePollen(String playerName, int amount) {
        int currentAmount = pollenMap.getOrDefault(playerName, 0);
        pollenMap.put(playerName, currentAmount + amount);
    }

    private void takePollen(String playerName, int amount) {
        int currentAmount = pollenMap.getOrDefault(playerName, 0);
        int newAmount = Math.max(currentAmount - amount, 0);
        pollenMap.put(playerName, newAmount);
    }

    private void setPollen(String playerName, int amount) {
        pollenMap.put(playerName, amount);
    }
}
