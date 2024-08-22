package net.sanctuaryhosting.soundTestMenu.Core;

import net.sanctuaryhosting.soundTestMenu.Other.Utils;
import net.sanctuaryhosting.soundTestMenu.Other.XMaterial;
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

public class pListener implements Listener{
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        String title = ChatColor.stripColor(event.getView().getTitle());
        
        if (title.equalsIgnoreCase("ꜱᴇʟᴇᴄᴛ ᴀ ᴄᴀᴛᴇɢᴏʀʏ")) {
            event.setCancelled(true);
            
            if (item == null)
                return;
            if (item.getType().equals(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial())) {
                if (!item.hasItemMeta())
                    return;
                
                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = switch (name) {
                    case "ᴀʟʟ ꜱᴏᴜɴᴅꜱ" -> "ALL";
                    case "ᴀᴍʙɪᴇɴᴛ ꜱᴏᴜɴᴅꜱ" -> "AMBIENT";
                    case "ʙʟᴏᴄᴋ ꜱᴏᴜɴᴅꜱ" -> "BLOCK";
                    case "ᴇɴᴄʜᴀɴᴛ ꜱᴏᴜɴᴅ" -> "ENCHANT";
                    case "ᴇɴᴛɪᴛʏ ꜱᴏᴜɴᴅꜱ" -> "ENTITY";
                    case "ᴇᴠᴇɴᴛ ꜱᴏᴜɴᴅ" -> "EVENT";
                    case "ʙʟᴀɴᴋ ꜱᴏᴜɴᴅ" -> "INTENTIONALLY";
                    case "ɪᴛᴇᴍ ꜱᴏᴜɴᴅꜱ" -> "ITEM";
                    case "ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ" -> "MUSIC";
                    case "ᴘᴀʀᴛɪᴄʟᴇ ꜱᴏᴜɴᴅ" -> "PARTICLE";
                    case "ᴜɪ ꜱᴏᴜɴᴅꜱ" -> "UI";
                    case "ᴡᴇᴀᴛʜᴇʀ ꜱᴏᴜɴᴅꜱ" -> "WEATHER";
                    default -> "";
                };
                
                pHandler.openSound(player, category);
            }
            
            if (item.getType().equals(XMaterial.MUSIC_DISC_5.parseMaterial())) {
                if (!item.hasItemMeta())
                    return;
                
                pHandler.openSound(player, "ALL");
            }
            
            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta())
                    return;
                
                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                
                if (name.equalsIgnoreCase("sᴛᴏᴘ")) {
                    pHandler.stopSound(player);
                }
            }
        }
        
        if (title.contains("ᴘᴀɢᴇ")) {
            event.setCancelled(true);
            
            if (item == null)
                return;
            if (item.getType().equals(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial())) {
                if (!item.hasItemMeta())
                    return;
                
                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String sound = name.replace("Sound: ", "");
                
                pHandler.stopSound(player);
                
                if (event.getClick().name().equalsIgnoreCase("SHIFT_LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 2, 0.5F);
                } else if (event.getClick().name().equalsIgnoreCase("LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 2, 1);
                } else if (event.getClick().name().equalsIgnoreCase("SHIFT_RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 2, 1.5F);
                } else if (event.getClick().name().equalsIgnoreCase("RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 2, 2);
                } else if (event.getClick().name().equalsIgnoreCase("DROP")) {
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
            
            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta())
                    return;
                
                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = "";
                int page = Integer.parseInt(title.split(" ")[3]);
                
                if (title.contains("ᴀʟʟ")) {
                    category = "ALL";
                } else if (title.contains("ᴀᴍʙɪᴇɴᴛ")) {
                    category = "AMBIENT";
                } else if (title.contains("ʙʟᴏᴄᴋ")) {
                    category = "BLOCK";
                } else if (title.contains("ᴇɴᴄʜᴀɴᴛ")) {
                    category = "ENCHANT";
                } else if (title.contains("ᴇɴᴛɪᴛʏ")) {
                    category = "ENTITY";
                } else if (title.contains("ᴇᴠᴇɴᴛ")) {
                    category = "EVENT";
                } else if (title.contains("ʙʟᴀɴᴋ")) {
                    category = "INTENTIONALLY";
                } else if (title.contains("ɪᴛᴇᴍ")) {
                    category = "ITEM";
                } else if (title.contains("ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ")) {
                    category = "MUSIC";
                } else if (title.contains("ᴘᴀʀᴛɪᴄʟᴇ")) {
                    category = "AMBIENT";
                } else if (title.contains("ᴜɪ")) {
                    category = "AMBIENT";
                } else if (title.contains("ᴡᴇᴀᴛʜᴇʀ")) {
                    category = "AMBIENT";
                }
                
                if (name.equalsIgnoreCase("ꜱᴏᴜɴᴅ ᴛᴇꜱᴛ ᴍᴇɴᴜ")) {
                    pHandler.openMain(player);
                } else if (name.equalsIgnoreCase("ɴᴇxᴛ ᴘᴀɢᴇ")) {
                    pHandler.openSound(player, category, page + 1);
                } else if (name.equalsIgnoreCase("ᴘʀᴇᴠɪᴏᴜs ᴘᴀɢᴇ")) {
                    pHandler.openSound(player, category, page - 1);
                } else if (name.equalsIgnoreCase("ꜱᴛᴏᴘ")) {
                    pHandler.stopSound(player);
                }
            }
        }
    }
}