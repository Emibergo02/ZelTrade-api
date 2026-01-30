package dev.unnm3d.zeltrade.api.enums;

import lombok.Getter;

@Getter
public enum ViewerUpdate {
    TRADE_CREATE('0', Actor.SPECTATOR, UpdateType.CREATE),

    TRADER_WINDOW('1', Actor.TRADER, UpdateType.WINDOW),
    TRADER_PRICE('3', Actor.TRADER, UpdateType.PRICE),
    TRADER_ITEM('4', Actor.TRADER, UpdateType.ITEM),
    TRADER_STATUS('5', Actor.TRADER, UpdateType.STATUS),

    CUSTOMER_WINDOW('6', Actor.CUSTOMER, UpdateType.WINDOW),
    CUSTOMER_PRICE('8', Actor.CUSTOMER, UpdateType.PRICE),
    CUSTOMER_ITEM('9', Actor.CUSTOMER, UpdateType.ITEM),
    CUSTOMER_STATUS('a', Actor.CUSTOMER, UpdateType.STATUS),

    SPECTATOR_WINDOW('b', Actor.SPECTATOR, UpdateType.WINDOW);

    private final char id;
    private final Actor actorSide;
    private final UpdateType updateType;

    ViewerUpdate(char id, Actor actorSide, UpdateType updateType) {
        this.id = id;
        this.actorSide = actorSide;
        this.updateType = updateType;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    public static ViewerUpdate valueOf(char id) {
        return switch (id) {
            case '0' -> TRADE_CREATE;
            case '1' -> TRADER_WINDOW;
            case '3' -> TRADER_PRICE;
            case '4' -> TRADER_ITEM;
            case '5' -> TRADER_STATUS;
            case '6' -> CUSTOMER_WINDOW;
            case '8' -> CUSTOMER_PRICE;
            case '9' -> CUSTOMER_ITEM;
            case 'a' -> CUSTOMER_STATUS;
            case 'b' -> SPECTATOR_WINDOW;
            default -> throw new IllegalArgumentException("Invalid trade update type char");
        };
    }

    public static ViewerUpdate valueOf(Actor actor, UpdateType updateType) {
        return switch (actor) {
            case TRADER -> switch (updateType) {
                case CREATE -> TRADE_CREATE;
                case WINDOW -> TRADER_WINDOW;
                case PRICE -> TRADER_PRICE;
                case ITEM -> TRADER_ITEM;
                case STATUS -> TRADER_STATUS;
            };
            case CUSTOMER -> switch (updateType) {
                case CREATE -> TRADE_CREATE;
                case WINDOW -> CUSTOMER_WINDOW;
                case PRICE -> CUSTOMER_PRICE;
                case ITEM -> CUSTOMER_ITEM;
                case STATUS -> CUSTOMER_STATUS;
            };
            case SPECTATOR, ADMIN -> switch (updateType) {
                case CREATE -> TRADE_CREATE;
                case WINDOW -> SPECTATOR_WINDOW;
                default -> throw new IllegalStateException("Unexpected value: " + updateType);
            };

        };
    }
}