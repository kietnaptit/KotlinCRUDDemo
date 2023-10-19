# Kotlin CRUD Demo

This is a simple Work Management App to demonstrate basic tasks between Kotlin and Database (SQLite). Create, Read, Update, Delete (CRUD)

## Basic Information

* Work dataclass (model) with following attributes: ID, Name, Des, Gender, Date
* MainActivity has a list of all works contain in Database, the list is a RecycleView with using WorkRecyclerAdapter. Each row's layout are defined in layout single_work.xml
* DetailActivity uses to Update or Delete a work
* NewActivity uses to Create a new work
* DBHelper uses to create database schema and add sample data into database
* DBHandler contain all CRUD function (getAllWorks, AddAWork, EditAWork, DeleteAWork)


## Bug
* This app contains lots of bug. I am a very beginner in Mobile App Development. I can only make sure basic CRUD task work

## Reference Resources

* Update soon, but thanks ChatGPT a lot