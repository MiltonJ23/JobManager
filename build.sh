export PATH_TO_BUILD=build
export PATH_TO_SRC=src/org/Task/Manager
export PATH_TO_FX=lib/javaFx

echo "Trying to build the models classes ....."
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/Process.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/ProcessSpool.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/ProcessUnit.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/Scheduler.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/User.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/models/InfoUser.java


echo "Trying to build the Controller classes ....."

javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/HistorController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/ControlProcessController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/InscriptionUtilisateurController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/AjoutProcessController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/InscriptionUtilisateurController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/ConnexionController.java
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Controller/AccueilController.java

echo "Building the Main Class...."
javac -d build -cp "$PATH_TO_FX/*:$PATH_TO_BUILD" $PATH_TO_SRC/Main.java

echo "Copying all the resources...."
cp -r res build

echo "Building the Jar File"
jar cvfm JobManager.jar MANIFEST.MF -C build/ .
