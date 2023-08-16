package za.co.serengti.util;

public enum ProductCategory {
    ELECTRONICS("ELECTRONICS"),
    GROCERIES("GROCERIES"),
    CLOTHING("CLOTHING"),
    BOOKS("BOOKS"),
    HOME_AND_GARDEN("HOME AND GARDEN"),
    HEALTH_AND_BEAUTY("HEALTH AND BEAUTY"),
    TOYS_AND_GAMES("TOYS AND GAMES"),
    AUTOMOTIVE("AUTOMOTIVE"),
    SPORTS_AND_OUTDOORS("SPORTS AND OUTDOORS"),
    OFFICE_SUPPLIES("OFFICE SUPPLIES"),
    PET_SUPPLIES("PET SUPPLIES"),
    MUSIC_AND_ENTERTAINMENT("MUSIC AND ENTERTAINMENT"),
    JEWELRY_AND_ACCESSORIES("JEWELRY AND ACCESSORIES"),
    TRAVEL_AND_LUGGAGE("TRAVEL AND LUGGAGE"),
    ARTS_AND_CRAFTS("ARTS AND CRAFTS");

    private final String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProductCategory fromDbValue(String value) {
        for (ProductCategory category : values()) {
            if (category.getValue().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + value);
    }

    public static boolean isValidCategory(String value) {
        for (ProductCategory category : values()) {
            if (category.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
