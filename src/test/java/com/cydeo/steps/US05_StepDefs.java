package com.cydeo.steps;

import com.cydeo.utility.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.*;

public class US05_StepDefs {

    String url      = ConfigurationReader.getProperty("library2.db.url") ;
    String username = ConfigurationReader.getProperty("library2.db.username") ;
    String password = ConfigurationReader.getProperty("library2.db.password") ;
    Connection conn;
    Statement stm;
    ResultSet rs;
    String actualType;

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() throws SQLException {

        String url      = ConfigurationReader.getProperty("library2.db.url") ;
        String username = ConfigurationReader.getProperty("library2.db.username") ;
        String password = ConfigurationReader.getProperty("library2.db.password") ;

        conn = DriverManager.getConnection(url,username,password);
        System.out.println("Connected database");
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query ="select bc.name, count(*) from book_borrow bb\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc";

        rs = stm.executeQuery(query);
        rs.next();
        actualType = rs.getString(1);


    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedType) throws SQLException {

        Assert.assertEquals(expectedType,actualType);



        //close the connection
        conn.close();
        stm.close();
        rs.close();

        System.out.println("Connection closed");



    }
}
