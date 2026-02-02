package dev.unnm3d.zeltrade.api.hooks;

import dev.unnm3d.zeltrade.api.core.ITrade;
import org.bukkit.entity.Player;

public interface RestrictionHook<T extends ITrade> {

    String getName();

    boolean restrict(Player player, T trade);
}
