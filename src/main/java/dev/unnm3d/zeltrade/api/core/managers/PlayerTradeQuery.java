package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.enums.TradeViewType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerTradeQuery<T extends ITrade> {
    private Stream<T> stream;
    private final UUID traderUUID;

    public PlayerTradeQuery(Stream<T> stream, UUID traderUUID) {
        this.traderUUID = traderUUID;
        this.stream = stream.filter(t -> t.isParticipant(traderUUID));
    }

    /**
     * Filter only active trades for the player.
     * @return the updated PlayerTradeQuery instance.
     */
    public PlayerTradeQuery<T> actives() {
        stream = stream.filter(t -> t.getTradeSide(t.getActor(traderUUID)).isActive());
        return this;
    }

    /**
     * Filter trades involving another specific player.
     * @param otherTraderUUID the UUID of the other player.
     * @return the updated PlayerTradeQuery instance.
     */
    public PlayerTradeQuery<T> withOtherPlayer(UUID otherTraderUUID) {
        stream = stream.filter(t -> t.isParticipant(otherTraderUUID));
        return this;
    }

    /**
     * Filter trades by the view type for the player.
     * @param viewType the TradeViewType to filter by.
     * @return the updated PlayerTradeQuery instance.
     */
    public PlayerTradeQuery<T> withViewType(TradeViewType viewType) {
        stream = stream.filter(t -> t.getTradeSide(t.getActor(traderUUID)).getCurrentTradeView() == viewType);
        return this;
    }

    /**
     * Filter trades where the player is currently viewing. (Not in NOT_VIEWING state)
     * @return the updated PlayerTradeQuery instance.
     */
    public PlayerTradeQuery<T> withPlayerViewing() {
        stream = stream.filter(t -> t.getTradeSide(t.getActor(traderUUID)).getCurrentTradeView() != TradeViewType.NOT_VIEWING);
        return this;
    }

    public PlayerTradeQuery<T> filter(Predicate<T> predicate) {
        stream = stream.filter(predicate);
        return this;
    }

    public Optional<T> getAnyOpt() {
        return stream.findAny();
    }

    public List<T> toList() {
        return stream.collect(Collectors.toList());
    }
}
