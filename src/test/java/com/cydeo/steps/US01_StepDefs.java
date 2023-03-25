package com.cydeo.steps;

import com.cydeo.utility.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.*;

public class US01_StepDefs {

     Connection conn;
     Statement stm;
     ResultSet rs;
     ResultSetMetaData rsmd;


    @Given("Establish the database connection")
    public void establish_the_database_connection() throws SQLException {

        // connect database
        String url      = ConfigurationReader.getProperty("library2.db.url") ;
        String username = ConfigurationReader.getProperty("library2.db.username") ;
        String password = ConfigurationReader.getProperty("library2.db.password") ;

         conn = DriverManager.getConnection(url, username,password);
         System.out.println("Connection established");
         stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);





    }
    @When("Execute query to get all IDs from users Then verify all users has unique ID")
    public void execute_query_to_get_all_i_ds_from_users_then_verify_all_users_has_unique_id() throws SQLException {

        rs = stm.executeQuery("select count(*) from books");
        rsmd = rs.getMetaData();

        rs.next();
        int actualCellNumber = rs.getInt(1);
        System.out.println("actualCellNumber = " + actualCellNumber);

        rs = stm.executeQuery("select distinct count(*) from books");


        rs.next();
        int  expectedCellNumber = rs.getInt(1);
        System.out.println("expectedCellNumber = " + expectedCellNumber);

        Assert.assertEquals(actualCellNumber,expectedCellNumber);


        // close connection

        conn.close();
        stm.close();
        rs.close();
        System.out.println("closed connection");


    }



}
