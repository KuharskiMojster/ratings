package si.kuharskimojster.models;

public enum RatingEnum {
    EXCEEDS_EXPECTATIONS(6), EXCELLENT(5), SUPERB(4), NICE(3), NEEDS_IMPROVEMENT(2), POOR(1);

    private int value;

    public int getRating() {
        return value;
    }

    private RatingEnum(int value) {
        this.value = value;
    }
}