# MasterMind
Basic implémentation of this popular game


## Console as GUI

### Build

Use **Maven 3** :

    $ mvn clean install 


By default, **Maven** will build a jar file located at `target/mastermind.jar`


### Run

Launch the regular jar file :

    $ java -jar target/mastermind.jar


## JavaFX as GUI

Subsequents implementation should support **JavaFX** GUI.


### Build

You have to select the specific `fx-mode` and disable the `console-mode` :

    $ mvn clean install '-P!console-mode,fx-mode'


This will build the `target/jfx/app/mastermind-jfx.jar` file.


### Run

Launch the following jar :

    $ java -jar target/jfx/app/mastermind-jfx.jar




