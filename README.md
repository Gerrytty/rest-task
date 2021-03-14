Приложение запускается на порту 5000, база данных - postgreSQL на порту 5432, в application.properties можно поменять.  
Необходимо создать базу данных blogdb или поменять на h2 database - раскомментировать зависимость в pom.xml и убрать зависимость на postgreSQL, поменять в application.properties драйвер (тоже закомментировать postgreSQL, раскомментировать h2)

Документация для REST без HATEOAS http://localhost:5000/swagger-ui.html  
Документация для HATEOAS http://localhost:5000/  