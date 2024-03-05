# SafetyApp
Safety Tool for FMEDA

# SafetyTool FMEDA Application

## Overview

SafetyTool FMEDA is a JavaFX application for performing Failure Modes, Effects, and Diagnostic Analysis (FMEDA) in the field of safety engineering.

## Features

- Dynamic table view for displaying block data.
- Export data to Excel functionality.
- Multiple screens and navigation.

## Getting Started

### Prerequisites

- Java 17 or higher.
- [JavaFX SDK](https://openjfx.io/) for JavaFX development.

### Build and Run

1. Clone the repository:

```bash
git clone https://VishwajeetVT@github.com/VishwajeetVT/SafetyApp.git
```
-Open the project in your preferred IDE.
-Run the SafetyApp class located in the com.safetytool.safetytool_fmeda package.

## MVC Pattern

This application follows the Model-View-Controller (MVC) pattern, a widely used architectural design pattern in software development. Here's a brief overview of each component:

### Model

- The **Model** represents the application's data and business logic.
- Classes such as `BlockDataModel` reside in this layer, handling data-related operations.

### View

- The **View** is responsible for presenting the user interface.
- FXML files (`block-view.fxml`) and corresponding controllers (`BlockViewController`) are part of the View layer.

### Controller

- The **Controller** acts as an intermediary between the Model and View.
- `AppController` and `BlockViewController` are examples of controllers handling application logic and interactions.

