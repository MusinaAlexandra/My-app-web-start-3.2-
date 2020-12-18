package org.mycompany.myname.Services;

import org.mycompany.myname.Models.UserProfile;
import org.mycompany.myname.Models.UsersDAO;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static Connection connection;

    static  {
        connection = getMysqlConnection();
    }

    public static UserProfile getUser(String login) {
        try {
            return (new UsersDAO(connection).get(login));
        } catch (SQLException e) {
            return null;
        }
    }

    public static void addUser(UserProfile userProfile) {
        try {
            addUser(userProfile.getLogin(), userProfile.getPass(), userProfile.getEmail());
        } catch (DBException ignored) { }
    }

    public static void addUser(String login, String pass, String mail) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(login, pass, mail);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("tpp?").                 //db name
                    append("user=root&").           //login
                    append("password=root&").       //password
                    append("serverTimezone=UTC");   //serverTimezone

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
