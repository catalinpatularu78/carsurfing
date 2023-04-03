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
	-  docker build -t carsurfing-mysql .
	 (to build mysql custom image which includes SQL statements to create the tables and populate the users table. See details in /sql/tables.sql)
	-  docker build -f ride-service/Dockerfile -t rideservice .
	 (to build the rideservice image)
	-  docker build -f user-service/Dockerfile -t userservice .
	  (to build the userservice image)
	- cd web-app && docker build -f Dockerfile -t web-app .
	  (to build the web-app image)
 - docker-compose up (to start the containers). Wait around 1 min until the containers are fully started and healthy.

 - to test the back-end functionality open HTTP client (e.g. Postman) or the integrated front-end with back-end go to http://localhost:3000/join in browser
 
 
 ______________________________________userservice use cases_____________________________
 		**** 1 ****
		to add user send POST request to resource URL http://localhost:9092/userapi/users with body

			(e.g. {
		"firstName": "Alan",
		"middleName": "Sergiu",
		"lastName": "Storm",
		"description": "",
		"email": "alanstorm@gmail.com",
		"phone": "0875675845",
		"verified": "Yes"
		})

    		**** 2 **** 
		to display all the users send GET request  to resource URL http://localhost:9092/userapi/users
		
		
_____________________________________rideservice use cases_________________________________________________________
		**** 1 ****
		to add a ride send POST request to resource URL http://localhost:9091/rideapi/rides with body

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

		**** 2 ****
		to see all rides  send GET request  to resource URL  http://localhost:9091/rideapi/rides

		**** 3 ****
		to delete a ride send DELETE request to resource  http://localhost:9091/rideapi/rides/<rideId>
		(replace <rideID> with an existing id: e.g.  http://localhost:9091/rideapi/rides/1)

		**** 4 ****
		to search for a ride by from/to location and date send POST request to resource URL 
		http://localhost:9091/rideapi/ridesForRouteAndDate with body
		e.g.
		 {
        		"fromLocation": "Cork",
        		"toLocation": "Dublin",
       			 "dateOfDeparture": "2022-05-15"

    		}
			NOTE:
			1. From/to location can be also one of the stops. From/to in the above doesn't
			mean necessarily start location and final destination. It means from/to from the customer POV.
			2. An empty list is returned in the following conditions:
			* ride doesn't exist.
			* From is the same as to location.
			* No spaces left in the ride.
		
		**** 5 ****
		to search for a ride by from/to location send GET request to resource URL with the from/to location in the path. 							E.g. http://localhost:9091/rideapi/rides/Athlone/Kinnegad
		NOTE: Notes from point 4 above are valid for this use case as well.
		
		**** 6 ****
		to book a ride send POST request to resource URL  http://localhost:9091/rideapi/rides/booking with body
			{
  			 "rideId": 1,
   			"passengerId": 3
			}
		NOTE: The use case will add the booking and decrease the number of spaces left if the booking is successful,
		only if the following conditions are true:
		1. if spaces are still available
		2. if a ride exists with the requested ID
		3. if the same booking for the same passenger doesn't exist
		
		
		
		
