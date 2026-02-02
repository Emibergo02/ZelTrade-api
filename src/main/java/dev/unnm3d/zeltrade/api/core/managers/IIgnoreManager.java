package dev.unnm3d.zeltrade.api.core.managers;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface IIgnoreManager {
    /**
     * Updates the ignore status between two players in both local cache and storage.
     *
     * @param playerName The player performing the ignore action.
     * @param targetName The player being ignored (or un-ignored).
     * @param ignore     True to ignore, false to un-ignore.
     */
    void ignorePlayer(@NotNull String playerName, @NotNull String targetName, boolean ignore);

    /**
     * Checks if a player is ignoring a specific target.
     *
     * @param playerName The player to check the ignore list of.
     * @param targetName The target player name.
     * @return True if the player is ignoring the target.
     */
    boolean isIgnoring(@NotNull String playerName, @NotNull String targetName);

    /**
     * Retrieves the set of players ignored by a specific player.
     *
     * @param playerName The player name.
     * @return A Set of strings representing ignored player names.
     */
    @NotNull
    Set<String> getIgnoredPlayers(@NotNull String playerName);

}
