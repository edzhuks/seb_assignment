# Currency Exchange application for SEB Technical assignment.

Backend made with Java 17 and maven (Spring Boot, H2 database).

Frontend made with React (Vite) with Chart.js for the currency history chart.

Project is structured as follows:
*    /webapp contains the React application. The built static version is already located in /src/main/resources/ - no building necessary.
*    /src/main/java/com/edgars/currency_calculator/util/ contains utility classes for converting from the lb.lt XML schema to Java objects.
*    /src/main/java/com/edgars/currency_calculator/ contains the core application. Rate.java is the base class containting an exchange rate at a specific date. This object is created from the utility classes and saved in the database.
    
