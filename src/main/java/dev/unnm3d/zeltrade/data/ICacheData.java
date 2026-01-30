package dev.unnm3d.zeltrade.data;


import dev.unnm3d.zeltrade.core.ITrade;
import dev.unnm3d.zeltrade.core.enums.ViewerUpdate;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface ICacheData<T extends ITrade> {

    void publishPlayerList(List<String> playerList);

    void updateCachePlayerList(String playerName, UUID playerUUID);

    /**
     * Update a trade in the cache
     *
     * @param tradeUUID the trade to update
     * @param type      the type of update (price, item, status)
     * @param value     the value to update
     * @return the number of subscribers that received the message
     */
    CompletionStage<Long> updateTrade(UUID tradeUUID, ViewerUpdate type, Object value);

    /**
     * Broadcast a full trade to all servers in the network
     *
     * @param trade the trade to broadcast
     * @return the number of subscribers that received the message
     */
    CompletionStage<Long> sendFullTrade(T trade);

    /**
     * Send a trade invite to other servers
     *
     * @param traderName   the trader name
     * @param customerName the customer name
     */
    void sendInvite(@NotNull String traderName, @NotNull String customerName);

    void sendIgnore(@NotNull String ignorer, @NotNull String ignored, boolean isIgnoring);

    /**
     * Send a query to all servers
     * The servers will respond by sending all their owned trades via sendFullTrade
     */
    void sendQuery();

    void close();

}
