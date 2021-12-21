package ru.cft.focusstart.repository.entity;

import ru.cft.focusstart.repository.DataSourceProvider;

import javax.sql.DataSource;

public abstract class JdbcEntityRepository {
    protected final DataSource dataSource;

    public JdbcEntityRepository() {
        this.dataSource = DataSourceProvider.getDataSource();
    }
}