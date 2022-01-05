package com.urise.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String query) {
        execute(query, PreparedStatement::execute);
    }

    public <T> T execute(String query, SqlExecutor<T> sqlExecutor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return sqlExecutor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }
}
