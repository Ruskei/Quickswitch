package com.ruskei.csgo;

import com.ruskei.csgo.utils.SpaceFormatter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class CustomHudDisplayer {

    public static void displayAmmoInfo(Player p) {
        if (CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand()) != null) {
            ItemStack nmsGun = CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand());
            NBTTagCompound tag = nmsGun.getTag();

            if (tag != null) {
                if (tag.getString("type") != null) {
                    String mag = "";
                    String reserve = "";
                    if (tag.getInt("mag") < 10) mag = SpaceFormatter.formatSpaces("{" + String.valueOf(tag.getInt("mag")).charAt(0) + "}");
                    else mag = SpaceFormatter.formatSpaces("{" + String.valueOf(tag.getInt("mag")).charAt(0) + "}{" + String.valueOf(tag.getInt("mag")).charAt(1) + "}");

                    if (tag.getInt("reserve") < 10) reserve = SpaceFormatter.formatSpaces("{" + String.valueOf(tag.getInt("reserve")).charAt(0) + "}");
                    else reserve = SpaceFormatter.formatSpaces("{" + String.valueOf(tag.getInt("reserve")).charAt(0) + "}{" + String.valueOf(tag.getInt("reserve")).charAt(1) + "}");

                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(SpaceFormatter.formatSpaces("{space256}{space128}{space64}{space16}" + mag + "{slash}" + reserve)));
                }
            }
        }
    }
}
