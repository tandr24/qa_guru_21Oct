package data;

public enum Language {
    RU("ru", "Все меню"),
    EN("en", "All Menu"),
    RS("sr-latn", "Ceo Meni");

    public final String value;
    public final String description;

    Language(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
