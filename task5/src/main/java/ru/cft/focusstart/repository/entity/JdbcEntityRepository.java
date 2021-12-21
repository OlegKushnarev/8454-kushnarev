package ru.cft.focusstart.repository.entity;

import javax.sql.DataSource;

public abstract class JdbcEntityRepository {
    protected final DataSource dataSource;

    public JdbcEntityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}