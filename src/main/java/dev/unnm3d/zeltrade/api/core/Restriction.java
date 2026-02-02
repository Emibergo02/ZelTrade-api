package dev.unnm3d.zeltrade.api.core;

public record Restriction(String restrictionName, long endRestrictionTimestamp) {
    public static Restriction from(String restrictionName, long duration) {
        return new Restriction(restrictionName, System.currentTimeMillis() + duration);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > endRestrictionTimestamp;
    }
}
