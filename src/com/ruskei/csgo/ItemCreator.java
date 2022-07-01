package com.ruskei.csgo;

import com.ruskei.csgo.utils.ChatFormatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreator {

    public static ItemStack createItem(Material material, int amount, String displayName, String... loreString){

        ItemStack item;
        List<String> lore = new ArrayList();

        item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatFormatter.chat(displayName));
        for (String s : loreString) {
            lore.add(ChatFormatter.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
