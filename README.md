# Starter kit for pact-java

A starter kit for consumer driven contract test using pact-jvm.

## Table of Contents

- [Project Description](#project-description)
- [Business Flow](#business-flow)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Project Description

This is a sample project that demonstrates the usage of Pact JVM for consumer-driven contract testing in a Java application. It showcases how to create pact tests, generate contract files, and verify them against a provider.

The project is built using Java and the following technologies:

- [Pact JVM](https://github.com/pact-foundation/pact-jvm): A JVM implementation of the Pact specification for contract testing.
- [JUnit](https://junit.org/): A unit testing framework for Java.
- [Maven](https://maven.apache.org/): A build automation tool for Java projects.

## Business Flow

This is a credit card delivering application where we have a consumer(**creditCard**) and a provider(**creditCheck**) build using [SpringBoot](https://spring.io/projects/spring-boot) framework. 


For demonstrating contract test we are using a basic application where **creditCheck** service will return credit score as HIGH when it receives **citizenNumber** as 1 from **creditCard** service and returns credit score as LOW when it receives **citizenNumber** as 2.

According to response **creditCard** service will return credit card **GRANTED** or **DENIED** to user.


## Prerequisites

Before getting started, ensure that the following software is installed on your system:

- Java Development Kit (JDK)
- Apache Maven

## Installation

To set up the project, follow these steps:

1. Clone the repository:

   ```shell
   git clone https://github.com/twCatalyst/pact-java-starter.git
   ```

2. Navigate to the project directory:

   ```shell
   cd pact-jvm-sample-project
   ```

3. Build the project using Maven:

   ```shell
   mvn clean install
   ```

This will download the required dependencies and compile the project.

## Usage

To run the pact tests and generate contract files, execute the following command:

```shell
mvn pact:publish
```

This command will execute the pact tests defined in the project and generate the corresponding contract files in the target directory.

To verify the contracts against a provider, you can use the Pact verification tasks provided by Pact JVM. Modify the verification configuration in the `pom.xml` file according to your provider and then execute the following command:

```shell
mvn pact:verify
```

This command will verify the generated contracts against the specified provider.

## Contributing

Contributions to this sample project are welcome! If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request to the original repository.

Please ensure that your code adheres to the project's coding standards and includes appropriate tests.



That's it! You can update the template with the relevant information for your Pact JVM sample project using Maven. Happy testing!
