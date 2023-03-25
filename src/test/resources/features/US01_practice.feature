
Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.

  Background:
   Given Establish the database connection

  Scenario: verify users has unique IDs
     When Execute query to get all IDs from users Then verify all users has unique ID