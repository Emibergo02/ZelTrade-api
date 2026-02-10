# ZelTrade API

A comprehensive API for managing player-to-player trades in Minecraft Bukkit/Spigot servers. ZelTrade provides a robust system for handling trade sessions, invitations, currency integration, and trade history.

## Features

- 🔄 **Trade Management** - Create and manage trade sessions between players
- 💰 **Currency Support** - Flexible currency hook system for multiple economy plugins
- 📊 **Trade History** - Archive and query past trades
- 🔔 **Event System** - Listen to trade events for custom integrations
- ⚡ **Async Operations** - Non-blocking database operations using CompletableFuture
- 🚫 **Player Ignore System** - Allow players to block trade requests
- ⭐ **Rating System** - Players can rate their trading experience

## Maven Dependency

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Emibergo02</groupId>
        <artifactId>ZelTrade-api</artifactId>
        <version>main-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
```gradle.build
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    compileOnly 'com.github.Emibergo02:ZelTrade-api:main-SNAPSHOT'
}
```

## Getting Started

### Accessing the API

```java
import dev.unnm3d.zeltrade.api.ZelTradeAPI;

public class MyPlugin extends JavaPlugin {
    private ZelTradeAPI<?> zelTradeAPI;
    
    @Override
    public void onEnable() {
        // Get the API instance
        zelTradeAPI = ZelTradeAPI.get();
    }
}
```

## Core Concepts

### Trade Lifecycle

A trade in ZelTrade follows this lifecycle:

1. **Invitation** - One player invites another to trade
2. **Opening** - Both players accept and the trade window opens
3. **Negotiation** - Players add items and set prices
4. **Confirmation** - Both players confirm the trade
5. **Completion** - Items and currency are exchanged
6. **Retrieval** - Players collect their items
7. **Archival** - Trade is saved to history

## Usage Examples

### 1. Opening a Trade Between Two Players

```java
import dev.unnm3d.zeltrade.api.ZelTradeAPI;
import dev.unnm3d.zeltrade.api.core.managers.ITradeManager;
import org.bukkit.entity.Player;

public void initiateTrade(Player initiator, Player target) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    ITradeManager<?> tradeManager = api.getTradeManager();
    
    // Open or invite trade - handles invite creation and acceptance automatically
    tradeManager.openOrInviteTrade(
        initiator,
        target.getUniqueId(),
        target.getName()
    );
}
```

### 2. Querying Player Trades

```java
import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.core.managers.PlayerTradeQuery;
import java.util.List;
import java.util.UUID;

public void getActiveTradesWithPlayer(UUID playerUUID, UUID otherPlayerUUID) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    // Query all active trades between two players
    List<? extends ITrade> trades = api.getTradeManager()
        .queryPlayerTrades(playerUUID)
        .actives()
        .withOtherPlayer(otherPlayerUUID)
        .toList();
    
    for (ITrade trade : trades) {
        getLogger().info("Active trade: " + trade.getUuid());
    }
}
```

### 3. Finding Specific Trades

```java
import dev.unnm3d.zeltrade.api.enums.TradeViewType;
import java.util.Optional;

public void findPlayerViewingTrade(UUID playerUUID) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    // Find any trade where the player is currently viewing
    Optional<? extends ITrade> viewingTrade = api.getTradeManager()
        .queryPlayerTrades(playerUUID)
        .withPlayerViewing()
        .getAnyOpt();
    
    if (viewingTrade.isPresent()) {
        ITrade trade = viewingTrade.get();
        getLogger().info("Player is viewing trade: " + trade.getUuid());
    }
}
```

### 4. Listening to Trade Events

```java
import dev.unnm3d.zeltrade.api.events.TradeInviteEvent;
import dev.unnm3d.zeltrade.api.events.TradeOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TradeListener implements Listener {
    
    @EventHandler
    public void onTradeInvite(TradeInviteEvent event) {
        Player inviter = event.getInviter();
        String inviteeName = event.getInviteeName();
        
        getLogger().info(inviter.getName() + " invited " + inviteeName + " to trade");
        
        // You can perform custom actions here
        // For example, log to database, send notifications, etc.
    }
    
    @EventHandler
    public void onTradeOpen(TradeOpenEvent<?> event) {
        Player opener = event.getOpener();
        ITrade trade = event.getTrade();
        boolean isFirstTime = event.isFirstTime();
        
        // Cancel the trade opening based on custom conditions
        if (shouldBlockTrade(opener, trade)) {
            event.setCancelled(true);
            opener.sendMessage("You cannot open this trade right now!");
            return;
        }
        
        if (isFirstTime) {
            getLogger().info("New trade opened: " + trade.getUuid());
        } else {
            getLogger().info("Trade reopened: " + trade.getUuid());
        }
    }
    
    private boolean shouldBlockTrade(Player player, ITrade trade) {
        // Your custom logic here
        return false;
    }
}
```

### 5. Managing Trade Prices

```java
import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.enums.Actor;

public void setTradePrice(ITrade trade, double amount) {
    // Set price for the trader side
    trade.setAndSendPrice("Vault", amount, Actor.TRADER);
    
    // Or set price for the customer side
    trade.setAndSendPrice("Vault", amount, Actor.CUSTOMER);
}
```

### 6. Checking Trade Status

```java
import dev.unnm3d.zeltrade.api.core.ITradeSide;

public void checkTradeStatus(ITrade trade, UUID playerUUID) {
    Actor actor = trade.getActor(playerUUID);
    ITradeSide tradeSide = trade.getTradeSide(actor);
    
    if (tradeSide.isActive()) {
        getLogger().info("Trade is active for player");
    }
    
    // Check if player is a participant
    if (trade.isParticipant(playerUUID)) {
        if (trade.isTrader(playerUUID)) {
            getLogger().info("Player is the trader");
        } else if (trade.isCustomer(playerUUID)) {
            getLogger().info("Player is the customer");
        }
    }
}
```

### 7. Accessing Archived Trades

```java
import dev.unnm3d.zeltrade.api.data.IStorageData;
import dev.unnm3d.zeltrade.api.core.IArchivedTrade;
import java.time.LocalDateTime;
import java.util.List;

public void viewTradeHistory(UUID playerUUID) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    IStorageData<?> storage = api.getDataStorage();
    
    // Get trades from the last 30 days
    LocalDateTime startDate = LocalDateTime.now().minusDays(30);
    LocalDateTime endDate = LocalDateTime.now();
    
    storage.getArchivedTrades(playerUUID, startDate, endDate)
        .thenAccept(archivedTrades -> {
            for (IArchivedTrade<?> archived : archivedTrades) {
                getLogger().info("Trade: " + archived.getUuid());
                // Process archived trade
            }
        });
}
```

### 8. Opening Archived Trades GUI

```java
import org.bukkit.entity.Player;
import java.time.LocalDateTime;

public void showTradeHistory(Player viewer, UUID targetPlayer) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    // Show last 7 days of trades
    LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
    LocalDateTime now = LocalDateTime.now();
    
    api.getTradeManager().openArchivedTrades(
        viewer,
        targetPlayer,
        weekAgo,
        now
    );
}
```

### 9. Managing Player Ignore List

```java
import dev.unnm3d.zeltrade.api.core.managers.IIgnoreManager;

public void manageIgnoreList(String playerName, String targetName, boolean ignore) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    IIgnoreManager ignoreManager = api.getIgnoreManager();
    
    if (ignore) {
        // Add player to ignore list
        ignoreManager.ignorePlayer(playerName, targetName);
    } else {
        // Remove player from ignore list
        ignoreManager.unignorePlayer(playerName, targetName);
    }
}

public void checkIfIgnored(String playerName, String targetName) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    api.getIgnoreManager().isIgnoring(playerName, targetName)
        .thenAccept(isIgnored -> {
            if (isIgnored) {
                getLogger().info(playerName + " is ignoring " + targetName);
            }
        });
}
```

### 10. Getting Player Ratings

```java
import dev.unnm3d.zeltrade.api.data.MeanRating;
import dev.unnm3d.zeltrade.api.data.IStorageData;

public void getPlayerRating(UUID playerUUID) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    IStorageData<?> storage = api.getDataStorage();
    
    storage.getMeanRating(playerUUID).thenAccept(rating -> {
        if (rating != null) {
            double averageRating = rating.mean();
            int totalRatings = rating.count();
            
            getLogger().info(String.format(
                "Player rating: %.2f/5 (%d trades)",
                averageRating,
                totalRatings
            ));
        }
    });
}
```

### 11. Creating Custom Currency Hooks

```java
import dev.unnm3d.zeltrade.api.hooks.CurrencyHook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class MyCustomCurrency implements CurrencyHook {
    
    @Override
    public @NotNull String getName() {
        return "MyCoins";
    }
    
    @Override
    public boolean depositPlayer(@NotNull UUID playerUUID, double amount, @Nullable String reason) {
        // Your deposit logic here
        // Return true if successful, false otherwise
        return true;
    }
    
    @Override
    public double getBalance(@NotNull UUID playerUUID) {
        // Return player's current balance
        return 0.0;
    }
    
    @Override
    public boolean withdrawPlayer(@NotNull UUID playerUUID, double amount, @Nullable String reason) {
        // Your withdrawal logic here
        return true;
    }
    
    @Override
    public @NotNull String getCurrencySymbol() {
        return "⛃";
    }
}
```

### 12. Filtering Trades with Custom Predicates

```java
import dev.unnm3d.zeltrade.api.core.ITrade;
import java.util.List;

public void findHighValueTrades(UUID playerUUID, double minValue) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    List<? extends ITrade> highValueTrades = api.getTradeManager()
        .queryPlayerTrades(playerUUID)
        .actives()
        .filter(trade -> {
            ITradeSide side = trade.getTradeSide(trade.getActor(playerUUID));
            // Custom logic to check trade value
            return side != null && calculateTradeValue(side) >= minValue;
        })
        .toList();
    
    getLogger().info("Found " + highValueTrades.size() + " high-value trades");
}

private double calculateTradeValue(ITradeSide side) {
    // Your custom value calculation
    return 0.0;
}
```

### 13. Rating a Trade

```java
import dev.unnm3d.zeltrade.api.data.IStorageData;
import dev.unnm3d.zeltrade.api.enums.Actor;

public void rateCompletedTrade(UUID tradeUUID, Actor raterSide, int rating) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    IStorageData<?> storage = api.getDataStorage();
    
    // Rating should be between 1-5
    if (rating >= 1 && rating <= 5) {
        storage.rateTrade(tradeUUID, raterSide, rating);
        getLogger().info("Trade rated: " + rating + " stars");
    }
}

public void getTradeRating(UUID tradeUUID) {
    ZelTradeAPI<?> api = ZelTradeAPI.get();
    
    api.getDataStorage().getTradeRating(tradeUUID)
        .thenAccept(rating -> {
            if (rating != null) {
                getLogger().info("Trader rating: " + rating.traderRating());
                getLogger().info("Customer rating: " + rating.customerRating());
            }
        });
}
```

## API Components

### Managers

- **ITradeManager** - Core trade operations and queries
- **ISessionManager** - Window and session management
- **IInviteManager** - Trade invitation system
- **IIgnoreManager** - Player ignore list management
- **IPlayerListManager** - Player name/UUID mapping
- **IIntegrationManager** - External plugin integrations

### Data Access

- **IStorageData** - Persistent storage operations (database)
- **ICacheData** - In-memory cache operations
- **DataKeys** - Constants for data access

### Events

- **TradeInviteEvent** - Fired when a player sends a trade invite
- **TradeOpenEvent** - Fired when a trade window is opened (cancellable)

### Enums

- **Actor** - Trade participant role (TRADER, CUSTOMER, VIEWER)
- **Status** - Trade status (EDITING, CONFIRMED, COMPLETED, etc.)
- **TradeViewType** - Current view type (TRADE, MONEY_EDITOR, etc.)
- **UpdateType** - Type of trade update
- **KnownRestriction** - Built-in restriction types

## Support

For issues, feature requests, or contributions, please visit the project repository.

## License

Check the main ZelTrade plugin repository for license information.
