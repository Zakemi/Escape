# Escape

This project is a small game, where you should escape from the enemies. Have fun!

## How can you run the project?

You will need Maven and Java. The project uses a database connection, so you must install the lib/ojdbc.jar file from project. Use this command from lib directory:

$ mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dpackaging=jar -Dversion=11.2.0.2.0

You can make a jar file from the project:

$ mvn compile
$ mvn package

After you can run the jar:

$ java -jar target/Escape-1.0-jar-with-dependencies.jar

### Save rank

If you have password for the database, you can save your rank easy.

Unfortunately you can't save if you don't have password.
