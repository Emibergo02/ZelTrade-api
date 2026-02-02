package dev.unnm3d.zeltrade.api.core.managers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IInviteManager {

    /**
     * Invite a customer to trade
     * @param inviter the trader name
     * @param invited the invited customer name
     */
    void invite(@NotNull String inviter, @NotNull String invited);

    /**
     * Get who the trader has invited
     *
     * @param inviter the player nam who invited
     * @return the invited customer name or null if none
     */
    @Nullable
    String getInvitee(@NotNull String inviter);

    /**
     * Accept an invitation to trade
     *
     * @param inviter the trader name who invited, who's invitation is being accepted and removed
     */
    void acceptInvitationOf(@NotNull String inviter);
}
