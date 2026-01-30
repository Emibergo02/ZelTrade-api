package dev.unnm3d.zeltrade.core;

import dev.unnm3d.zeltrade.core.enums.Actor;
import dev.unnm3d.zeltrade.core.enums.Status;
import dev.unnm3d.zeltrade.core.enums.StatusActor;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

public interface ITrade {

    /**
     * Gets the unique ID of the trade.
     *
     * @return The UUID.
     */
    UUID getUuid();

    /**
     * Gets the trader side object.
     *
     * @return The Trader's side.
     */
    <T extends ITradeSide> T getTraderSide();

    /**
     * Gets the customer side object.
     *
     * @return The Customer's side.
     */
    <T extends ITradeSide> T getCustomerSide();

    /**
     * Determines the role of a player UUID in this trade.
     *
     * @param playerUUID The UUID of the player.
     * @return The Actor role.
     */
    Actor getActor(UUID playerUUID);

    /**
     * Checks if a player is a direct participant (Trader or Customer).
     *
     * @param playerUUID The UUID of the player.
     * @return True if participant, false otherwise.
     */
    boolean isParticipant(UUID playerUUID);

    /**
     * Retrieves the specific TradeSide object based on the Actor role.
     *
     * @param actor The actor role.
     * @return The corresponding TradeSide.
     */
    <T extends ITradeSide> T getTradeSide(Actor actor);

    /**
     * Checks if the UUID belongs to the trader.
     *
     * @param playerUUID The UUID to check.
     * @return True if trader.
     */
    boolean isTrader(UUID playerUUID);

    /**
     * Checks if the UUID belongs to the customer.
     *
     * @param playerUUID The UUID to check.
     * @return True if customer.
     */
    boolean isCustomer(UUID playerUUID);

    /**
     * Set the price of the trade.
     *
     * @param currencyName The name of the currency.
     * @param price        The price to set.
     * @param actorSide    If the price is for the trader side.
     */
    void setPrice(String currencyName, double price, Actor actorSide);

    /**
     * Set the price of the trade and send the update to the database/cache.
     *
     * @param currencyName The name of the currency.
     * @param price        The price to set.
     * @param actorSide    The side to set the price for.
     */
    void setAndSendPrice(String currencyName, double price, Actor actorSide);

    /**
     * Refund the player on the specified side on all currencies.
     *
     * @param funder   The side that funded the trade.
     * @param receiver The side that will receive the funds.
     */
    void withdrawFunds(Actor funder, Actor receiver);

    /**
     * Set the status of the trade.
     *
     * @param newStatus The status to set.
     * @param actorSide The side of the trade to set the status for.
     */
    void setStatus(StatusActor newStatus, Actor actorSide);

    /**
     * Set the status of the trade and send the update to the database/cache.
     *
     * @param newStatus      The status to set.
     * @param previousStatus The previous status.
     * @param viewerSide     The side of the trade to set the status for.
     * @return A stage containing the new status.
     */
    CompletionStage<Status> changeAndSendStatus(StatusActor newStatus, Status previousStatus, Actor viewerSide);


    /**
     * Called when a status is changed.
     * Starts the completion timer if both are confirmed.
     * Terminates the completion timer if one is refused.
     */
    void confirmPhase();

    /**
     * Checks if the completion timer is currently active.
     *
     * @return True if running.
     */
    boolean isConfirmationRunning();

    /**
     * This phase is called when the CompletionTimer is finished.
     * It switches the sides of trader and target and handles finalization.
     */
    void completedPhase();

    /**
     * Check if an inventory is empty and the status is completed.
     * Then it resets the trade for the player and locks the whole GUI.
     *
     * @param tradeSide    The side of the player.
     * @param whoIsEditing The side of the player that is editing.
     * @return A stage containing the resulting status.
     */
    CompletionStage<Status> retrievedPhase(Actor tradeSide, Actor whoIsEditing);

    /**
     * Finish the trade and remove the player name from trades.
     * If both trader and target are disconnected, deactivates the trade without removing it.
     *
     * @param actorSide The actor side that is closing the trade.
     */
    void finish(Actor actorSide);

    /**
     * Try to collect items for the actor and then retrieves the trade if possible.
     *
     * @param retrievingActor The actor trying to collect items.
     * @return false if items couldn't be collected, or the trade is in a wrong configuration, true otherwise.
     */
    boolean tryCollectAndRetrieve(Actor retrievingActor);

    /**
     * Serializes the trade data into a byte array.
     *
     * @return The byte array representation.
     */
    byte[] serialize();
}
