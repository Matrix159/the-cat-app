# The Cat App

The Cat App is an application to let users see information about Cats.

Data is provided by: [The Cat API](https://thecatapi.com/)

## 📋 Features

- View and search through a list of cat breeds.
- View details of a cat breed, including a description and statistics like their child friendliness, dog friendliness, and energy levels.

## Modules

The repository is organized into the following modules:

- `build-logic`: Contains the shared build logic for the project.
- `app`: Contains the main application code and entry points.
- `core:data`: Provides data-related functionality, including data sources and repository implementations.
- `core:domain`: Contains the domain layer, including business logic models and repository interfaces.
- `core:ui`: Contains shared UI elements across the application, such as theme and shared composables.
- `feature:catbreeds`: Contains the feature module for displaying cat breeds.

## 🔨 Technologies Used

The repository utilizes the following dependencies:

- Jetpack Compose: For views.
- Compose Navigation: For handling screen navigation within the app.
- Ktor Client: For making network requests to the Cat API.
- Hilt: For dependency injection.
- Kotlin Coroutines: For managing asynchronous operations.
- Coil: For asynchronous loading and displaying of images.
- Jetpack ViewModels: For managing screen states.
- Timber: For logging.

## Setup & Running

To run the application, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio. (Android Studio Iguana 2023.2.1 Patch 1 is recommended)
3. Setup a `secrets.properties` file in the root of the project with the following content, replace `api_key_here` with an API key from https://thecatapi.com/:
```properties
CATS_API_KEY="api_key_here"
```
4. Build and run the project on an Android emulator or device.
