package christmas.config;

public enum BadgeConfig {
    FIRST_BADGE("산타"),
    SECOND_BADGE("트리"),
    THIRD_BADGE("별"),
    BADGE_NONE("없음");

    private final String badge;

    BadgeConfig(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }
}
