package com.ruskei.csgo;

import org.bukkit.inventory.ItemStack;

public class RangedWeapon {

    private final String name;
    private ItemStack itemStack;
    private WeaponID type;
    private final float baseDmg;
    private final float penetration;
    private final float fireRate;
    private final int magSize;
    private final int reserve;
    private final float mobility;
    private final float taggingPower;
    private final float tracers;
    private final float damageFalloff;

    public enum WeaponID {
        AK47
    }

    public RangedWeapon(String name, ItemStack itemStack, WeaponID type, float baseDmg, float penetration, float fireRate, int magSize, int reserve, float mobility, float taggingPower, float tracers, float damageFalloff) {
        this.name = name;
        this.itemStack = itemStack;
        this.type = type;
        this.baseDmg = baseDmg;
        this.penetration = penetration;
        this.fireRate = fireRate;
        this.magSize = magSize;
        this.reserve = reserve;
        this.mobility = mobility;
        this.taggingPower = taggingPower;
        this.tracers = tracers;
        this.damageFalloff = damageFalloff;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public WeaponID getType() {
        return type;
    }

    public float getBaseDmg() {
        return baseDmg;
    }

    public float getPenetration() {
        return penetration;
    }

    public float getFireRate() {
        return fireRate;
    }

    public int getMagSize() {
        return magSize;
    }

    public int getReserve() {
        return reserve;
    }

    public float getMobility() {
        return mobility;
    }

    public float getTaggingPower() {
        return taggingPower;
    }

    public float getTracers() {
        return tracers;
    }

    public float getDamageFalloff() {
        return damageFalloff;
    }
}
