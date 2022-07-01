package com.ruskei.csgo.utils;

import com.ruskei.csgo.Hitbox;
import com.ruskei.csgo.HitboxParent;
import com.ruskei.csgo.Main;
import com.ruskei.csgo.Projectile;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class RayTrace {

    private final Player p;
    private final Vector origin, direction;

    private final Projectile projectile;

    public RayTrace(Player p, Projectile projectile) {
        this.p = p;
        this.origin = p.getEyeLocation().toVector();
        this.direction = p.getLocation().getDirection();

        this.projectile = projectile;
    }

    public void shoot(Player p, float distance, float accuracy, Main plugin) {
        World w = p.getWorld();

        double radians = Math.toRadians(p.getLocation().getYaw() + 90);
        Vector playerDirection = p.getLocation().getDirection();
        Vector newDirection = new Vector(Math.cos(radians), 0, Math.sin(radians));
        float sneakingMod = (p.isSneaking()) ? 0.35f : 0f;
        Vector tracerOrigin = p.getLocation().toVector().clone().add(new Vector((-1 * newDirection.getZ()) / 2, 1.5 - sneakingMod, (newDirection.getX()) / 2)).add(new Vector(playerDirection.getX() / 2, playerDirection.getY() / 2, playerDirection.getZ() / 2));
        Vector difference = (origin.clone().add(direction.clone().multiply(distance))).subtract(tracerOrigin);
        Vector tracerDirection = new Vector(difference.getX() / distance, difference.getY() / distance, difference.getZ() / distance);

        for (double d = 0; d <= distance; d += accuracy) {
            Vector currentPos = origin.clone().add(direction.clone().multiply(d));

            if (!w.getBlockAt((int) Math.floor(currentPos.getX()), (int) Math.floor(currentPos.getY()), (int) Math.floor(currentPos.getZ())).getType().equals(Material.AIR)) {
                return;
            }

            for (HitboxParent parent : plugin.hitboxParents) {
                for (Hitbox hitbox : parent.getHitboxes()) {
                    if (inBounds(hitbox, currentPos)) {
                        projectile.hitEntity(hitbox.getBound(), hitbox, plugin);
                        return;
                    }
                }
            }

            Vector tracerCurrent = tracerOrigin.clone().add(tracerDirection.clone().multiply(d));

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(252, 186, 3), 0.25f);
            p.spawnParticle(Particle.REDSTONE, tracerCurrent.toLocation(p.getWorld()), 1, dustOptions);
        }
    }

    private boolean inBounds(Hitbox hitbox, Vector pos) {
        return pos.getX() >= hitbox.getMin().getX() && pos.getX() <= hitbox.getMax().getX() &&
                pos.getY() >= hitbox.getMin().getY() && pos.getY() <= hitbox.getMax().getY() &&
                pos.getZ() >= hitbox.getMin().getZ() && pos.getZ() <= hitbox.getMax().getZ();
    }
}
