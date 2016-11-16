# Escape

This project is a small game where you should escape from the enemies. Have fun!

Screenshots about the game are availabe in the screenshots directory.

## How can you run the project?

### Easy way

Run this command from the project's directory OR doubleclick on the runnable jar file.

```$ java -jar runnable_jar/Escape-1.0-jar-with-dependencies.jar```

### Other way

You will need Maven and Java. The project uses a database connection, so you must install the lib/ojdbc.jar file from project. Use this command from lib directory:

```$ mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dpackaging=jar -Dversion=11.2.0.2.0```

You can make a jar file from the project:

```$ mvn compile```
```$ mvn package```

After you can run the jar:

```$ java -jar target/Escape-1.0-jar-with-dependencies.jar```

### Save rank

If you have a password for the database, you can save your rank easily.

For security reasons you can't save your score if you don't have password.


