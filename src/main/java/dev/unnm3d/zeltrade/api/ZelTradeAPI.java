package dev.unnm3d.zeltrade.api;

import dev.unnm3d.zeltrade.api.core.ITrade;
import dev.unnm3d.zeltrade.api.core.managers.*;
import dev.unnm3d.zeltrade.api.data.ICacheData;
import dev.unnm3d.zeltrade.api.data.IStorageData;

public interface ZelTradeAPI<T extends ITrade> {

    <V extends IStorageData<T>> V getDataStorage();
    <V extends ICacheData<T>> V getDataCache();
    <V extends IPlayerListManager> V getPlayerListManager();
    <V extends ISessionManager<T>> V getSessionManager();
    <V extends IInviteManager> V getInviteManager();
    <V extends IIgnoreManager> V getIgnoreManager();
    <V extends ITradeManager<T>> V getTradeManager();

    /**
     * Get ZelTrade API instance
     * @return the ZelTrade API instance
     */
    static ZelTradeAPI<?> get() {
        return ZelTradeProvider.getApi();
    }
}
