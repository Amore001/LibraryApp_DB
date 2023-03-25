package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class US04_StepDefs {
    BookPage bookPage = new BookPage();
    UsersPage usersPage = new UsersPage();

    @When("the user searches for {string} book And the user clicks edit book button")
    public void the_user_searches_for_book_and_the_user_clicks_edit_book_button(String bookName) {

        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(usersPage.editUser, 5).click();
        BrowserUtil.waitFor(2);

        String actualName = bookPage.bookName.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");


    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() throws SQLException {


        String url      = ConfigurationReader.getProperty("library2.db.url") ;
        String username = ConfigurationReader.getProperty("library2.db.username") ;
        String password = ConfigurationReader.getProperty("library2.db.password") ;

        // connection with database

        Connection conn = DriverManager.getConnection(url, username, password);

        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stm.executeQuery("");

        String actualName = bookPage.bookName.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");







    }
}
