package com.ruskei.csgo;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Hitbox {

    private HitboxParent parent;

    private Vector min;
    private Vector max;

    private LivingEntity bound;
    private BodyPart type;
    private Vector minOffset;
    private Vector maxOffset;

    public Hitbox(HitboxParent parent, Vector min, Vector max, LivingEntity bound, BodyPart type, Vector minOffset, Vector maxOffset) {
        this.parent = parent;
        this.min = min;
        this.max = max;
        this.bound = bound;
        this.type = type;
        this.minOffset = minOffset;
        this.maxOffset = maxOffset;
    }

    public void updateBounds() {
        min = bound.getLocation().toVector().clone().add(rotateVectorAroundY(minOffset, Math.atan2(bound.getLocation().getDirection().getX(), bound.getLocation().getDirection().getZ())));
        max = bound.getLocation().toVector().clone().add(rotateVectorAroundY(maxOffset, Math.atan2(bound.getLocation().getDirection().getX(), bound.getLocation().getDirection().getZ())));
    }

    public HitboxParent getParent() {
        return parent;
    }

    public Vector getMin() {
        return min;
    }

    public void setMin(Vector min) {
        this.min = min;
    }

    public LivingEntity getBound() {
        return bound;
    }

    public BodyPart getType() {
        return type;
    }

    public Vector getMax() {
        return max;
    }

    public void setMax(Vector max) {
        this.max = max;
    }

    public static Vector rotateVectorAroundY(Vector vector, double degrees) {
        double rad = Math.toRadians(degrees);

        double currentX = vector.getX();
        double currentZ = vector.getZ();

        double cosine = Math.cos(rad);
        double sine = Math.sin(rad);

        return new Vector((cosine * currentX - sine * currentZ), vector.getY(), (sine * currentX + cosine * currentZ));
    }
}

