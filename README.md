Openlogbook - Amateur Radio Logging Software
About Openlogbook

Openlogbook is a free and open-source logging application designed for amateur radio operators to efficiently track and manage QSO records. It provides a user-friendly interface and a variety of features to organize log entries effectively.

This project is based on JaLog, originally developed by minex123 in 2013, with later contributions from Ron Kinney (GitHub: septantrionalis). In 2025, Openlogbook was rebranded and enhanced by Campbell Reed to improve functionality and maintainability. The branding within the project will be updated soon.
Features

    Intuitive QSO Logging – Easily record and manage your contacts.
    Customizable Log Entries – Tailor your logging experience to fit your needs.
    Cross-Platform Support – Compatible with Windows, macOS, and Linux.
    Open-Source & Free – Licensed under the GNU General Public License v2.0 (GPL-2.0).

Installation & Usage
1. Clone the Repository

git clone https://github.com/campbell-r-e/Openlogbook.git
cd openlogbook

2. Build the Project Using Gradle

Ensure you have Gradle installed or use the provided Gradle Wrapper.
Using the Gradle Wrapper (Recommended)

./gradlew clean build      # On macOS/Linux
gradlew.bat clean build    # On Windows

Using a System-wide Gradle Installation

gradle clean build

3. Run Openlogbook

java -jar openlogbook/openlogbook/release/openlogbook.jar

License & Credits

Original Author: minex123 (2013)
Contributor: Ron Kinney (GitHub: septantrionalis) – 2019
Updated & Maintained by: Campbell Reed – 2025

Openlogbook is licensed under the GNU General Public License v2.0 (GPL-2.0), ensuring it remains free and open for all users.
JaLog Legacy Information
License

Copyright 2007 rkinney
Version

Since jlog 1.0
Contribute & Support

Contributions are welcome! If you have feature requests, ideas, or improvements, please visit the GitHub repository and submit a pull request or open an issue.
Modification Notice

This software is a modified version of JaLog (originally by minex123 under GPL-2.0) and has been rebranded as Openlogbook.

Modified and released by: Campbell Reed
Date of Modification: 2025-02-20
Summary of Changes:
Project Structure

    Reorganized source files into a dedicated src/ directory for better clarity and maintainability.
    Only Java source code is now housed within src/.

Build Configuration

    Gradle replaces Apache Ant as the build tool.
    The Gradle build process now generates JAR files inside openlogbook/openlogbook/release/.

Code Fixes

    Corrected a typo in the FM mode definition within Mode.java, ensuring proper functionality.

API Update

    Updated the API URL for call sign lookup, resolving previous issues.

Release Notes
Release Date: 2025-02-20

Released by: Campbell Reed
Summary of Changes:
Code Reorganization

    Java source code has been reformatted and structured under the src/ directory to enhance readability and maintainability.

API and Functionality Fixes

    Fixed the API call sign lookup by updating the API URL.
    Resolved a typo in the FM mode definition to ensure correct operation.

Build Process Improvements

    Switched from Apache Ant to Gradle for a cleaner and more efficient build process.
    The original licensing under the GNU General Public License v2.0 (GPL-2.0) remains unchanged.