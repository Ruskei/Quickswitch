package com.ruskei.csgo.commands;

import com.ruskei.csgo.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DebugCommands implements CommandExecutor {

    private Main plugin;
    public static BukkitTask testTask;

    public DebugCommands(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("quickswitch").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && args.length > 0) {
            Player p = (Player) commandSender;

            if (args[0].equalsIgnoreCase("dummy")) {
                LivingEntity stand = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                ((ArmorStand) stand).setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
                ((ArmorStand) stand).setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                ((ArmorStand) stand).setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                ((ArmorStand) stand).setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
                HitboxParent parent = new HitboxParent(new ArrayList<>());
                Hitbox head = new Hitbox(parent, new Vector(0, 0, 0), new Vector(0, 0, 0), stand, BodyPart.HEAD, new Vector(-0.25, 1.5, -0.25), new Vector(0.25, 2, 0.25));
                Hitbox chest = new Hitbox(parent, new Vector(0, 0, 0), new Vector(0, 0, 0), stand, BodyPart.CHEST, new Vector(-0.25, 1.125, -0.125), new Vector(0.25, 1.5, 0.125));
                Hitbox gut = new Hitbox(parent, new Vector(0, 0, 0), new Vector(0, 0, 0), stand, BodyPart.GUT, new Vector(-0.25, 0.75, -0.125), new Vector(0.25, 1.125, 0.125));
                Hitbox legs = new Hitbox(parent, new Vector(0, 0, 0), new Vector(0, 0, 0), stand, BodyPart.LEGS, new Vector(-0.25, 0, -0.125), new Vector(0.25, 0.75, 0.125));

                parent.getHitboxes().add(head);
                parent.getHitboxes().add(chest);
                parent.getHitboxes().add(gut);
                parent.getHitboxes().add(legs);

                plugin.hitboxParents.add(parent);



                return true;
            } else if (args[0].equalsIgnoreCase("give")) {
                if (args.length != 2) {
                    return false;
                }

                try {
                    p.getInventory().addItem(plugin.validWeapons.get(RangedWeapon.WeaponID.valueOf(args[1])).getItemStack());
                } catch (IllegalArgumentException e) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }
}
