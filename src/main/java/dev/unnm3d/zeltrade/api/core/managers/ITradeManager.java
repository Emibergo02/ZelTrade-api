package dev.unnm3d.zeltrade.api.core.managers;

import dev.unnm3d.zeltrade.api.core.ITrade;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface ITradeManager<T extends ITrade> {
    Optional<T> getPlayerViewingTrade(@NotNull UUID playerUUID);
}
