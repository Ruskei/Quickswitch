package com.ruskei.csgo.utils;

import org.bukkit.ChatColor;

public class ChatFormatter {

    public static String chat (String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
