# storage-manager
A Java application with a GUI made using JavaFX for management of a clothing storage. It operates on a MySQL database containing stored clothes data.

![Przechwytywanie](https://github.com/mickol111/storage-manager/assets/22640141/d3dd9296-3c1d-4054-8e79-7a877ce36c16)
<i>Fig.1. Application layout</i>

## Features
- database connection and operation
- creating cloth models
- saving, loading and viewing cloth models
- adding objects to and removing objects from the database

## Project structure
src/main/java/com/controller directory contains controllers of JavaFX application views.
src/main/java/com/model directory contains class files defining classes for various cloth types.
src/main/java/com/util/ClothDAO.java is a DAO class for operations on cloth objects and executing database operations.
src/main/java/com/util/DBUtil.java is a class for database connection. It defines database operations.
src/main/java/com/App.java runs the application.
