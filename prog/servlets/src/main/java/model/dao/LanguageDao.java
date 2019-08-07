package model.dao;

import model.entity.Language;

public interface LanguageDao extends GenericDao<Language> {
    Language findLanguageByLocale(String locale);
}
