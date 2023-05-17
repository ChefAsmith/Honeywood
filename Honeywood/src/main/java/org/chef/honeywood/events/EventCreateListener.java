package org.chef.honeywood.events;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventCreateListener implements Listener {

    private JavaPlugin plugin;

    public EventCreateListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].substring(1); // Get the command without the leading "/"

        if (command.equalsIgnoreCase("eventcreate") && player.hasPermission("honeywood.eventcreate")) {
            event.setCancelled(true); // Cancel the command

            // Get the message text from the config.yml file (assuming it's stored as "event_message")
            String messageText = plugin.getConfig().getString("event_message");

            // Split the message into multiple lines
            String[] lines = messageText.split("\\\\n"); // Use "\\n" to represent line breaks in the config file

            // Create the clickable message component
            TextComponent messageComponent = new TextComponent();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                messageComponent.addExtra(ChatColor.translateAlternateColorCodes('&', line));
                if (i < lines.length - 1) {
                    messageComponent.addExtra("\n");
                }
            }
            messageComponent.addExtra("\n" + ChatColor.GREEN + "Click to join");
            messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/eventjoin"));

            // Send the message to all players
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.spigot().sendMessage(messageComponent);
            }
        }
    }
}
