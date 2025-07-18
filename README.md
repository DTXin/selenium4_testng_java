# Introduction
Demo building an automation framework from scratch with Selenium 4 and REST Assured.

# Libraries and Frameworks
This sample using the following libraries and frameworks:
- Java 21 – Programming language
- Selenium WebDriver 4 – For browser automation
- Maven – Build tool and package management
- TestNG – Test framework for structuring and executing tests
- REST Assured – For API automation
- Allure Report – For test reporting

# Key Features
- Page Object Model design.
- Supports parallel and sequential execution of tests.
- Supports cross browser testing in local and remote (like Firefox, Chrome, Edge).
- Supports Cross Platform Testing (like Windows, linux).
- Data-driven Testing with parameterization support.
- Log test actions to log4j.
- Ability to retry a test case fail/skip.
- Ability to execute tests in Selenium Grid using Docker.
- Ability to configure and schedule jenkins job to build triggers automatically

# Setup Instructions
- Clone the Repository
```
$ git clone https://github.com/DTXin/selenium4_testng_java.git
$ cd selenium4_testng_java    
```
- Install Dependencies using Maven
```
$ mvn clean install
```
# Running Tests on Local
1. [Web UI] Run via TestNG XML
```
$ mvn test -DsuiteXmlFile="test-suite\saucedemo\testng.xml" 
```
2. [API] Run via TestNG XML
```
$ mvn test -DsuiteXmlFile="test-suite\api\testng.xml" 
```

# Running Tests on Selenium Grid via Docker
- Start the Selenium Grid in local using the docker-compose-v3-seleniumgrid.yml file
- Run the following command:

The First:
```
$ docker build -t my-selenium-tests .
```
The Second:
```
$ docker-compose -f docker-compose-seleniumgrid.yml up --build
```

This will start the selenium grid which can be access using http://localhost:4444

# Generating Allure Reports
1. Generate Allure Reports
```
$ allure generate
```

2. Open Allure Reports
```
$ allure open
```