package dev.unnm3d.zeltrade.api.enums;

public enum KnownRestriction {
    MOVED("PLAYER_MOVED"),
    MOUNT("PLAYER_MOUNT"),
    DAMAGED("PLAYER_DAMAGED"),
    COMBAT("PLAYER_COMBAT"),
    WORLD_CHANGE("WORLD_CHANGE"),
    WORLD_BLACKLISTED("WORLD_BLACKLISTED");

    private final String name;

    KnownRestriction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}