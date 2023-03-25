package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.*;

public class US02_StepDefs {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage= new DashBoardPage();
    String expectedBorrowedBooks;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {

        loginPage.login(user);
        BrowserUtil.waitFor(3);


    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        expectedBorrowedBooks = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("expectedBorrowedBooks = " + expectedBorrowedBooks);

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() throws SQLException {

        // connect database
        String url      = ConfigurationReader.getProperty("library2.db.url") ;
        String username = ConfigurationReader.getProperty("library2.db.username") ;
        String password = ConfigurationReader.getProperty("library2.db.password") ;

        Connection conn = DriverManager.getConnection(url,username,password);
        System.out.println("Connection set up");
        Statement stm =conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery("select count(*) from book_borrow\n" +
                "where is_returned=0");


        ResultSetMetaData rsmd = rs.getMetaData();


        rs.next();
        String actualBorrowedBooks = rs.getString(1);
        System.out.println("actualBorrowedBooks = " + actualBorrowedBooks);

        Assert.assertEquals(expectedBorrowedBooks, actualBorrowedBooks);

        //close the connection
        conn.close();
        stm.close();
        rs.close();


    }


}
