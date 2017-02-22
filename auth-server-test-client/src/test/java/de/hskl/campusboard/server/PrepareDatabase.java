/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.fail;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class PrepareDatabase
{

    private static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:3306/campusboard_personal";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "VERY_SECURE";

    public void init()
    {
        System.out.println("get connection");
        try (Connection cn = getConnection())
        {
            final Path DELETE_ALL_SCRIPT = Paths.get(PrepareDatabase.class.getResource("/db_scripts/test_delete_all.sql").toURI());
            final Path INSERT_CLIENTS_SCRIPT = Paths.get(PrepareDatabase.class.getResource("/db_scripts/test_client.sql").toURI());
            final Path INSERT_USERS_SCRIPT = Paths.get(PrepareDatabase.class.getResource("/db_scripts/test_users.sql").toURI());
            final Path INSERT_CANTEEN_SCRIPT = Paths.get(PrepareDatabase.class.getResource("/db_scripts/test_canteen.sql").toURI());
            final Path INSERT_TIMETABLE_SCRIPT = Paths.get(PrepareDatabase.class.getResource("/db_scripts/test_timetable.sql").toURI());
        
            insert(DELETE_ALL_SCRIPT, cn);
            insert(INSERT_CLIENTS_SCRIPT, cn);
            insert(INSERT_USERS_SCRIPT, cn);
            insert(INSERT_CANTEEN_SCRIPT, cn);
            insert(INSERT_TIMETABLE_SCRIPT, cn);
        }
        catch (SQLException | IOException | URISyntaxException e)
        {
            e.printStackTrace();
            fail("couldn't prepare database");
        }        
    }

    private void insert(Path sqlFilePath, Connection cn) throws SQLException, IOException
    {
        StringBuilder queryBuilder = new StringBuilder();
        
        for (String query : Files.readAllLines(sqlFilePath, StandardCharsets.UTF_8))
        {
            if(query.isEmpty())
                continue;
            
            queryBuilder.append(query);
            
            if(query.contains(";"))
            {
                Statement stat = cn.createStatement();
                stat.executeUpdate(queryBuilder.toString());
                queryBuilder.setLength(0);
            }            
        }
        
        if(queryBuilder.length() > 0)
            fail("UNFINISHED SQL-QUERY: " + queryBuilder.toString());
    }

    private Connection getConnection() throws SQLException
    {
        Connection cn = DriverManager.getConnection(DATABASE_CONNECTION_URL, DATABASE_USER, DATABASE_PASSWORD);
        cn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return cn;
    }

}
