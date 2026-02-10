package dev.unnm3d.zeltrade.api.data;


import dev.unnm3d.zeltrade.api.core.IArchivedTrade;
import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.enums.Actor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface IStorageData<T extends ITrade> {
    void backupTrade(@NotNull T trade);

    void removeTradeBackup(@NotNull UUID tradeUUID);

    void updateStoragePlayerList(@NotNull String playerName, @NotNull UUID playerUUID);

    void ignorePlayer(@NotNull String playerName, @NotNull String targetName, boolean ignore);

    void rateTrade(@NotNull UUID archivedTradeUUID, @NotNull Actor actor, int rating);

    CompletionStage<Map<Integer, T>> restoreTrades();

    CompletionStage<Map<String, UUID>> loadNameUUIDs();

    CompletionStage<Set<String>> getIgnoredPlayers(@NotNull String playerName);

    CompletionStage<TradeRating> getTradeRating(@NotNull UUID tradeUUID);

    CompletableFuture<MeanRating> getMeanRating(@NotNull UUID playerUUID);

    /**
     * Archive a trade, not available for REDIS storage type
     *
     * @param trade the trade to archive
     * @return true if the trade was archived successfully
     */
     default CompletableFuture<@Nullable IArchivedTrade<T>> archiveTrade(@NotNull T trade) {
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Get the archived trades for a player between two timestamps
     * Not available for REDIS storage type
     *
     * @param playerUUID     the player to get the trades for
     * @param startTimestamp the start timestamp
     * @param endTimestamp   the end timestamp
     * @return a map of timestamps and trades
     */
    default CompletableFuture<List<IArchivedTrade<T>>> getArchivedTrades(@NotNull UUID playerUUID, @NotNull LocalDateTime startTimestamp, @NotNull LocalDateTime endTimestamp) {
        return CompletableFuture.completedFuture(new ArrayList<>());
    }

    default CompletableFuture<Optional<IArchivedTrade<T>>> getArchivedTrade(@NotNull UUID tradeUUID) {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    default void wipeArchivedTrades(){}

    void close();

}
