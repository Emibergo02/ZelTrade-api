package dev.unnm3d.zeltrade.api.hooks;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface VanishHook {

    boolean canSee(@NotNull Player name, @NotNull Player other);

    boolean isVanished(@NotNull Player name);

}
