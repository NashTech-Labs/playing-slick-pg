# playing-slick-pg

Building Reactive Play application with [Slick](https://github.com/slick/slick) extensions for PostgreSQL

This is a classic CRUD application, backed by a PostgreSQL database. It demonstrates:
- Handling asynchronous results, Handling time-outs
- Achieving, Futures to use more idiomatic error handling.
- Accessing a JDBC database, using Slick.
- Achieving, table pagination and sorting functionality.
- Embedded JS & CSS libraries with [WebJars](http://www.webjars.org/).
- Play and Scala-based template engine implementation
- Integrating with a CSS framework (Twitter Bootstrap 3.1.1).  Twitter Bootstrap requires a different form layout to the default one that the Play form helper generates, so this application also provides an example of integrating a custom form input constructor.
- Bootswatch-Yeti with Twitter Bootstrap 3.1.1 to improve the look and feel of the application

-----------------------------------------------------------------------
###Instructions :-
-----------------------------------------------------------------------
* Install PostgreSQL, if you do not have it already. You can get it from [here](http://www.postgresql.org/download/)
* Create a database called `test`
* To run the Play Framework, you need JDK 6 or later
* Install Typesafe Activator if you do not have it already. You can get it from here: http://www.playframework.com/download
* Execute `./activator clean compile` to build the product
* Execute `./activator run` to execute the product
* playing-slick-pg should now be accessible at localhost:9000

-----------------------------------------------------------------------
###References :-
-----------------------------------------------------------------------
* [Play Framework](http://www.playframework.com/)
* [Slick Plugin for Play](https://github.com/playframework/play-slick)
* [Slick - Functional Relational Mapping for Scala](http://slick.typesafe.com/)
* [Slick extensions for PostgreSQL](https://github.com/tminglei/slick-pg)
* [Bootstrap 3.1.1](http://getbootstrap.com/css/)
* [Bootswatch](http://bootswatch.com/yeti/)
* [WebJars](http://www.webjars.org/)
