package cz.devfire.playsoundtester.Core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cz.devfire.playsoundtester.Other.Utils;
import cz.devfire.playsoundtester.Other.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class pHandler {
    public static Inventory main_menu;
    public static Inventory sound_menu;
    public static ArrayList<Sound> sounds = Lists.newArrayList(Sound.values());
    public static HashMap<String, List<String>> categories = Maps.newHashMap();

    public static void init() {
        Inventory inv;
        ItemStack paper = new ItemStack(XMaterial.PAPER.parseMaterial());
        ItemMeta paperMeta = paper.getItemMeta();
        ItemStack map = new ItemStack(XMaterial.MAP.parseMaterial());
        ItemMeta mapMeta = map.getItemMeta();
        ItemStack blank = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial(), 1, (short) 15);
        ItemMeta blankMeta = blank.getItemMeta();
        Integer pos = 0;
        Integer suma = 0;

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

        // Categories init
        if (!Utils.getServerVersion().contains("v1_8")) {

            // Categories menu
            inv = Bukkit.createInventory(null, 45, "Sounds categories");
            pos = 9;

            blankMeta.setDisplayName("§r");
            blank.setItemMeta(blankMeta);

            for (Integer x = 0; x < 45; x++) {
                if (x <= 8) {
                    inv.setItem(x, blank);
                }

                if (x >= 36) {
                    inv.setItem(x, blank);
                }
            }

            for (String category : categories.keySet()) {
                if (category.equalsIgnoreCase("ALL")) continue;

                paperMeta.setDisplayName(Utils.cc("&fCategory: &f&n"+ category));
                paperMeta.setLore(Utils.ccl(Arrays.asList("&r","&f&l» &7Click to open this sound category","&f&l» &7This category contains: &e"+ categories.get(category).size() +"&7 sounds")));
                paper.setItemMeta(paperMeta);

                inv.setItem(pos, paper);
                pos++;

                suma += categories.get(category).size();
            }

            mapMeta.setDisplayName(Utils.cc("&fAll categories"));
            mapMeta.setLore(Utils.ccl(Arrays.asList("&r","&f&l» &7Click to open this sound category","&f&l» &7This category contains: &e"+ suma +"&7 sounds")));
            map.setItemMeta(mapMeta);
            inv.setItem(pos, map);
            pos++;

            ItemStack stop = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==");
            ItemMeta stopMeta = stop.getItemMeta();
            stopMeta.setDisplayName(Utils.cc("&c&lStop"));
            stopMeta.setLore(Utils.ccl(Arrays.asList("","&f&l» &7Stop playing sounds")));
            stop.setItemMeta(stopMeta);
            inv.setItem(40, stop);

            main_menu = inv;
        }

        // Sound menu
        inv = Bukkit.createInventory(null, 54);

        for (Integer i = 0; i < 54; i++) {
            if (i >= 45) {
                inv.setItem(i, blank);
            }
        }

        if (!Utils.getServerVersion().contains("v1_8")) {
            ItemStack stop = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==");
            ItemMeta stopMeta = stop.getItemMeta();
            stopMeta.setDisplayName(Utils.cc("&c&lStop"));
            stopMeta.setLore(Utils.ccl(Arrays.asList("", "&f&l» &7Stop playing sounds")));
            stop.setItemMeta(stopMeta);
            inv.setItem(49, stop);
        }

        sound_menu = inv;
    }

    public static void stopSound(Player player) {
        if (!Utils.getServerVersion().contains("v1_8")) {
            for (Sound sound : Sound.values()) {
                player.stopSound(sound);
            }
        }

        return;
    }

    public static void openMain(Player player) {
        player.openInventory(main_menu);
    }

    public static void openSound(Player player, String category) {
        openSound(player, category, 1);
    }

    public static void openSound(Player player, String category, Integer page) {
        Integer max_page = Integer.parseInt(String.format("%.0f", Math.floor(categories.get(category).size()/45)+1));
        Inventory inv = Bukkit.createInventory(null,54, Utils.cc("Category&0: &c"+ category +" &0- &8Page: "+ page +"/"+ max_page));
        inv.setContents(sound_menu.getContents());

        Integer end = 45 * page;
        Integer start = end - 45;
        Integer now = 0;
        Integer position = 0;

        if (end > categories.get(category).size()) {
            end = categories.get(category).size();
        }

        for (String sound : categories.get(category)) {
            if (start <= now && now < end) {
                ItemStack paper = new ItemStack(XMaterial.PAPER.parseMaterial());
                ItemMeta paperMeta = paper.getItemMeta();

                paperMeta.setDisplayName(Utils.cc("&rSound: &n" + sound));
                paperMeta.setLore(Utils.ccl(Arrays.asList(
                        "",
                        "&f&lPress",
                        " &f&l» &eMIDDLE        CLICK &8- &7Plays sound with pitch 0 ",
                        " &f&l» &eLEFT+SHIFT  CLICK &8- &7Plays sound with pitch 0.5 ",
                        " &f&l» &eLEFT        &l  &eCLICK &8- &7Plays sound with pitch 1 ",
                        " &f&l» &eRIGHT+SHIFT CLICK &8- &7Plays sound with pitch 1.5 ",
                        " &f&l» &eRIGHT       &l  &eCLICK &8- &7Plays sound with pitch 2 ",
                        "",
                        " &f&l» &eQ &8- &7Print sound name into chat box"
                )));
                paper.setItemMeta(paperMeta);

                inv.setItem(position, paper);
                position++;
            }

            now++;
        }

        if (!Utils.getServerVersion().contains("v1_8")) {
            ItemStack home = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzVhMzViNWNhMTUyNjg2ODVjNDY2MDUzNWU1ODgzZDIxYTVlYzU3YzU1ZDM5NzIzNDI2OWFjYjVkYzI5NTRmIn19fQ==");
            ItemMeta homeMeta = home.getItemMeta();
            homeMeta.setDisplayName(Utils.cc("&f&lBack to Categories"));
            homeMeta.setLore(Utils.ccl(Arrays.asList("", "&f&l» &7Open main menu")));
            home.setItemMeta(homeMeta);
            inv.setItem(45, home);
        }

        ItemStack less = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=");
        ItemMeta lessMeta = less.getItemMeta();
        lessMeta.setDisplayName(Utils.cc("&f&lPrevious page"));
        less.setItemMeta(lessMeta);

        ItemStack more = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19");
        ItemMeta moreMeta = more.getItemMeta();
        moreMeta.setDisplayName(Utils.cc("&f&lNext page"));
        more.setItemMeta(moreMeta);

        ItemStack blank = Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ0ZjE4N2Y0MWNhZTY0MTU1OGY4Nzg3YmYxZTdiZTcyYTZkNzI5MTFiMjFjOTdkOTE2ZjBhN2ZhYWYyOGY3In19fQ==");
        ItemMeta blankMeta = blank.getItemMeta();
        blankMeta.setDisplayName(Utils.cc("&r"));
        blank.setItemMeta(blankMeta);

        if (max_page == 1) {
            inv.setItem(52, blank);
            inv.setItem(53, blank);
        } else if (page < max_page && page == 1) {
            inv.setItem(52, blank);
            inv.setItem(53, more);
        } else if (page < max_page) {
            inv.setItem(52, less);
            inv.setItem(53, more);
        } else if (page == max_page) {
            inv.setItem(52, less);
            inv.setItem(53, blank);
        } else {
            inv.setItem(52, less);
            inv.setItem(53, more);
        }

        player.openInventory(inv);
    }
}
