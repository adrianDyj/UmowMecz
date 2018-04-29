package pl.umowmecz.model;

public enum City {

    GDANSK("Gdańsk"),
    GDYNIA("Gdynia"),
    SOPOT("Sopot");

    private final String displayCity;

    City(String displayCity) {
        this.displayCity = displayCity;
    }

    public String getDisplayCity() {
        return displayCity;
    }
}
