Usage of the server:
-- client classes must be present on the android app
-- client classes and methods should be used to retrieve and maintain information
-- initially the server will be hard coded as being located at "http://localhost:8080" for 
   development purposes 
-- The server MUST be initialized before the android app calls the server methods
-- Once the server is started, it can be left running until you are finished with it.

How to run the server:
-- start the server from the command line by going to the the location of the 
   runnable jar file in the 'dist' folder and enter "java -jar WebDevilsServer.jar"
   
   Example:
   C:/ ... /WebDevilsApp/server/dist>java -jar WebDevilsServer.jar

-- If the desired, the "WebDevilsServer.jar" file can be moved to a location of your choosing
   and run from there using the command line.
-- The server can also be started in an IDE by running the "Server.java" class
-- If you get this error ...
   
   Server failed: java.rmi.server.ExportException: Port already in use: 8080; nested exception is:
        java.net.BindException: Address already in use: JVM_Bind
        
   This means that the server is already running somewhere on your system. You'll have to find it and
   use it, stop it, or restart your computer. Only one instance of the server can be bound to a
   given port at one time.
   