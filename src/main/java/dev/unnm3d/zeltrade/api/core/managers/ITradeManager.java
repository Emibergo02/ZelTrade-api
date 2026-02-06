package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.core.ITrade;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITradeManager<T extends ITrade> {

    /**
     * This query helps you find a trader's trades with various filters.
     *
     * @param traderUUID The UUID of the player whose trades you want to query.
     * @return A PlayerTradeQuery object that allows you to filter and retrieve trades involving the specified player.
     */
    PlayerTradeQuery<T> queryPlayerTrades(@NotNull UUID traderUUID);

    /**
     * After checking ignore list, get the trade between the player and the target, then if found,
     * accept the invite and open the trade window for the player.
     * This method initializes the trade.
     *
     * @param player     The player who is accepting the invite and opening the trade window.
     * @param targetUUID The UUID of the target player, the inviter
     * @param targetName The name of the target player, the inviter. If null, it will be fetched from the player list manager.
     * @param force      Whether to skip ignore list checks and opening restrictions.
     */
    @ApiStatus.Internal
    void startAcceptInviteAndOpen(Player player, UUID targetUUID, @NotNull String targetName, boolean force);

    /**
     * 1. Checks if the player has an active trade with the target, if so, opens the trade window.
     * 2. If the player has been invited by the target, accepts the invite and opens the trade window.
     * 3. If 1 and 2 are not the case, sends a trade invite to the target player.
     *
     * @param player     The player who is trying to open or invite a trade.
     * @param targetUUID The UUID of the target player.
     * @param targetName The name of the target player. If null, it will be fetched from the player list manager.
     */
    @ApiStatus.Internal
    void openOrInviteTrade(Player player, @NotNull UUID targetUUID, @NotNull String targetName);

    /**
     * Open the trade window for the player with the specified trade. If force is true, it will skip ignore list checks and opening restrictions.
     * @param trade The trade to open the window for.
     * @param player The player to open the trade window for.
     * @param force Whether to skip ignore list checks and opening restrictions.
     */
    void openTradeWindow(T trade, Player player, boolean force);

    /**
     * Open the archived trades GUI of the target player for the player, within the specified time range.
     * @param player The player to open the archived trades GUI for.
     * @param targetUUID The UUID of the owner of the archived trades.
     * @param start The start of the time range to filter archived trades. If null, it will not filter by start time.
     * @param end The end of the time range to filter archived trades. If null, it will not filter by end time.
     */
    void openArchivedTrades(Player player, UUID targetUUID, LocalDateTime start, LocalDateTime end);

    /**
     * @deprecated This feature is no longer supported and will be removed in future versions.
     * Open the active trades GUI of the player, showing all active trades involving the player.
     * @param player The player to open the active trades GUI for.
     */
    @Deprecated
    void openActiveTrades(Player player);

    /**
     * Get a list of all trades.
     * Internal use only. Use queryPlayerTrades(UUID) to get trades with filters.
     *
     * @return A list of all trades.
     */
    @ApiStatus.Internal
    List<T> getAllTrades();

    /**
     * Get a trade by its UUID.
     * @param tradeUUID The UUID of the trade to retrieve.
     * @return An Optional containing the trade if found, or an empty Optional if no trade with the specified UUID exists.
     */
    Optional<T> getTrade(UUID tradeUUID);
}
