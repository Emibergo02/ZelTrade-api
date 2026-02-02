package dev.unnm3d.zeltrade.api.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a player sends a trade invite to another player.
 */
@Getter
public class TradeInviteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player inviter;
    private final String inviteeName;

    /**
     * Constructor for the TradeInviteEvent.
     * @param inviter The player who is sending the trade invite.
     * @param inviteeName The name of the player who is being invited to trade.
     */
    public TradeInviteEvent(Player inviter, String inviteeName) {
        super(true);
        this.inviter = inviter;
        this.inviteeName = inviteeName;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
