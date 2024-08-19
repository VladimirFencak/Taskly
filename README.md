# Task Management App

## Overview
This is an Android application in development, using Kotlin, Jetpack Compose for UI, and Hilt for dependency injection. The application follows the MVVM architecture pattern and Clean Architecture principles to ensure scalability and maintainability. The app is designed for task management using an online API.

## Features
- **Splash Screen**: Checks if the user is logged in by validating the JWT token with the server. If valid, navigates to the main screen; if not, navigates to the login screen.
- **Login Screen**: Allows users to log in using their credentials. It handles errors such as incorrect credentials and network issues.
- **Register Screen**: Allows users to create a new account. Similar error handling as the login screen.

## Features in Development (Future Plan)
- **Calendar**: Calendar with users' tasks.
- **Tasks**: Tasks that users can edit and add notifications for.
- **Events**: Events for multiple users.

## Screenshots
<div style="display: flex;">
    <img src="https://github.com/VladimirFencak/Taskly/assets/17989784/dfc791d7-741a-4523-ba8c-ff081123e7c6" alt="Splash Screen" width="200" style="margin-right: 10px;">
    <img src="https://github.com/VladimirFencak/Taskly/assets/17989784/8114951f-6e46-4453-ae4b-041e5c061744" alt="Login Screen" width="200" style="margin-right: 10px;">
    <img src="https://github.com/VladimirFencak/Taskly/assets/17989784/8823997c-3a7c-4700-8e32-738eae5dc261" alt="Register Screen" width="200">
</div>

## Technologies Used
- **Kotlin**: Programming language for Android development.
- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **Hilt**: Dependency injection library for Android.
- **MVVM**: Architecture pattern to separate the UI, business logic, and data.
- **Clean Architecture**: Not 100%, as it is overkill for an app of this size. I decided not to use UseCases but follow other principles. Logic from VM can always be moved to UseCases if needed.
- **Ktor**: Framework for making HTTP requests to the API.
- **Room**: For local database.
- **JWT**: JSON Web Tokens for secure authentication.
- **EncryptedSharedPreferences**: Secure storage for JWT tokens.
- **NavHostController**: For navigation between screens with a nested navigation graph.

## Project Structure
- **CORE**: Contains core functionalities and common utilities.
- **AUTH**: Manages authentication-related features such as login and registration.
- **Agenda**: Feature for calendar, and tasks/events in the calendar.

## Project Structure in Development (Future Plan)
- **User**: Feature package for user profile.

## API Integration
The application uses an external API for task management. While the API is not developed by me, it is integrated using Ktor for handling HTTP requests. JWT tokens are used for secure authentication and are stored using EncryptedSharedPreferences.
