package dev.unnm3d.zeltrade.api.core.managers;

import java.util.Optional;
import java.util.UUID;

public interface IPlayerListManager {
    /**
     * Get the UUID of a player by their name, searching inside ZelTrade player list
     * @param name The name of the player
     * @return An Optional containing the UUID if found, or empty if not found
     */
    Optional<UUID> getPlayerUUID(String name);

    /**
     * Get the player name by their UUID, searching inside ZelTrade player list
     * @param uuid The UUID of the player
     * @return An Optional containing the player name if found, or empty if not found
     */
    Optional<String> getPlayerName(UUID uuid);
}
