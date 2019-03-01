# Candidate Manager ApI
REST API for candidate management.

# Runing instructions
## Edit configurations
1. Copy **src/main/resources/application.properties.sample** as **src/main/resources/application.properties**. **Do not** delete sample file.
2. Add database credentials to the database setup section.
3. Do port and debug logging configurations at app configuration section.
4. Add users to the last section in order to authenticate by basic authentication. Make sure you set the **user count**.
5. Specify and configure email credentials a the email setup section of properties.
## Run by source code
Browse the root directory that contains pom.xml and run:
```sh
mvn clean install
mvn spring-boot:run
```
## Run by JAR executable.
Copy paste new **application.properties** to **target/** folder to inject configurations in to executable. run:
```sh
jar uf  *.jar  application.properties
java -jar *.jar
```
