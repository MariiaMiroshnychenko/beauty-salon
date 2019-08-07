package model.dao;

import model.dao.impl.JdbcFactoryDao;

import java.util.Optional;

public abstract class FactoryDao {
    private static Optional<FactoryDao> factory = Optional.empty();

    public abstract LanguageDao languageDao();
    public abstract ProcedureDao procedureDao();
    public abstract RecordDao recordDao();
    public abstract ReviewDao reviewDao();
    public abstract ScheduleDao scheduleDao();
    public abstract UserDao userDao();

    public static FactoryDao getInstance() {
        if (!factory.isPresent()) {
            synchronized (FactoryDao.class) {
                if (!factory.isPresent()) {
                    factory = Optional.of(new JdbcFactoryDao());
                }
            }
        }
        return factory.get();
    }
}
