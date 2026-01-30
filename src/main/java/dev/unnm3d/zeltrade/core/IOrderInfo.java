package dev.unnm3d.zeltrade.core;


import dev.unnm3d.zeltrade.core.enums.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IOrderInfo {

    /**
     * Gets the current status of the order (e.g., REFUSED, CONFIRMED).
     * @return The status.
     */
    Status getStatus();

    /**
     * Sets the status of the order.
     * @param status The new status.
     */
    void setStatus(Status status);

    /**
     * Gets the rating associated with this order.
     * @return The rating.
     */
    Integer getRating();

    /**
     * Sets the rating for this order.
     * @param rating The new rating.
     */
    void setRating(Integer rating);


    /**
     * Gets the map of currency names to price values.
     * @return A map of prices.
     */
    Map<String, Double> getPrices();

    /**
     * Sets the price for a specific currency.
     * @param currency The currency name.
     * @param price    The amount.
     */
    void setPrice(String currency, double price);

    /**
     * Gets the price for a specific currency.
     * @param currency The currency name.
     * @return The price, or 0.0d if not set.
     */
    double getPrice(@NotNull String currency);

    /**
     * Serializes the order info into a byte array.
     * @return The byte array representation.
     */
    byte[] serialize();
}