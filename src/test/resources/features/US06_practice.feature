Feature: Books module
  As a librarian, I should be able to add new book into library

  @wip @db
  Scenario Outline: Verify added book is matching with DB Given the "librarian" on the home page must match with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page
    When the librarian click to add book
    And the librarian enter book name "<Book Name>"
    When the librarian enter ISBN "<ISBN>"
    And the librarian enter year "<Year>"
    When the librarian enter author "<Author>"
    And the librarian choose the book category "<Book Category>" And the librarian click to save changes
    Then verify "The book has been created." message is displayed
    And verify "<Book Name>" information

    Examples:

      | Book Name        | ISBN     | Year | Author          | Book Category        |
      | Code Power       | 10111213 | 2021 | Robert C.Martin | Drama                |
      | Head Second Java | 10111314 | 2021 | Kathy Sierra    | Action and Adventure |
      | The Scrum Guide  | 11112030 | 2006 | Mitch Lacey     | Short Story          |







