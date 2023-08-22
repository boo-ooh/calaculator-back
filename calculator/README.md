1 - clone git repository and open with IntelliJ

2 - run the file in "src/main/resource/database/db_creation.sql" in MySql 

3 - update application.properties: 

    - values to your database in properties starting with "spring.datasource" 
    - to run locally you can comment all variables starting with "server."
    - add your preferred text to "api.security.token.secret"

4 - run the class CalculatorApplication