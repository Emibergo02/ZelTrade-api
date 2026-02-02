package dev.unnm3d.zeltrade.api.events;

import dev.unnm3d.zeltrade.api.core.ITrade;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * * Event fired when a trade is opened.
 * @param <T> the type of trade.
 */
@Getter
public class TradeOpenEvent<T extends ITrade> extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final T trade;
    private final Player opener;
    private final boolean firstTime;
    private boolean cancelled = false;

    public TradeOpenEvent(T trade, Player opener, boolean firstTime) {
        super(true);
        this.trade = trade;
        this.opener = opener;
        this.firstTime = firstTime;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
