# Cyral JDBC Drivers
This repository contains custom JDBC drivers for access clusters behind [cyral](https://cyral.com) data access tool. 

The current latest version is 0.0.1, intended to serve personal needs.

## Pre-requisites
1. Cyral CLI installed and configured.
2. Access to Cyral managed database clusters.
3. Any SQL client that allows you to register custom JDBC drivers.

## Design
The custom JDBC drivers are based on the original JDBC drivers for MySQL and PostgreSQL. The custom drivers are designed to be drop-in replacements for the original drivers. The custom drivers are designed to be used with any SQL client that allows you to register custom JDBC drivers.

At a high level, the custom drivers work by intercepting the connection requests and inject an existing valid access token generated using cyral CLI.


## Supported Databases
1. MySQL
2. PostgreSQL

## How to use

### Applications
Use the driver just like any other JDBC driver.
1. Declare a dependency on artifact.
    ```xml
    <dependency>
        <groupId>pro.anuj.skunkworks</groupId>
        <artifactId>cyral-jdbc</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    ```
2. Use the driver in your code just like any other JDBC driver.
   1. example for MySQL:
    ```java
    Class.forName("pro.anuj.skunkworks.cyral.jdbc.mysql.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://<host>:<port>/<database>", "<username>", "<password>");
    ```
   2. In a spring app:
   ```yaml
   spring:
     datasource:
        url: jdbc:mysql://cyral.acme.com:3306
        username: root
        password: root
        driverClassName: pro.anuj.skunkworks.cyral.jdbc.CyralMySqlDriver
    ```
3. The driver will automatically inject the access token into the connection request.

### IntelliJ IDEA based IDEs
1. Download and save custom JDBC drivers for Cyral from GitHub releases.
    https://github.com/indyaah/cyral-jdbc-driver/releases
    https://github.com/indyaah/cyral-jdbc-driver/releases/download/0.0.1/cyral-jdbc-driver-0.0.1.zip
2. Unzip the downloaded file.
3. Fire up IntelliJ IDEA.
4. In any project, open database tool window and click on database with gear icon.
5. Select drivers tab.
6. Click on the "+" icon to add new driver.
7. Specify Name as "Cyral <MySQL/POSTGRESQL>". You can choose any name you prefer.
8. Click on "+" icon to add new driver file.
9. Select the path to the downloaded JDBC driver jar files.
10. Make sure to select all jar files from the zip file.
11. Click on OK.
12. If the JAR files have been added correctly you would see a bunch of driver class names available in the class drop down.
13. Select the driver class name based on the database you are connecting to. Either `pro.anuj.skunkworks.cyral.jdbc.mysql.Driver` for MySQL or `pro.anuj.skunkworks.cyral.jdbc.postgres.Driver` for PostgreSQL.
14. Create a new data source and select the driver you just added. You shouldnt have to password field. You can leave it blank. 