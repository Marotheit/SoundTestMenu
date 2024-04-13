package com.marotheit.soundtestmenu.Core;

import com.marotheit.soundtestmenu.Other.Utils;
import com.marotheit.soundtestmenu.Other.XMaterial;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class pListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        String title = ChatColor.stripColor(event.getView().getTitle());

        if (title.equalsIgnoreCase("ꜱᴇʟᴇᴄᴛ ᴀ ᴄᴀᴛᴇɢᴏʀʏ")) {
            event.setCancelled(true);

            if (item == null) return;
            if (item.getType().equals(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = "";
                switch (name) {
                    case "ᴀʟʟ ꜱᴏᴜɴᴅꜱ":
                        category = "ALL";
                        break;
                    case "ᴀᴍʙɪᴇɴᴛ ꜱᴏᴜɴᴅꜱ":
                        category = "AMBIENT";
                        break;
                    case "ʙʟᴏᴄᴋ ꜱᴏᴜɴᴅꜱ":
                        category = "BLOCK";
                        break;
                    case "ᴇɴᴄʜᴀɴᴛ ꜱᴏᴜɴᴅ":
                        category = "ENCHANT";
                        break;
                    case "ᴇɴᴛɪᴛʏ ꜱᴏᴜɴᴅꜱ":
                        category = "ENTITY";
                        break;
                    case "ᴇᴠᴇɴᴛ ꜱᴏᴜɴᴅ":
                        category = "EVENT";
                        break;
                    case "ʙʟᴀɴᴋ ꜱᴏᴜɴᴅ":
                        category = "INTENTIONALLY";
                        break;
                    case "ɪᴛᴇᴍ ꜱᴏᴜɴᴅꜱ":
                        category = "ITEM";
                        break;
                    case "ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ":
                        category = "MUSIC";
                        break;
                    case "ᴘᴀʀᴛɪᴄʟᴇ ꜱᴏᴜɴᴅ":
                        category = "PARTICLE";
                        break;
                    case "ᴜɪ ꜱᴏᴜɴᴅꜱ":
                        category = "UI";
                        break;
                    case "ᴡᴇᴀᴛʜᴇʀ ꜱᴏᴜɴᴅꜱ":
                        category = "WEATHER";
                        break;
                }

                pHandler.openSound(player, category);
            }

            if (item.getType().equals(XMaterial.MUSIC_DISC_5.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                pHandler.openSound(player, "ALL");
            }

            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());

                if (name.equalsIgnoreCase("sᴛᴏᴘ")) {
                    pHandler.stopSound(player);
                }
            }
        }

        if (title.contains("ᴘᴀɢᴇ")) {
            event.setCancelled(true);

            if (item == null) return;
            if (item.getType().equals(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String sound = name.replace("Sound: ", "");

                pHandler.stopSound(player);

                if (event.getClick().name().equalsIgnoreCase("SHIFT_LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,0.5F);
                } else if (event.getClick().name().equalsIgnoreCase("LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,1);
                } else if (event.getClick().name().equalsIgnoreCase("SHIFT_RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,1.5F);
                } else if (event.getClick().name().equalsIgnoreCase("RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,2);
                } else if (event.getClick().name().equalsIgnoreCase("DROP")) {
                    if (Utils.getServerVersion().contains("v1_8")) {
                        player.sendMessage(Utils.cc("&7[&3SoundTestMenu&7] &f" + sound));
                    } else {
                        TextComponent text = new TextComponent();
                        text.setText(Utils.cc("&7[&3SoundTestMenu&7] "));
                        TextComponent text2 = new TextComponent();
                        text2.setText(Utils.cc("&f" + sound));
                        text2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fClick to copy.").create()));
                        text2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sound));
                        text.addExtra(text2);

                        player.spigot().sendMessage(text);
                    }
                }
            }

            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = "";
                int page = Integer.parseInt(title.split(" ")[3]);

                if (title.contains("ᴀʟʟ")){
                    category = "ALL";
                } else if (title.contains("ᴀᴍʙɪᴇɴᴛ")){
                    category = "AMBIENT";
                } else if (title.contains("ʙʟᴏᴄᴋ")){
                    category = "BLOCK";
                } else if (title.contains("ᴇɴᴄʜᴀɴᴛ")){
                    category = "ENCHANT";
                } else if (title.contains("ᴇɴᴛɪᴛʏ")){
                    category = "ENTITY";
                } else if (title.contains("ᴇᴠᴇɴᴛ")){
                    category = "EVENT";
                } else if (title.contains("ʙʟᴀɴᴋ")){
                    category = "INTENTIONALLY";
                } else if (title.contains("ɪᴛᴇᴍ")){
                    category = "ITEM";
                } else if (title.contains("ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ")){
                    category = "MUSIC";
                } else if (title.contains("ᴘᴀʀᴛɪᴄʟᴇ")){
                    category = "AMBIENT";
                } else if (title.contains("ᴜɪ")){
                    category = "AMBIENT";
                } else if (title.contains("ᴡᴇᴀᴛʜᴇʀ")){
                    category = "AMBIENT";
                }

                if (name.equalsIgnoreCase("ꜱᴏᴜɴᴅ ᴛᴇꜱᴛ ᴍᴇɴᴜ")) {
                    pHandler.openMain(player);
                } else if (name.equalsIgnoreCase("ɴᴇxᴛ ᴘᴀɢᴇ")) {
                    pHandler.openSound(player, category,page+1);
                } else if (name.equalsIgnoreCase("ᴘʀᴇᴠɪᴏᴜs ᴘᴀɢᴇ")) {
                    pHandler.openSound(player, category,page-1);
                } else if (name.equalsIgnoreCase("ꜱᴛᴏᴘ")) {
                    pHandler.stopSound(player);
                }
            }
        }
    }
}