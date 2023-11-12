# RPG API

This project is just a hobby.
Some RPG thoughts I'm putting down on paper.

### Requirements
* [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Git](https://git-scm.com/)
* [GraalVM](https://www.oracle.com/java/technologies/downloads/#graalvmjava21)

## Getting started
:warning: :warning: BEFORE START, MAKE SURE DOCKER ENVIROMENT IS UP AND RUNNING. :warning: :warning:

In order to build the project, run the command below.
```
./gradlew build
```

In order to start the docker containers, run the command below.
```
./gradlew composeUp
```

NOTE: "./gradlew run" command won't work yet due some Java 21 incompabilities.
In order to start the application, we need to execute the Application.main() method.
