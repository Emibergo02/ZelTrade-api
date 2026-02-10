package dev.unnm3d.zeltrade.api.hooks;

import dev.unnm3d.zeltrade.api.core.ITrade;
import org.bukkit.entity.Player;

public interface RestrictionHook<T extends ITrade> {

    String getName();

    /**
     * Checks if the given player is restricted from engaging in the specified trade.
     * @param player The player to check for restrictions.
     * @param trade The trade to check against.
     * @return true if the player is restricted from the trade, false otherwise.
     */
    boolean restrict(Player player, T trade);
}
