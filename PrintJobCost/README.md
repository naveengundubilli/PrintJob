# PrintJobCost

## Overview
PrintJobCost is a Java application designed to calculate the cost of print jobs based on input parameters provided in a CSV file. It supports different paper sizes (A4, A5) and handles both color and black-and-white printing, as well as single- and double-sided options.

## Features
- Reads print job parameters from a CSV file
- Supports A4 and A5 paper sizes (easily extensible)
- Calculates costs for color and black-and-white pages
- Handles single- and double-sided printing
- Provides detailed error handling and input validation
- Outputs results in a formatted table

## Project Structure
```
PrintJobCost/
  ├── pom.xml                  # Maven build configuration
  ├── src/
  │   ├── main/
  │   │   └── java/
  │   │       └── ...         # Application source code
  │   └── test/
  │       └── java/
  │           └── ...         # Unit tests
  └── README.md               # Project documentation
```

## Build Instructions
This project uses Maven for build and dependency management. To build the project, run:

```sh
mvn clean package
```

## Running the Application
To run the application, use the following command:

```sh
java -cp target/PrintJobCost-1.0.jar com.print.input.reader.CsvFileReader <inputFile> <printSize>
```
- `<inputFile>`: Path to the CSV file containing print job parameters
- `<printSize>`: Paper size (e.g., `A4`, `A5`)

### Example CSV File Format
Each line should contain:
```
totalPages,colorPages,doubleSided
```
Example:
```
10,5,true
20,10,false
15,0,true
```

## Testing
To run the unit tests:

```sh
mvn test
```

## Extending the Application
To add support for a new paper size:
1. Implement a new class in `com.print.size` that implements the `PrintSize` interface.
2. Register the new size in `SizeFactory.java`.

## License
This project is provided for educational and demonstration purposes. 