# expensetracker

Microservices :

1) limits-service 
2) Spring-cloud-config-server

 Added a Config-Client (limits-service) and Config-Server (spring-cloud-config-server) for direct refresh of the Configurations.
 
 Initially when the Client is up and running, it loads the configuration details from the git repository (limits-servie.properties) only once.
 So, when we make configuration changes to the git and is refreshed to client through "localhost:8981/actuator/refresh"without restarting the server.
 
 localhost:8981/rest/limits -> 
 
 {
"maximum": 4000,
"minimum": 40
}

This shows the refreshed values from the git through @RefreshScope.

Also DEV and QA profiles are added so that the current active environment is loaded through spring.profiles.active=dev
