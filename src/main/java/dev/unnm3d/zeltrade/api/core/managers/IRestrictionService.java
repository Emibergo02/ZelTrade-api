package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.core.Restriction;
import dev.unnm3d.zeltrade.api.hooks.RestrictionHook;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IRestrictionService<T extends ITrade> {

    /**
     * Check if the player is restricted from trading
     *
     * @param player The player to check
     * @param trade The trade context
     * @return The restriction if the player is restricted, null otherwise
     */
    @Nullable Restriction getRestriction(@NotNull Player player, @NotNull T trade);

    /**
     * Register a new restriction hook
     * @param hook The restriction hook to register
     */
    void addRestrictionHook(@NotNull RestrictionHook<T> hook);

    /**
     * Trigger a restriction event, starts the corresponding restriction cooldown if any
     * @param player the player to trigger the restriction for
     * @param restrictionName the name of the restriction to trigger
     */
    void triggerRestriction(@NotNull Player player, @NotNull String restrictionName);
}
