JaLog - Amateur Radio Logging Software
About JaLog

JaLog is a free and open-source logging application designed for amateur radio operators to efficiently track and manage QSO records. It provides a straightforward, user-friendly interface while offering useful features for organizing log entries.

This project was originally developed by minex123 in 2013, with later contributions from Ron Kinney (GitHub: septantrionalis). It is now maintained to ensure continued usability and community contributions.
Features

    Easy-to-use logging system for QSOs
    Customizable log entries
    Support for multiple operating systems (Windows, macOS, Linux)
    Simple and efficient interface
    Free and open-source under the GPL v2 license

Installation & Usage

    Clone the repository:

git clone https://github.com/yourusername/jalog.git
cd jalog

Build the project using Apache Ant:

ant clean
ant

Run JaLog:

    java -jar dist/jalog.jar

License & Credits

    Original Author: minex123 (2013)
    Contributor: Ron Kinney (GitHub: septantrionalis) - 2019
    Updated & Maintained by: Campbell Reed - 2025

JaLog is licensed under the GNU General Public License v2.0 (GPL-2.0), ensuring it remains free and open for all users.
Contribute & Support

Contributions are welcome. If you have ideas, feature requests, or would like to contribute, visit the GitHub repository.













## Modification Notice 

This software is a modified version of JaLog (originally by minex123 under GPL-2.0).  
Modified and released  by Campbell Reed on 2025-02-20.

**Summary of changes:**
- Fixed a typo issue in FM mode definition.
- Reorganized project structure.
- Updated Ant build configuration.


Modification Disclosure

Date of Modification: 2025-02-20
Modified by: Campbell Reed

Summary of Changes:

    Project Structure: Reorganized source files under a dedicated src/ directory for better clarity and maintainability. Only Java source code remains within src/.
    Build Configuration (build.xml): Modified Ant build file to compile source files into a separate build/ directory and store all generated JAR files into a dedicated dist/ folder for improved organization.
    Code Fix: Corrected a typo in the Mode.java file related to the FM mode definition, ensuring proper functionality.

Additional Notes:

    Dependencies are manually managed and not automatically resolved by Ivy or other dependency managers.

These changes were made to improve the build process, maintainability, and accuracy of the project. The original licensing terms under the GNU General Public License v2.0 (GPL-2.0) remain in effect.



Release Notes

Release Date: 2025-02-20Released by: Campbell Reed

Summary of Changes:

Code Reorganization:

Java source code has been reformatted and reorganized into dedicated, structured folders under the src/ directory. This change enhances readability, maintainability, and project clarity.

Notes:

No changes in functionality or dependencies.

Original licensing under GNU General Public License v2.0 (GPL-2.0) remains unchanged.

