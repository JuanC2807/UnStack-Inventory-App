# UnStack

UnStack is an Android inventory-management application developed collaboratively for a mobile application development course. The app helps users view inventory, update stock, manage restocking workflows, and locate suppliers.

## Features

- User registration and login with Firebase Authentication
- Inventory viewing and stock management
- Item editing and stock updates
- Restock request workflow
- Inventory restocking
- Supplier location viewing with Google Maps
- Firebase Data Connect backend
- Role-based application functionality

## Technologies

- Java
- Android SDK
- Firebase Authentication
- Firebase Data Connect
- PostgreSQL
- Google Maps SDK
- Gradle

## Project Structure

- `app/src/main/java/cs477/gmu/unstack/` — Android application source
- `app/src/main/res/` — layouts and Android resources
- `dataconnect/schema/` — Firebase Data Connect GraphQL schema
- `dataconnect/example/` — Data Connect queries and mutations
- `gradle/` — Gradle configuration

## Setup

This repository does not include private/local Firebase and Google Maps configuration.

To run the application:

1. Open the project in Android Studio.
2. Configure the appropriate Firebase project.
3. Place your Firebase Android configuration at:

       app/google-services.json

4. Add your Google Maps API key to `local.properties`:

       MAPS_API_KEY=your_api_key_here

5. Sync the Gradle project.
6. Build and run the application on an emulator or Android device.

## Contributors

- Juan Carlos Garcia Solis
- Ayden Shimek

This project was completed collaboratively as part of a university mobile application development course.
