package model.entity;

import java.util.Objects;

public class Procedure {
    private Integer id;
    private Integer code;
    private String name;
    private Integer languageId;

    private Language language;

    public Procedure(Integer id, Integer code, String name, Integer languageId, Language language) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.languageId = languageId;
        this.language = language;
    }

    public Procedure(Integer id, Integer code, String name, Integer languageId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.languageId = languageId;
    }

    public Procedure(Integer code, String name, Integer languageId) {
        this.code = code;
        this.name = name;
        this.languageId = languageId;
    }

    public Procedure() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return Objects.equals(id, procedure.id) &&
                Objects.equals(code, procedure.code) &&
                Objects.equals(name, procedure.name) &&
                Objects.equals(languageId, procedure.languageId) &&
                Objects.equals(language, procedure.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, languageId, language);
    }
}
