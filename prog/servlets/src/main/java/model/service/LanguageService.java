package model.service;

import model.entity.Language;

public interface LanguageService {
    Language findLanguageByLocale(String locale);
}
