package com.ruskei.csgo;

import java.util.List;

public class HitboxParent {

    private List<Hitbox> hitboxes;

    public HitboxParent(List<Hitbox> hitboxes) {
        this.hitboxes = hitboxes;
    }

    public void updateBounds() {
        hitboxes.forEach(Hitbox::updateBounds);
    }

    public void delete(Main plugin) {
        plugin.hitboxParents.remove(this);
        hitboxes.clear();
    }

    public List<Hitbox> getHitboxes() {
        return hitboxes;
    }
}
