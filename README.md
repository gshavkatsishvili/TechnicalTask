### About test automation task:
Build a test framework to automate a RESTful WS (https://gorest.co.in/). 
1. Create functional tests for the Users API:
https://gorest.co.in/public-api/users

### Required Tools
Java 18<br />
Maven 3.8.5<br />
allure 2.17.3

### Launch of test
In the root of project put in command prompt:<br />
```mvn clean test -DsuiteXmlFile="testng.xml"```<br />
OR<br />
```mvn clean test -Dtest="User_Data_Writer_Test_Suite"``` (if you want to execute only 1 case)

```mvn clean test -Dtest="User_Test_Suite"``` (if you want to execute only 1 case)

### Test report
In the root of project put in command prompt:<br />
```allure serve build/allure-results/```  <br />
Then open ```Behaviors``` tab and you will see the result <br />
