# LiteDB - A crude database from scratch

Welcome to LiteDB! This is a basic SQL database I implemented from scratch using Java. This database is NOT to be used in production (although you may try, and fail :p).

This database supports the basic SELECT, INSERT, CREATE TABLE, UPDATE and DELETE queries. Query keywords are always lowercase. Most queries are very similar (or the exact same) as PostgreSQL.

-   Create a table:
    ```
    create table students (id int, name varchar(100));
    ```

-   Insert into table:
    ```
    insert into students (id, name) values (1, 'Toby');
    ```

-   Query the table:
    ```
    select * from students where name = 'Toby';
    select * from students;
    ```

-   Update table entries:
    ```
    update students set name = 'Adi' where id = 1;
    update students set name = 'Adi';
    ```

-   Delete entries:
    ```
    delete from students where name = 'Adi';
    delete from students;
    ```
You can also retrieve table metadata.

-   Fetch information for each table in the database, using the `tables_meta` table
    ```
    select * from tables_meta;

    Returns:
    row_size, table_name
    267, columns_meta
    109, tables_meta
    109, students
    ```

-   Fetch the column information for each table in the database, using the `columns_meta` table

    ```
    select * from columns_meta;

    Returns:
    column_size, column_name, column_type, table_name
    4, column_size, int, columns_meta
    104, column_name, varchar(100), columns_meta
    54, column_type, varchar(50), columns_meta
    104, table_name, varchar(100), columns_meta
    4, row_size, int, tables_meta
    104, table_name, varchar(100), tables_meta
    104, name, varchar(100), students
    4, id, int, students
    ```

## Setup
-   Clone this repository
    ```
    git clone https://github.com/clueless-skywatcher/litedb.git
    ```
-   Navigate to litedb folder
    ```
    cd litedb
    ```
-   Run Maven build
    ```
    mvn clean install -f litedb-core/pom.xml
    ```
-   Run the JAR
    ```
    java -jar litedb-core/target/litedb-0.0.1.jar
    ```

Feel free to play around with the `sample.sql` file provided.

## Future Plans:
- Implement buffer manager
- Implement transactions and ACID compliance
- Implement recovery and rollback systems
- Implement indices
- Implement views and materialization
- Implement aggregations and sorting
- Implement support for new data types:
    - bigint
    - text
    - and more...
