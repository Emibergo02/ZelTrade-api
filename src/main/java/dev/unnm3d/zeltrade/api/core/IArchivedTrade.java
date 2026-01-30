package dev.unnm3d.zeltrade.api.core;

import java.util.Date;


public interface IArchivedTrade<T extends ITrade> {
    Date getArchivedAt();

    T getTrade();
}
