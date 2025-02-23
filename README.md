Openlogbook - Amateur Radio Logging Software
About Openlogbook

Openlogbook is a free and open-source logging application designed for amateur radio operators to efficiently track and manage QSO records. It offers a user-friendly interface and a host of useful features to organize log entries effectively.

This project is based on JaLog, originally developed by minex123 in 2013 with later contributions from Ron Kinney (GitHub: septantrionalis). Openlogbook has been rebranded and enhanced by Campbell Reed (2025) to provide improved functionality and maintainability.
Features

    Intuitive QSO Logging: Easily record and manage your contacts.
    Customizable Log Entries: Tailor your logging experience to your preferences.
    Cross-Platform Support: Compatible with Windows, macOS, and Linux.
    Streamlined Interface: Focus on logging without unnecessary clutter.
    Open-Source & Free: Licensed under the GNU General Public License v2.0 (GPL-2.0).

Installation & Usage

Clone the repository:

git clone https://github.com/yourusername/openlogbook.git
cd openlogbook

Build the project using Apache Ant:

ant clean
ant

Run Openlogbook:

java -jar dist/openlogbook.jar

License & Credits

    Original Author: minex123 (2013)
    Contributor: Ron Kinney (GitHub: septantrionalis) – 2019
    Updated & Maintained by: Campbell Reed – 2025

Openlogbook is licensed under the GNU General Public License v2.0 (GPL-2.0), ensuring it remains free and open for all users.
Contribute & Support

Contributions are welcome! If you have feature requests, ideas, or improvements, please visit the GitHub repository and submit a pull request or open an issue.
Modification Notice

This software is a modified version of JaLog (originally by minex123 under GPL-2.0) and has been rebranded as Openlogbook.

Modified and released by: Campbell Reed
Date of Modification: 2025-02-20

Summary of Changes:

    Project Structure:
        Reorganized source files into a dedicated src/ directory for better clarity and maintainability.
        Only Java source code is now housed within src/.

    Build Configuration:
        Updated the Apache Ant build file (build.xml) to compile source files into a separate build/ directory.
        All generated JAR files are now stored in a dedicated dist/ folder for improved organization.

    Code Fixes:
        Corrected a typo in the FM mode definition within Mode.java, ensuring proper functionality.

    API Update:
        Updated the API URL for call sign lookup, resolving previous issues.

Additional Notes:

    Dependencies are manually managed and are not automatically resolved by Ivy or any other dependency managers.

Release Notes

Release Date: 2025-02-20
Released by: Campbell Reed

Summary of Changes:

    Code Reorganization:
        Java source code has been reformatted and organized into structured folders under the src/ directory to enhance readability and maintainability.

    API and Functionality Fixes:
        Fixed the API call sign lookup by updating the API URL.
        Resolved a typo in the FM mode definition to ensure correct operation.

    Build Process Improvements:
        Updated the Ant build configuration for a cleaner and more efficient build process.

Original licensing under the GNU General Public License v2.0 (GPL-2.0) remains unchanged.

