package com.ruskei.csgo;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Projectile {

    private final Player p;
    private final RangedWeapon weapon;

    private List<LivingEntity> hitPlayers = new ArrayList<>();

    public Projectile(Player p, RangedWeapon weapon) {
        this.p = p;
        this.weapon = weapon;
    }

    public void hitEntity(LivingEntity e, Hitbox hitbox, Main plugin) {
        if (!hitPlayers.contains(e)) {
            hitPlayers.add(e);
            System.out.println(hitbox.getType());

            float dmgMod = 1f;

            switch (hitbox.getType()) {
                case HEAD:
                    dmgMod = 4f;
                    break;
                case GUT:
                    dmgMod = 1.25f;
                    break;
                case LEGS:
                    dmgMod = 0.75f;
                    break;

            }

            float finalDamage = weapon.getBaseDmg() * dmgMod;
            if (e.getHealth() - finalDamage <= 0f) {
                e.setHealth(0);
                hitbox.getParent().delete(plugin);
                return;
            }

            finalDamage = e.getHealth() - finalDamage < 0f ? (float) e.getHealth() : finalDamage;
            e.damage(0);
            e.setHealth(e.getHealth() - finalDamage);
        }
    }
}
