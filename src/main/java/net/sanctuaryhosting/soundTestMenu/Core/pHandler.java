package net.sanctuaryhosting.soundTestMenu.Core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sanctuaryhosting.soundTestMenu.Other.Utils;
import net.sanctuaryhosting.soundTestMenu.Other.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

public class pHandler{
    public static Inventory main_menu;
    public static Inventory sound_menu;
    public static ArrayList<Sound> sounds = Lists.newArrayList(Sound.values());
    public static HashMap<String, List<String>> categories = Maps.newHashMap();
    
    public static void init() {
        Inventory inv;
        ItemStack disc = new ItemStack(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial());
        ItemMeta discMeta = disc.getItemMeta();
        ItemStack allsoundsdisc = new ItemStack(XMaterial.MUSIC_DISC_5.parseMaterial());
        ItemMeta allsoundsdiscMeta = allsoundsdisc.getItemMeta();
        ItemStack blank = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial(), 1, (short) 15);
        ItemMeta blankMeta = blank.getItemMeta();
        int suma = 0;
        
        // List
        categories.put("ALL", Lists.newArrayList());
        for (Sound sound : sounds) {
            String[] s = sound.name().split("_");
            
            if (s.length != 1) {
                if (!categories.containsKey(s[0])) {
                    categories.put(s[0], Lists.newArrayList());
                }
                
                categories.get(s[0]).add(sound.name());
            }
            
            categories.get("ALL").add(sound.name());
        }
        
        // Categories menu
        inv = Bukkit.createInventory(null, 54, "ꜱᴇʟᴇᴄᴛ ᴀ ᴄᴀᴛᴇɢᴏʀʏ");
        
        assert blankMeta != null;
        blankMeta.setDisplayName("§r");
        blank.setItemMeta(blankMeta);
        
        for (int x = 0; x < 54; x++) {
            if (x <= 2 || x == 4 || x >= 6 && x <= 19 || x >= 25 && x <= 28 || x >= 34) {
                inv.setItem(x, blank);
            }
        }
        
        for (String category : categories.keySet()) {
            if (category.equalsIgnoreCase("ALL"))
                continue;
            
            String categoryName = "";
            int categoryPos = 0;
            categoryName = switch (category) {
                case "ALL" -> {
                    categoryPos = 3;
                    yield "ᴀʟʟ ꜱᴏᴜɴᴅꜱ";
                }
                case "AMBIENT" -> {
                    categoryPos = 20;
                    yield "ᴀᴍʙɪᴇɴᴛ ꜱᴏᴜɴᴅꜱ";
                }
                case "BLOCK" -> {
                    categoryPos = 21;
                    yield "ʙʟᴏᴄᴋ ꜱᴏᴜɴᴅꜱ";
                }
                case "ENCHANT" -> {
                    categoryPos = 22;
                    yield "ᴇɴᴄʜᴀɴᴛ ꜱᴏᴜɴᴅ";
                }
                case "ENTITY" -> {
                    categoryPos = 23;
                    yield "ᴇɴᴛɪᴛʏ ꜱᴏᴜɴᴅꜱ";
                }
                case "EVENT" -> {
                    categoryPos = 24;
                    yield "ᴇᴠᴇɴᴛ ꜱᴏᴜɴᴅ";
                }
                case "INTENTIONALLY" -> {
                    categoryPos = 5;
                    yield "ʙʟᴀɴᴋ ꜱᴏᴜɴᴅ";
                }
                case "ITEM" -> {
                    categoryPos = 29;
                    yield "ɪᴛᴇᴍ ꜱᴏᴜɴᴅꜱ";
                }
                case "MUSIC" -> {
                    categoryPos = 30;
                    yield "ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ";
                }
                case "PARTICLE" -> {
                    categoryPos = 31;
                    yield "ᴘᴀʀᴛɪᴄʟᴇ ꜱᴏᴜɴᴅ";
                }
                case "UI" -> {
                    categoryPos = 32;
                    yield "ᴜɪ ꜱᴏᴜɴᴅꜱ";
                }
                case "WEATHER" -> {
                    categoryPos = 33;
                    yield "ᴡᴇᴀᴛʜᴇʀ ꜱᴏᴜɴᴅꜱ";
                }
                default -> categoryName;
            };
            
            assert discMeta != null;
            discMeta.setDisplayName(Utils.cc("&6" + categoryName));
            discMeta.setLore(Utils.ccl(Arrays.asList("&r", "&5This category contains &6" + categories.get(category).size() + "&5 sounds.", "", "&8» &6Left-Click &5to select this category.")));
            disc.setItemMeta(discMeta);
            
            inv.setItem(categoryPos, disc);
            
            suma += categories.get(category).size();
        }
        
        assert allsoundsdiscMeta != null;
        allsoundsdiscMeta.setDisplayName(Utils.cc("&6ᴀʟʟ ꜱᴏᴜɴᴅꜱ"));
        allsoundsdiscMeta.setLore(Utils.ccl(Arrays.asList("&r", "&5This category contains &6" + suma + "&5 sounds.", "", "&8» &6Left-Click &5to select this category.")));
        allsoundsdisc.setItemMeta(allsoundsdiscMeta);
        inv.setItem(3, allsoundsdisc);
        
        main_menu = inv;
        
        // Sound menu
        inv = Bukkit.createInventory(null, 54);
        
        for (int i = 0; i < 54; i++) {
            if (i >= 45) {
                inv.setItem(i, blank);
            }
        }
        
        ItemStack stop = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjk1M2IxMmEwOTQ2YjYyOWI0YzA4ODlkNDFmZDI2ZWQyNmZiNzI5ZDRkNTE0YjU5NzI3MTI0YzM3YmI3MGQ4ZCJ9fX0=");
        ItemMeta stopMeta = stop.getItemMeta();
        assert stopMeta != null;
        stopMeta.setDisplayName(Utils.cc("&cꜱᴛᴏᴘ"));
        stopMeta.setLore(Utils.ccl(Arrays.asList("", "&8» &6Click &5to &cstop &5playing sounds.")));
        stop.setItemMeta(stopMeta);
        inv.setItem(50, stop);
        
        sound_menu = inv;
    }
    
    public static void stopSound(Player player) {
        for (Sound sound : Sound.values()) {
            player.stopSound(sound);
        }
    }
    
    public static void openMain(Player player) {
        player.openInventory(main_menu);
    }
    
    public static void openSound(Player player, String category) {
        openSound(player, category, 1);
    }
    
    public static void openSound(Player player, String category, int page) {
        String categoryName = switch (category) {
            case "ALL" -> "ᴀʟʟ ꜱᴏᴜɴᴅꜱ";
            case "INTENTIONALLY" -> "ʙʟᴀɴᴋ ꜱᴏᴜɴᴅ";
            case "AMBIENT" -> "ᴀᴍʙɪᴇɴᴛ ꜱᴏᴜɴᴅꜱ";
            case "BLOCK" -> "ʙʟᴏᴄᴋ ꜱᴏᴜɴᴅꜱ";
            case "ENCHANT" -> "ᴇɴᴄʜᴀɴᴛ ꜱᴏᴜɴᴅ";
            case "ENTITY" -> "ᴇɴᴛɪᴛʏ ꜱᴏᴜɴᴅꜱ";
            case "EVENT" -> "ᴇᴠᴇɴᴛ ꜱᴏᴜɴᴅ";
            case "ITEM" -> "ɪᴛᴇᴍ ꜱᴏᴜɴᴅꜱ";
            case "MUSIC" -> "ᴍᴜꜱɪᴄ ᴛʀᴀᴄᴋꜱ";
            case "PARTICLE" -> "ᴘᴀʀᴛɪᴄʟᴇ ꜱᴏᴜɴᴅ";
            case "UI" -> "ᴜɪ ꜱᴏᴜɴᴅꜱ";
            case "WEATHER" -> "ᴡᴇᴀᴛʜᴇʀ ꜱᴏᴜɴᴅꜱ";
            default -> "";
        };
        int max_page = Integer.parseInt(String.format("%.0f", (double) (categories.get(category).size() / 45) + 1));
        Inventory inv = Bukkit.createInventory(null, 54, Utils.cc(categoryName + ": ᴘᴀɢᴇ " + page + " ᴏꜰ " + max_page));
        inv.setContents(sound_menu.getContents());
        
        int end = 45 * page;
        int start = end - 45;
        int now = 0;
        int position = 0;
        
        if (end > categories.get(category).size()) {
            end = categories.get(category).size();
        }
        
        for (String sound : categories.get(category)) {
            if (start <= now && now < end) {
                ItemStack disc = new ItemStack(XMaterial.MUSIC_DISC_OTHERSIDE.parseMaterial());
                ItemMeta discMeta = disc.getItemMeta();
                
                assert discMeta != null;
                discMeta.setDisplayName(Utils.cc("&6" + sound));
                discMeta.setLore(Utils.ccl(Arrays.asList("", "&8» &6Shift+Left-Click &5to play this sound with a pitch of &60.5&5.", "&8» &6Left-Click &5to play this sound with a pitch of &61&5.", "&8» &6Shift+Right-Click &5to play this sound with a pitch of &61.5&5.", "&8» &6Right-Click &5to play this sound with a pitch of &62&5.", "&8» &6Q &5to print this sound name into the chat box.")));
                disc.setItemMeta(discMeta);
                
                inv.setItem(position, disc);
                position++;
            }
            
            now++;
        }
        
        ItemStack home = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTJkN2E3NTFlYjA3MWUwOGRiYmM5NWJjNWQ5ZDY2ZTVmNTFkYzY3MTI2NDBhZDJkZmEwM2RlZmJiNjhhN2YzYSJ9fX0=");
        ItemMeta homeMeta = home.getItemMeta();
        assert homeMeta != null;
        homeMeta.setDisplayName(Utils.cc("&6ꜱᴏᴜɴᴅ ᴛᴇꜱᴛ ᴍᴇɴᴜ"));
        home.setItemMeta(homeMeta);
        inv.setItem(48, home);
        
        ItemStack less = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVkOWQ1YzJiNDgwNzA1OGQ5ODdjNmUxZDYzMDBhMWNjNGI5ZWVlN2IxNmYxZjBhY2FjMTRmZmNkMWE5Njk5ZiJ9fX0=");
        ItemMeta lessMeta = less.getItemMeta();
        assert lessMeta != null;
        lessMeta.setDisplayName(Utils.cc("&6ᴘʀᴇᴠɪᴏᴜs ᴘᴀɢᴇ"));
        less.setItemMeta(lessMeta);
        
        ItemStack more = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg3YmFhNDc2NzIzNGMwMWMwNGI4YmJlYjUxOGEwNTNkY2U3MzlmNGEwNDM1OGE0MjQzMDJmYjRhMDE3MmY4In19fQ==");
        ItemMeta moreMeta = more.getItemMeta();
        assert moreMeta != null;
        moreMeta.setDisplayName(Utils.cc("&6ɴᴇxᴛ ᴘᴀɢᴇ"));
        more.setItemMeta(moreMeta);
        
        ItemStack blank = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial(), 1, (short) 15);
        ItemMeta blankMeta = blank.getItemMeta();
        assert blankMeta != null;
        blankMeta.setDisplayName(Utils.cc("&r"));
        blank.setItemMeta(blankMeta);
        
        if (max_page == 1) {
            inv.setItem(45, blank);
            inv.setItem(53, blank);
        } else if (page < max_page && page == 1) {
            inv.setItem(45, blank);
            inv.setItem(53, more);
        } else if (page < max_page) {
            inv.setItem(45, less);
            inv.setItem(53, more);
        } else if (page == max_page) {
            inv.setItem(45, less);
            inv.setItem(53, blank);
        } else {
            inv.setItem(45, less);
            inv.setItem(53, more);
        }
        
        player.openInventory(inv);
    }
}