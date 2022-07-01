package com.ruskei.csgo;

import com.ruskei.csgo.commands.DebugCommands;
import com.ruskei.csgo.listeners.PlayerInteractListener;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public HashMap<RangedWeapon.WeaponID, RangedWeapon> validWeapons = new HashMap<>();

    public List<HitboxParent> hitboxParents = new ArrayList<>();

    @Override
    public void onEnable() {
        new PlayerInteractListener(this);
        new DebugCommands(this);

        initializeWeapons();

        this.getServer().getScheduler().runTaskTimer(this, () -> {
            for (HitboxParent parent : hitboxParents) {
                parent.updateBounds();
            }
        }, 0, 2);

        this.getServer().getScheduler().runTaskTimer(this, () -> {
            Bukkit.getOnlinePlayers().forEach(CustomHudDisplayer::displayAmmoInfo);
        }, 0, 10);
    }

    @Override
    public void onDisable() {

    }

    private void initializeWeapons() {
        RangedWeapon gun = new RangedWeapon("AK-47", null, RangedWeapon.WeaponID.AK47, 7.2f, 0.775f, 600, 30, 90, 0.86f, 0.6f, 0.33f, 0.02f);
        validWeapons.put(RangedWeapon.WeaponID.AK47, new RangedWeapon("AK-47", genGunItemStack(gun, Material.IRON_HOE), RangedWeapon.WeaponID.AK47, 7.2f, 0.775f, 600, 30, 90, 0.86f, 0.6f, 0.33f, 0.02f));
    }

    private ItemStack genGunItemStack(RangedWeapon gun, Material mat) {
        ItemStack gunItemStack = ItemCreator.createItem(mat, 1, gun.getName(),
                "&7Base Damage: " + gun.getBaseDmg(),
                "&7Firerate: " + gun.getFireRate());
        net.minecraft.world.item.ItemStack nmsGun = CraftItemStack.asNMSCopy(gunItemStack);
        NBTTagCompound gunTag = nmsGun.getTag() == null ? new NBTTagCompound() : nmsGun.getTag();
        gunTag.setString("type", gun.getType().toString());
        gunTag.setInt("mag", gun.getMagSize());
        gunTag.setInt("reserve", gun.getReserve());
        nmsGun.setTag(gunTag);

        return CraftItemStack.asBukkitCopy(nmsGun);
    }
}
