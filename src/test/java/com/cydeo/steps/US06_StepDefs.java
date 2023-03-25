package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.github.javafaker.Code;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US06_StepDefs {

    BookPage bookPage= new BookPage();
    UsersPage usersPage = new UsersPage();
    String bookName;
    String author;

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBookBtn.click();
        BrowserUtil.waitFor(2);


    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        BrowserUtil.waitForVisibility(bookPage.bookName, 5).sendKeys(bookName);
        BrowserUtil.waitFor(3);
        this.bookName =bookName;

    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        BrowserUtil.waitForVisibility(bookPage.isbn, 5).sendKeys(isbn);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        BrowserUtil.waitForVisibility(bookPage.year, 5).sendKeys(year);


    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        BrowserUtil.waitForVisibility(bookPage.author, 5).sendKeys(author);
        this.author= author;


    }
    @When("the librarian choose the book category {string} And the librarian click to save changes")
    public void the_librarian_choose_the_book_category_and_the_librarian_click_to_save_changes(String bookCategory) {

        Select select= new Select(bookPage.mainCategoryElement);
        select.selectByVisibleText(bookCategory);
        BrowserUtil.waitFor(2);
        bookPage.saveBtn.click();
        BrowserUtil.waitFor(2);


    }

    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String expectedToastMsg) {
       String actualToastMsg= usersPage.toastMessage.getText();
        Assert.assertEquals(expectedToastMsg,actualToastMsg);

    }

    @Then("verify {string} information")
    public void verify_information(String actualBookName) {

        String query =" select id,name,author from books\n" +
                "where name = '"+bookName+"' and author='"+author+"' order by id desc";

        DB_Util.runQuery(query);

        String expectedBookName= DB_Util.getCellValue(1,2);
        System.out.println(expectedBookName);
        Assert.assertEquals(expectedBookName, actualBookName);


    }



}
