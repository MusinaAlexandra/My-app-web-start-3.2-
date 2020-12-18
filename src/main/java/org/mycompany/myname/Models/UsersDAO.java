package org.mycompany.myname.Models;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserProfile get(String login) throws SQLException {
        return executor.execQuery("select * from users where user_login='" + login + "'", result -> {
            result.next();
            return new UserProfile(result.getString(2), result.getString(3), result.getString(4));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_login='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String login, String pass, String mail) throws SQLException {
        executor.execUpdate("insert into users (user_login, user_pass, user_mail) " +
                "values ('" + login + "', '" + pass + "', '" + mail + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users " +
                "(id bigint auto_increment, user_login varchar(32) unique, " +
                "user_pass varchar(32), user_mail varchar(255), primary key (id))");
    }

    public void dropTableUsers() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
