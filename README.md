# SVM
Open Software Version Management

## Feature
1. one main version mapping multiple sub component version
2. support manage sub component version and related customized attributes
3. support manage project and product line 
4. support manage project main version sequence
5. easy and simply user management
6. provider sync API for third-party system
7. generate online release notes for main version

## How to Run

### Setup database
1. run MySQL script file doc/DB/DB.sql to build database structure
2. run initial script file doc/DB/DB_INIT.sql to initial database with first setting

### Setup project
1. modify database settings in code/pom.xml 
2. quick run from code: ```mvn spring-boot:run```
3. open browser visit address: http://localhost:8080
4. login by user name: `admin` user pass: `admin`

### Deploy
1. run command `mvn clean install` under folder `code`
2. copy build tar/zip file in folder code/target/build
3. go to run svm.bat or svm.sh after unzip build file

## Screenshots

http://redmine.openthinks.com/projects/open-svm/wiki/Overview