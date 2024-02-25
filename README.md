## To setup environment
I have used various dependency such as:

Selenium-java :To use all selenium class and method

log4j:To know,how the process is going.All the log is stored in log directory

testng:To make test method as Test using testng framework

webdrivermanager: To setup various browser driver dynamically

javafaker: To genrate fake data,for testing

allure-testng: To use various annotation of allure in testng framework

## To generate Allure report in HTML format,we have to do following setp:
In any Operating System:Set Allure Path in your system

For Setting Allure Path we can refer following offical Website:https://allurereport.org/docs/gettingstarted-installation/

After installation we can generate by following command in terminal:

## To View the test Report,run command:
## allure serve <allure-results-path>

## Setup Project in Your ide by following step:
1.git clone https://github.com/kaflin/ShopIstEcommerce.git

2.After cloning project,open the project in Your Local System ide(i.e I am using Intellij idea for my project)

3.Navigate resources >> TestNG.xml(run TestNG.xml ) or with maven command you can use:mvn test

## Project Structure:

Page Object Model:

For Doing Action on page

The screenshot of allure Report has been added to Allure Report ScreenShot Folder

Test:

Writing Test,For what to do in Page

Screen shot of allure report has been added to Allure Report Screenshot folder

