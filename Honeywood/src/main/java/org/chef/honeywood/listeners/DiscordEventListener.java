package org.chef.honeywood.listeners;

import org.chef.honeywood.discord.DiscordWebhook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class DiscordEventListener implements Listener {

    private final Logger logger;

    public DiscordEventListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        int playerCount = Bukkit.getOnlinePlayers().size();
        String webhookURL = "https://discord.com/api/webhooks/1101028812512297002/gMwWnuKtn1KdQVVtq-sAjw-XLz1xuYUghJLSsTHg_DZUXnlLXqfJGHzPEgZZooc8SsS2";
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        Color color = new Color(0, 255, 0);
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                .setDescription("**" + player.getName() + "** has joined the server! (" + playerCount + "/" + Bukkit.getMaxPlayers() + ")")
                .setAuthor("Honeywood", "", "")
                .setColor(color);
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException ex) {
            logger.severe(Arrays.toString(ex.getStackTrace()));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        int playerCount = Bukkit.getOnlinePlayers().size() - 1;
        String webhookURL = "https://discord.com/api/webhooks/1101028812512297002/gMwWnuKtn1KdQVVtq-sAjw-XLz1xuYUghJLSsTHg_DZUXnlLXqfJGHzPEgZZooc8SsS2";
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        Color color = new Color(255, 51, 0);
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                .setDescription("**" + player.getName() + "** has left the server... :C (" + playerCount + "/" + Bukkit.getMaxPlayers() + ")")
                .setAuthor("Honeywood", "", "")
                .setColor(color);
        webhook.addEmbed(embed);
        try {
            webhook.execute();
        } catch (IOException ex) {
            logger.severe(Arrays.toString(ex.getStackTrace()));
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String eventMessage = e.getMessage();
        Player player = e.getPlayer();
        String webhookURL = "https://discord.com/api/webhooks/1101028812512297002/gMwWnuKtn1KdQVVtq-sAjw-XLz1xuYUghJLSsTHg_DZUXnlLXqfJGHzPEgZZooc8SsS2";
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        eventMessage = eventMessage.replaceAll("@everyone", "Trying to ping are ya?").replaceAll("@here", "Trying to ping are ya?");
        webhook.setContent("**" + player.getName() + "** >> " + eventMessage);
        try {
            webhook.execute();
        } catch (IOException ex) {
            logger.severe(Arrays.toString(ex.getStackTrace()));
        }
    }
}
