Team B project 

Carsurfing app:
* Maven project with two modules, both being Spring boot apps.
* Functionality can be tested using docker-compose.
* To make changes, checkout local branch. 
* Push to master only after making sure the functionality is working after testing manually with the services deployed in Docker containers.

To test with Docker run the following commands:

- clone project from https://github.com/catalinpatularu78/carsurfing.git
- cd carsurfing
- checkout your local working branch
- mvn clean install (this will build and package jars for two services userservice and rideservice)
- build images for each service:
	-  docker build -t carsurfing-mysql-db .
	 (to build mysql custom image which includes SQL statements to create the tables and populate the users table. See details in /sql/tables.sql)
	-  docker build -f ride-service/Dockerfile -t rideservice . 
	 (to build the rideservice image)
	-  docker build -f user-service/Dockerfile -t userservice .
	  (to build the userservice image)
 - docker-compose up (to start the containers)
 - to test the functionality open HTTP client (e.g. Postman)
 	-  userservice use cases:
 		1.  to add user send POST request to resource URL http://localhost:10556/userapi/users with body
 		
 		(e.g. {
        "firstName": "Alan",
        "middleName": "Sergiu",
        "lastName": "Storm",
        "description": "",
        "email": "alanstorm@gmail.com",
        "phone": "0875675845",
        "verified": "Yes"
        })
        
    	2. to display all the users send GET request  to resource URL http://localhost:10556/userapi/users
    	
	- rideservice use cases:
		1. to add a ride send POST request to resource URL http://localhost:10555/rideapi/rides with body
		
		( e.g. {
 
        "driverId":1,
        "fromLocation": "Ashbourne",
        "toLocation": "Cork",
        "dateOfDeparture": "2024-11-11",
        "estimatedDepartureTime": "10:00:00",
        "spacesLeft": 1,
        "stop1": "Bray",
        "stop2": "",
        "stop3": ""
		})
		
		2. to see all rides  send GET request  to resource URL  http://localhost:10555/rideapi/rides
		
		3. to delete a ride send DELETE request to resource  http://localhost:10555/rideapi/rides/<rideId>
		(replace <rideID> with an existing id: e.g.  http://localhost:10555/rideapi/rides/1)
		
