package dev.unnm3d.zeltrade.api.hooks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public interface CurrencyHook {

    @NotNull
    String getName();

    boolean depositPlayer(@NotNull UUID playerUUID, double amount, @Nullable String reason);

    double getBalance(@NotNull UUID playerUUID);

    boolean withdrawPlayer(@NotNull UUID playerUUID, double amount, @Nullable String reason);

    @NotNull
    String getCurrencySymbol();
}
