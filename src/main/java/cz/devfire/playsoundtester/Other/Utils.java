package cz.devfire.playsoundtester.Other;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class Utils {
    public static String cc(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> ccl(List<String> stringList) {
        List<String> list = Lists.newArrayList();

        for (String s : stringList) {
            list.add(cc(s));
        }

        return list;
    }

    public static String getServerVersion() {
        String version = null;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (Exception e) {
            try {
                version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[1];
            } catch (Exception e2) { /* */ }
        }

        return version;
    }

    public static ItemStack getHead(String value) {
        if (Utils.getServerVersion().contains("v1_8") || Utils.getServerVersion().contains("v1_12")) {
            return getCustomTextureHead(value);
        } else {
            ItemStack skull = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial());
            UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
            return Bukkit.getUnsafe().modifyItemStack(skull,
                    "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
            );
        }
    }

    public static ItemStack getCustomTextureHead(String value) {
        ItemStack head = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }
}
