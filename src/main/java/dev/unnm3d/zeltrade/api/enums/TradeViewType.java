package dev.unnm3d.zeltrade.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradeViewType {
    NOT_VIEWING(0),
    TRADE(1),
    MONEY_EDITOR(2),
    ITEM_CONTAINER(3);

    private final int id;

    public static TradeViewType fromStr(String idStr) {
        int intId= Integer.parseInt(idStr);
        if (intId == 0) return NOT_VIEWING;
        if (intId == 1) return TRADE;
        if (intId == 2) return MONEY_EDITOR;
        return ITEM_CONTAINER;
    }

    public static TradeViewType fromByte(byte idByte) {
        if (idByte == 0) return NOT_VIEWING;
        if (idByte == 1) return TRADE;
        if (idByte == 2) return MONEY_EDITOR;
        return ITEM_CONTAINER;
    }

    public byte toByte() {
        return (byte) id;
    }

    public String toChar() {
        return String.valueOf(id);
    }
}
