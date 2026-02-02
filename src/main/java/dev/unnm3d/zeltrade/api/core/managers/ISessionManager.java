package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.enums.Actor;
import dev.unnm3d.zeltrade.api.enums.TradeViewType;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface ISessionManager<T extends ITrade> {

    /**
     * Opens a window for the player, creating a session if one does not exist.
     *
     * @param trade  The trade to open
     * @param player The player to open the window for
     * @param type   The type of window to open (TRADE, MONEY_EDITOR, etc.)
     * @param side   The actor side that is opening the window
     * @param force  Whether to force open the window (bypass restrictions)
     * @param delay  The delay before opening the window (in ticks)
     * @param args   Additional arguments for the window (e.g. currency name)
     */
    void openWindow(T trade, Player player, TradeViewType type, Actor side,
                    boolean force, long delay, Object... args);

    /**
     * Safely closes a player's session and removes it from the map.
     * Handles both online and offline (logout) scenarios.
     *
     * @param playerUUID The UUID of the player to close the session for.
     */
    void closePlayerSession(UUID playerUUID);
}
