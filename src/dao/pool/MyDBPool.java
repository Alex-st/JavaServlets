package dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 6/8/15.
 */
public class MyDBPool {
    Map<Connection, Boolean> dbpool;
 //   Connection connWithEncoding;
    String dbName;
    String user;
    String pass;

    public MyDBPool() {
        dbpool = new HashMap<Connection, Boolean>();
    }

    //Create pool with one connection
    public MyDBPool(String dbName, String user, String pass) {
        this();
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName+"?characterEncoding=UTF-8", user, pass);

//            System.out.println("jdbc:mysql://127.0.0.1:3306/"+dbName);
//            System.out.println(conn == null);
            dbpool.put(conn, true);

//            connWithEncoding =
//                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName+"?characterEncoding=UTF-8", user, pass);

        } catch (InstantiationException e) {
            Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public synchronized Connection addNewConnection() throws SQLException{
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName+"?characterEncoding=UTF-8", user, pass);

        dbpool.put(conn, true);
        return conn;

    }

    @Override
    public void finalize() {
        for (Map.Entry<Connection, Boolean> i: dbpool.entrySet()) {
            try {
                i.getKey().close();
            } catch (SQLException e) {
                Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
            }
        }
//        try {
//            connWithEncoding.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public int numberOfConnections() {
        return dbpool.size();
    }

    public int numberOfFreeConnections() {
        int temp = 0;
        for (Map.Entry<Connection, Boolean> i: dbpool.entrySet()) {
            if (i.getValue() == true)
                temp++;
        }
        return temp;
    }

    public synchronized Connection getConnection() {
       Connection temp = null;
        for (Map.Entry<Connection, Boolean> entry : dbpool.entrySet()) {
           if (entry.getValue() == true) {
               temp = entry.getKey();
               break;
           }
        }
        if (temp == null)
            try {
                temp = addNewConnection();
            } catch (SQLException e) {
                Logger.getLogger(MyDBPool.class.getName()).log(Level.SEVERE, null, e);
            }
        else {
            dbpool.remove(temp);
            dbpool.put(temp, false);
        }
        return temp;
    }

//    public Connection getConnWithEncoding() {
//        return connWithEncoding;
//    }

    public synchronized void releaseConnection(Connection con) {
        dbpool.remove(con);
        dbpool.put(con, true);
    }
}