package model.entity;

import java.util.Objects;

public class Language {
    private Integer id;
    private String locale;

    public Language(Integer id, String locale) {
        this.id = id;
        this.locale = locale;
    }

    public Language() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) &&
                Objects.equals(locale, language.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locale);
    }
}
