# Movie Analysis System

## Project Contributors
- Felipe Arias Russi (201914996)
- Luisa Fernanda Estrada (201631037)
- Mario Alejandro Ruiz (201920695)

## Project Overview
This system is designed to analyze extensive movie data, handling tasks such as loading, processing, and querying movie details and casting information. It uses Java for back-end data processing and a clear MVC architecture for organization.

## Installation
1. Clone the repository to your local machine.
2. Download the following data files into the `data/` directory:
   - [AllMoviesCastingRaw.csv](https://drive.google.com/file/d/1NrvPGPDXrhIoxT3oOtKMNfscfNapz4cC/view?usp=sharing)
   - [AllMoviesDetailsCleaned.csv](https://drive.google.com/file/d/18Sf0Sxh2MlTK1z9s7qvi48OuqKJwdGvL/view?usp=sharing)
3. Include the `commons-lang3-3.11.jar` and `opencsv-5.2.jar` libraries in your project's classpath.
4. Compile the source files found in the `src/` directory using your preferred Java compiler or IDE.

## How to Run
Execute `Main.java` located in the `src/main/` directory to start the application.

## Features
- Load data from CSV files for casting and movie details.
- Perform data processing to analyze and filter movie information.
- Utilize custom data structures for efficient data management.

## Directory Structure
- `data/`: CSV files with raw and cleaned data.
- `docs/`: Documentation files and images showing data structures.
- `lib/`: Required libraries.
- `src/`: Source code.
  - `controller/`: Application logic.
  - `main/`: Entry point of the system.
  - `model/`: Data handling and structures.
  - `view/`: User interface components.

## Dependencies
- Apache Commons Lang 3.11
- OpenCSV 5.2

## Note
The current version requires CSV files to be placed in the `data/` folder for the application to function correctly.

## License
The project is currently not under any license. Please include an appropriate license file before distributing or contributing to this project.

---

Por favor, reemplaza cualquier sección con información específica del proyecto si es necesario. Si el proyecto utiliza una licencia específica, inclúyela en la sección correspondiente del README.
