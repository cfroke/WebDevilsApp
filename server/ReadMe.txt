Usage of the server:
-- client classes must be present on the android app
-- client classes and methods should be used to retrieve and maintain information
-- initially the server will be hard coded as being located at "http://localhost:8080" for 
   development purposes 
-- The server MUST be initialized before the android app is run

If security is desired ...
-- call System.setSecurityManager(new RMISecurityManager()); in main impl.
-- "security.policy" must be present under the java project folder in order to run 
   successfully
-- will need the following command prompt when server is started  ...
-Djava.security.policy=file:${workspace_loc}/server/security.policy 
-Djava.rmi.server.codebase=file:${workspace_loc}/server/bin/

How to run the server:
-- start the server from the command line by going to the the location of the 
   runnable jar file in the dist folder and enter "java -jar server.jar"
-- start the server in an IDE by running the "Server.java" class

 