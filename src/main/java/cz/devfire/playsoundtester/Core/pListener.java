package cz.devfire.playsoundtester.Core;

import cz.devfire.playsoundtester.Other.Utils;
import cz.devfire.playsoundtester.Other.XMaterial;
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

        if (title.equalsIgnoreCase("Sounds categories")) {
            event.setCancelled(true);

            if (item == null) return;
            if (item.getType().equals(XMaterial.PAPER.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = name.replace("Category: ", "");

                pHandler.openSound(player, category);
            }

            if (item.getType().equals(XMaterial.MAP.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                pHandler.openSound(player, "ALL");
            }

            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());

                if (name.equalsIgnoreCase("Stop")) {
                    pHandler.stopSound(player);
                }
            }
        }

        if (title.contains("Category: ")) {
            event.setCancelled(true);

            if (item == null) return;
            if (item.getType().equals(XMaterial.PAPER.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String sound = name.replace("Sound: ", "");

                pHandler.stopSound(player);

                if (event.getClick().name().equalsIgnoreCase("MIDDLE")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,0);
                } else if (event.getClick().name().equalsIgnoreCase("SHIFT_LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,0.5F);
                } else if (event.getClick().name().equalsIgnoreCase("LEFT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,1);
                } else if (event.getClick().name().equalsIgnoreCase("SHIFT_RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,1.5F);
                } else if (event.getClick().name().equalsIgnoreCase("RIGHT")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound),1,2);
                } else if (event.getClick().name().equalsIgnoreCase("DROP")) {
                    if (Utils.getServerVersion().contains("v1_8")) {
                        player.sendMessage(Utils.cc("&c&lSoundTester &8&l» &7Sound: &e" + sound));
                    } else {
                        TextComponent text = new TextComponent();
                        text.setText(Utils.cc("&c&lSoundTester &8&l» "));
                        TextComponent text2 = new TextComponent();
                        text2.setText(Utils.cc("&7Click to copy &e" + sound));
                        text2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fClick to copy").create()));
                        text2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sound));
                        text.addExtra(text2);

                        player.spigot().sendMessage(text);
                    }
                }
            }

            if (item.getType().equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
                if (!item.hasItemMeta()) return;

                String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String category = title.split(" ")[1];
                Integer page = Integer.parseInt(title.split(" ")[4].split("/")[0]);

                if (name.equalsIgnoreCase("Back to Categories")) {
                    pHandler.openMain(player);
                } else if (name.equalsIgnoreCase("Next page")) {
                    pHandler.openSound(player, category,page+1);
                } else if (name.equalsIgnoreCase("Previous page")) {
                    pHandler.openSound(player, category,page-1);
                } else if (name.equalsIgnoreCase("Stop")) {
                    pHandler.stopSound(player);
                }
            }
        }
    }
}
