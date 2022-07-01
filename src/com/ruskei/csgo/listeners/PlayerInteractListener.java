package com.ruskei.csgo.listeners;

import com.ruskei.csgo.*;
import com.ruskei.csgo.utils.RayTrace;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.scheduler.BukkitTask;

public class PlayerInteractListener implements Listener {

    private Main plugin;

    private boolean isHolding = false;
    private BukkitTask holdTask = null;
    private BukkitTask firingTask = null;

    public PlayerInteractListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand()) != null) {
            ItemStack nmsGun = CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand());
            NBTTagCompound tag = nmsGun.getTag();

            if (tag != null) {
                if (tag.getString("type") != null) {
                    RangedWeapon gun = plugin.validWeapons.get(RangedWeapon.WeaponID.valueOf(tag.getString("type")));

                    if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (tag.getInt("mag") != gun.getMagSize() && tag.getInt("reserve") != 0) {
                            int preReload = tag.getInt("mag");
                            tag.setInt("mag", tag.getInt("mag") + Math.min(gun.getMagSize() - tag.getInt("mag"), tag.getInt("reserve")));
                            tag.setInt("reserve", tag.getInt("reserve") - Math.min(gun.getMagSize() - preReload, tag.getInt("reserve")));

                            CustomHudDisplayer.displayAmmoInfo(p);
                        }

                        p.getInventory().setItemInMainHand(CraftItemStack.asBukkitCopy(nmsGun));
                    } else {
                        if (holdTask != null) {
                            holdTask.cancel();
                            isHolding = true;
                        }

                        if (firingTask == null) {
                            if (!isHolding) {
                                if (tag.getInt("mag") == 0) {
                                    tag.setInt("mag", Math.min(tag.getInt("reserve"), gun.getMagSize()));
                                    tag.setInt("reserve", tag.getInt("reserve") - Math.min(tag.getInt("reserve"), gun.getMagSize()));
                                } else {
                                    RayTrace bullet = new RayTrace(p, new Projectile(p, gun));
                                    bullet.shoot(p, 40, 0.2f, plugin);

                                    tag.setInt("mag", tag.getInt("mag") - 1);
                                }

                                p.getInventory().setItemInMainHand(CraftItemStack.asBukkitCopy(nmsGun));
                                CustomHudDisplayer.displayAmmoInfo(p);
                            } else {
                                firingTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                                    if (tag.getInt("mag") == 0) {
                                        tag.setInt("mag", Math.min(tag.getInt("reserve"), gun.getMagSize()));
                                        tag.setInt("reserve", tag.getInt("reserve") - Math.min(tag.getInt("reserve"), gun.getMagSize()));
                                    } else {
                                        RayTrace bullet = new RayTrace(p, new Projectile(p, gun));
                                        bullet.shoot(p, 40, 0.2f, plugin);

                                        tag.setInt("mag", tag.getInt("mag") - 1);
                                    }

                                    p.getInventory().setItemInMainHand(CraftItemStack.asBukkitCopy(nmsGun));
                                    CustomHudDisplayer.displayAmmoInfo(p);
                                }, 0, (long) Math.ceil(20 / (gun.getFireRate() / 60)));
                            }
                        }

                        holdTask = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            isHolding = false;
                            holdTask = null;

                            if (firingTask != null) firingTask.cancel();
                            firingTask = null;
                        }, 5);
                    }
                }
            }
        }
    }
}
