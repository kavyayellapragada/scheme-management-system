# Scheme Management System

## Simple Application For Fetching all the applicable government schemes based on the options selected by User.

### Description
Scheme Management System is a web application for all relevant government schemes applicable to user based on the options selected by user. This application provides all the things that a naive user needs. Through this application one can easily get schemes information related to Agriculture, Entrepreneurship, Education, Sports, Health. Application maintenance is easy.

### Requirements
* Java 11 or higher.
* Maven
* MySQL Database
* Run on Tomcat 9

### How To Run
* As it's a Maven Project, you can call `mvn clean install` in terminal(in project directory) to get a war file.(You can find it in **target** folder)
* You need to have MySQL database to import tables from projectdb database
* Default username is `root` and password is `root`.
* Deploy the war file to the tomcat server and visit http://localhost:8080/scheme_mngmt in chrome browser

## Application Overview
### Home Page (Initial Opening Page)
![Home Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/home1.png)

### List of available scheme categories
![Home Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/home2.png)

### About
![Home Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/home3.png)

### How It Works
![Home Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/home4.png)

### Fill User Details
![User Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/form.png)

### Admin Operations (List Users, Delete existing User, List Schemes, Add new Scheme, Delete existing Scheme)
![Admin Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/adminform.png)

### Project Schema
![DB Page](https://raw.githubusercontent.com/kavyayellapragada/scheme-management-system/main/scheme_management_system/src/main/webapp/images/projectdb.png)

