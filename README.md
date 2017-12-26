# DemoForReview
TThis project is written in Java and will serve an example of implementing a Selenium test project with Selenium, the test run on a [demo site](http://yoniflenner.net/prestashop/index.php). 
The site is a demo of an e-commerce site, and the project cover several flows om the site.
I used in the project the Page Object Pattern using PageFactory, Framework contain three major sections:
* pageObjects
* projectUtilities 
* Project_TestCases

## The project included:
* Common function class
* Page Object Pattern using PageFactory
* Commonly used test utility classes
* Reports  using [extent reports](http://extentreports.com/community/#Version_2) 
* logs file using [Log4j](https://logging.apache.org/log4j/2.x/).
* Sikuli (comparing images engine) [Sikuli](http://sikulix.com/) get version 1.1.1 , In order to work with Sikuli Webdriver it needed to download Sikuli file + add sikulixapi.jar to project build path. 
* DDT - test input and output values are read from data files in this framework I used Excel file in two test cases.

## Drivers
There is a folder with 3 drivers:
* Chromedriver 
* Firefox 
* IEDriver
    
```
```






