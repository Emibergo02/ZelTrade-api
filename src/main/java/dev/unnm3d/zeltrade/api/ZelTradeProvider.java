package dev.unnm3d.zeltrade.api;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ZelTradeProvider {
    private static ZelTradeAPI<?> api;

    private ZelTradeProvider() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    static @NotNull ZelTradeAPI<?> getApi(){
        return Objects.requireNonNull(api, "API is not set! Please specify in plugin.yml ZelChat as dependency.");
    }

    public static void set(@NotNull ZelTradeAPI<?> api) {
        ZelTradeProvider.api = api;
    }
}
