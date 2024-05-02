package com.marotheit.soundtestmenu.Other;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class Utils{
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
    
    public static ItemStack getHead(String value) {
        ItemStack skull = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial());
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        int[] intArray = uuidToIntArray(hashAsId);
        return Bukkit.getUnsafe().modifyItemStack(skull, String.format("minecraft:player_head[minecraft:profile={id: [I;" + intArray[0] + "," + intArray[1] + "," + intArray[2] + "," + intArray[3] + "], properties: [{name: \"textures\", value: \"" + value + "\"}]}]"));
    }
    
    private static int[] uuidToIntArray(UUID uuid) {
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        
        int[] intArray = new int[4];
        intArray[0] = (int) (mostSignificantBits >> 32);
        intArray[1] = (int) mostSignificantBits;
        intArray[2] = (int) (leastSignificantBits >> 32);
        intArray[3] = (int) leastSignificantBits;
        
        return intArray;
    }
    
}