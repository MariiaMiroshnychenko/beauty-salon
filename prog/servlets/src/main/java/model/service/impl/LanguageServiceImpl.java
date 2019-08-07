package model.service.impl;

import model.dao.FactoryDao;
import model.dao.LanguageDao;
import model.entity.Language;
import model.service.LanguageService;

public class LanguageServiceImpl implements LanguageService {
    @Override
    public Language findLanguageByLocale(String locale) {
        LanguageDao languageDao = FactoryDao.getInstance().languageDao();
        Language language = languageDao.findLanguageByLocale(locale);

        languageDao.close();
        return language;
    }
}
