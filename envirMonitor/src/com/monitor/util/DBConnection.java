package com.monitor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnection {
	static String drivername = null;
    static String url = null;
    static String username = null;
    static String password = null;
    static Connection conn = null;
    static {
        setDataBase();
        try {
            Class.forName(drivername);
          //  System.out.println("驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void setDataBase() {
        String filePath = DBConnection.class.getResource("").toString();
        try {
            filePath = DBConnection.class.getResource("").toURI().getPath();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        File file = new File(filePath + "sql.txt");
        String files = null;
        int index = 0;
        int flag = 1;
        try {
            FileInputStream filein = new FileInputStream(file);
            BufferedReader read = new BufferedReader(new InputStreamReader(filein));
            try {
                while ((files = read.readLine()) != null) {
                    index = files.indexOf(":");
                    switch (flag) {
                        case 1:
                            drivername = files.substring(index + 1, files.length());
                        case 2:
                            url = files.substring(index + 1, files.length());
                        case 3:
                            username = files.substring(index + 1, files.length());
                        case 4:
                            password = files.substring(index + 1, files.length());
                    }
                    flag++;
                }
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() {
      
        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
           // System.out.println("连接创建成功");
        } catch (SQLException e) {
        	System.out.println("连接创建失败");
            e.printStackTrace();
        }
        return conn;
    }

    public static void free(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭失败");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("关闭失败");
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (SQLException e) {
                    System.out.println("关闭失败");
                }
            }
        }
    }
    
    public static void freeStatement(ResultSet rs, Connection conn, Statement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭失败");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("关闭失败");
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                    	stmt.close();
                    }
                } catch (SQLException e) {
                    System.out.println("关闭失败");
                }
            }
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		conn = DBConnection.getConnection(); 
	}
}
