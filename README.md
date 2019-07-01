# Transaction Service
This was completed for ME Bank code assessment in Java 8.

## Usage
    
### Running via distributions package
This has been build using gradle and can be run with gradle wrapper which has been included.

Once the the zip file has been extracted, 
1. Go to the extraction directory. 
2. Build the project with 
3. Check RequestControllerTest to verify the test case and run it on the IDE to see the test results.

The test RequestControllerTest will be run and outputs the response as below. 

    10:02:40.448 [Test worker] INFO  a.c.m.t.service.RequestController - Account Status: 
    Relative balance for the period:-25.0
    Number of transactions included:1   

Test data can be changed in the processAccountStatusRequest method. 
I have used the given sample input file and placed it under src/test/resources.


## Design

 - I have used standard MVC pattern, so that this can be easily converted into a web-service. 
 - RequestController, handles the incoming requests and passes them to AccountService. 
 - AccountService uses the TransactionServiceDao to get all the transactions of the account for a given period. 
 - TransactionServiceDao imports the CSV file and loads all the transactions when initialised. 
