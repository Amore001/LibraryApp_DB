package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class US03_StepDefs {
    List<String> expectedList;



    @When("the user navigates to {string} page And the user clicks book categories")
    public void the_user_navigates_to_page_and_the_user_clicks_book_categories(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
        BrowserUtil.waitForClickablility(new BookPage().mainCategoryElement, 3);
        Select select = new Select(  new BookPage().mainCategoryElement);
        expectedList = new ArrayList<>();
        List<WebElement> elements= select.getOptions();
        for (WebElement each : elements) {
            expectedList.add(each.getText());
        }
        expectedList.remove(0);
        System.out.println("expectedList = " + expectedList);


    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() throws SQLException {

        String url      = ConfigurationReader.getProperty("library2.db.url") ;
        String username = ConfigurationReader.getProperty("library2.db.username") ;
        String password = ConfigurationReader.getProperty("library2.db.password") ;


        // connect database
        Connection conn = DriverManager.getConnection(url, username,password);
        System.out.println("Connection established");
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stm.executeQuery("select name from book_categories");

        List<String> actualList = new ArrayList<>();

        while(rs.next()){
            String cellValue = rs.getString(1);
            actualList.add(cellValue);

        }

        System.out.println("actualList = " + actualList);

        Assert.assertEquals(expectedList, actualList);

        // connection closed
        conn.close();
        stm.close();
        rs.close();
        System.out.println("Connection closed");


    }


}
