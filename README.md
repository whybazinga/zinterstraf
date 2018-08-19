# zinterstraf

In order to start this application you should read below information:

A) Make sure that you have already installed:
1. npm
2. at least java8
3. mysql server

B) Just do the following points step by step:
1. Open the project via a preferable IDE with the installed maven-plugin
2. Import maven dependencies
3. Initialize the database with the following SQL script:
 - DROP SCHEMA IF EXISTS zinterdb;
   CREATE SCHEMA IF NOT EXISTS zinterdb
   CHARACTER SET utf8;
   SET GLOBAL time_zone = '+3:00';
   USE zinterdb;
4. Type the following for creation the react ready-to-use bundle:
 - cd src/main/frontend
 - npm install
 - npm run build && npm run copy
5. Start the application:
 - mvn spring-boot:run

(if java = 9 -> add to jvm: --add-modules java.xml.bind )

