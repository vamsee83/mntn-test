# mntn-test

##Test Details
> Test will verify the Public REST API https://cat-fact.herokuapp.com/facts/ is returning <br />
  cat facts. ex: Cats make more than 100 different sounds whereas dogs make around 10.

## Approach
> Using TestNG as a framework and Java with REST-assured for triggering REST API. Gradle as build tool to build the project and run tests.

## Pros and cons of approach
- Pros
  > As test is written using TestNG, all the test data is passed through xml files. This will help any non-technical person to update the test data and execute the tests <br />
  
  > All the configuration data is passed through xml file, that way data is separated from tests.
  
  > TestNG Reports are generated and present in build folder
  
  > Test is well-structured with all initialization happening in @Beforeclass.
  
- Cons
  > As test is using Public REST API, the server can go down any time and response may be changed.
  
  > More negative tests can be added.
  
  > Log file can be generated using logback
  
## Execution
> Test can be executed using CLI ./gradlew clean test
