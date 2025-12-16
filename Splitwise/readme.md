User should be able to create account and manage profile information.
user should be able to create groups and add members to the groups.
user should be able to add expenses to the group and specifying the amount, description, and participants involved.
System should automatically split the expenses equally among the participants.
user should be able to view other users and settle their balance.
System should support different split methods, such as equal split, percentage split, and exact amounts.
Users should be able to view their transaction history and group expenses.
The system should handle concurrent transactions and ensure data consistency

-----------******************----------
Entities: User, Group, Expense, BalanceSheet, split,  SplitStrategy, SplitwiseSystem

Requirements:

User Management:
Create and manage user profiles
Track user balances
Handle user relationships

Group Management:
Create and manage groups
Add/remove members
Track group expenses

Expense Management:
Add expenses to groups or individuals
Support different split types (EQUAL, EXACT, PERCENTAGE)
Track expense history

Balance Management:
Calculate balances between users
Track who owes whom
Handle settlements

Transaction Management:
Record transactions
Track payment status
Generate balance reports