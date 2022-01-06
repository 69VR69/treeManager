package treeManager;

import treeManager.Data.Tree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseTools
    {
        private String ip = "192.168.56.101";
        private String user = "";
        private String password = "";
        private Connection connection;
        
        public DatabaseTools(String ip, String user, String password)
            {
                this.ip = ip;
                this.user = user;
                this.password = password;
                this.open();
            }
        
        public void open()
            {
                try
                    {
                        Class.forName("org.mariadb.jdbc.Driver");
                        connection = DriverManager.getConnection(composeUrl(), this.getUser(), this.getPassword());
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        public void insertTree(Tree t)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertTree);
                        //ps.setInt(1,t.getId());
                        ps.executeUpdate();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        public void updateTree()
            {
                try
                    {
                    PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateTree);
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private String composeUrl() //TODO : finish
        {
            return ip;
        }
        
        public String getUser()
            {
                return user;
            }
        
        public String getPassword()
            {
                return password;
            }
    }
