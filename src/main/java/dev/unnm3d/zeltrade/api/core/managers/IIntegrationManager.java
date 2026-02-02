package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.hooks.CurrencyHook;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public interface IIntegrationManager {

    /**
     * Register a currency hook to be used in the trade GUI.
     * Make sure you are not using an existing currency name!
     *
     * @param currencyName The unique name of the currency on which the hook will be registered
     * @param currencyHook The currency hook implementation
     * @param displayItem The item to display in the trade GUI for this currency
     */
    void addCurrencyHook(String currencyName, CurrencyHook currencyHook, ItemStack displayItem);

    /**
     * Get a registered currency hook by its name.
     * @param currencyName The name of the currency
     * @return The currency hook, or null if not found
     */
    CurrencyHook getCurrencyHook(String currencyName);

    Set<String> getCurrencyNames();

    List<CurrencyHook> getCurrencyHooks();
}
