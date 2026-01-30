package dev.unnm3d.zeltrade.core;

import dev.unnm3d.zeltrade.core.enums.Status;
import dev.unnm3d.zeltrade.core.enums.TradeViewType;

import java.util.Optional;
import java.util.UUID;

public interface ITradeSide {

    /**
     * Gets the UUID of the trader on this side.
     * @return The trader's UUID.
     */
    UUID getTraderUUID();

    /**
     * Gets the name of the trader.
     * @return The trader's name.
     */
    String getTraderName();

    /**
     * Gets the OrderInfo containing inventory, prices, and status.
     * @return The OrderInfo object.
     */
    <T extends IOrderInfo> T getOrder();

    /**
     * Checks if this trade side is currently active/connected.
     * @return True if active.
     */
    boolean isActive();

    /**
     * Sets the active state of this trade side.
     * @param active The active state.
     */
    void setActive(boolean active);

    /**
     * Gets the current view type (e.g., waiting, trading).
     * @return The TradeViewType.
     */
    TradeViewType getCurrentTradeView();

    /**
     * Sets the current view type.
     * @param currentTradeView The new view type.
     */
    void setCurrentTradeView(TradeViewType currentTradeView);


    /**
     * Sets the price for a currency and triggers necessary UI updates.
     * @param currency The currency name.
     * @param price    The price amount.
     */
    void setPrice(String currency, double price);

    /**
     * Sets the status of the trade side and triggers necessary UI updates.
     * @param status The new status.
     */
    void setStatus(Status status);

    /**
     * Notifies the interface that the opposite side's status has changed.
     */
    void notifyOppositeStatus();

    /**
     * Notifies the interface that the opposite side's price has changed.
     */
    void notifyOppositePrice();

    /**
     * Notifies the interface to update a profile element (e.g., player head).
     * @param itemChar The character representing the item in the GUI.
     */
    void notifyProfile(char itemChar);

    /**
     * Serializes the trade side data into a byte array.
     * @return The byte array representation.
     */
    byte[] serialize();
}