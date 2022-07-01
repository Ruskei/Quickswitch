package com.ruskei.csgo.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class RayHits {

    private List<Player> hits;

    public RayHits(List<Player> hits) {
        this.hits = hits;
    }

    public List<Player> getHits() {
        return hits;
    }
}
