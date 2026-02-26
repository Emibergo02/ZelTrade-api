package dev.unnm3d.zeltrade.api.core.managers;


import dev.unnm3d.zeltrade.api.hooks.VanishHook;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishManager {
    private VanishHook integration;

    public void setIntegration(@NotNull VanishHook integration) {
        this.integration = integration;
    }

    @NotNull
    public VanishHook getIntegration() {
        return integration;
    }

    public boolean canSee(@NotNull Player name, @NotNull Player other) {
        return integration.canSee(name, other);
    }

    public boolean isVanished(@NotNull Player name) {
        return integration.isVanished(name);
    }

}
