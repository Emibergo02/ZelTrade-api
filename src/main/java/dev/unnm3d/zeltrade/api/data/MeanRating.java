package dev.unnm3d.zeltrade.api.data;

public record MeanRating(String playerName, double mean, int countedTrades) {
    public static MeanRating empty() {
        return new MeanRating(null, 0.0, 0);
    }
}
