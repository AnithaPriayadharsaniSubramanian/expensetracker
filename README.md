# expensetracker

Microservices :

1) Spring-cloud-config-server
Added a Config-Client (limits-service) and Config-Server (spring-cloud-config-server) for direct refresh of the Configurations.
 
 Initially when the Client is up and running, it loads the configuration details from the git repository (limits-servie.properties) only    once, when we make configuration changes to the git - it is refreshed client through "localhost:8981/actuator/refresh" without restarting the server.
 
 localhost:8981/rest/limits -> 
 
 {
"maximum": 4000,
"minimum": 40
}

This shows the refreshed values from the git.

Also DEV and QA profiles are added to load the environments, spring.profiles.active=dev

2) expense-service         (To store and retrieve the expenses based on month,category,date)

   a) CategoryController
   b) ExpenseController
   
3) budget-plan-service         (interacts with the expense service to calculate the savings for the month)
 
   a) BudgetController   - consumes the expense-service microservices and calculates the Budget

 4) CustomizedResponseEntityExceptionHandler (Handle Exception and Validations)
    
