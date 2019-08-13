package model.service.general;

import model.entity.Language;

public interface LanguageService {
    Language findLanguageByLocale(String locale);
}
