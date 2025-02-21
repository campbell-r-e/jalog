JaLog - Amateur Radio Logging Software
Original Author:

- minex123 (2008) Ron Kinney (GitHub: septantrionalis)

Contributors:
- Campbell Reed - 2025
About JaLog


JaLog is a free and open-source logging application designed for amateur radio operators to efficiently track and manage QSO records. It provides a straightforward, user-friendly interface while offering useful features for organizing log entries.

This project was originally developed and released by Ron Kinney (GitHub: septantrionalis) in 2008. The project is now maintained by Campbell Reed to ensure continued usability and community contributions.
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

java -jar jalog/release/jalog.jar

License & Credits

    Original Author: Ron Kinney (GitHub: septantrionalis) - 2013
        (Initially based on work by minex123)
    Updated & Maintained by: Campbell Reed - 2025

JaLog is licensed under the GNU General Public License v2.0 (GPL-2.0), ensuring it remains free and open for all users.
Contribute & Support

Contributions are welcome! If you have ideas, feature requests, or would like to contribute, visit the GitHub repository.
Modification Notice (Version 1.06.1)

This software is a modified version of JaLog originally developed and uploaded to GitHub by Ron Kinney (septantrionalis) under GPL-2.0.
Modified and released as version 1.06.1 by Campbell Reed on 2025-02-20.
Summary of Changes:

    FM Mode Fix: Corrected a typo in the Mode.java file related to FM mode definition.
    Project Structure: Reorganized source files into a dedicated src/ directory for clarity and maintainability.
    Build Configuration (build.xml): Updated Ant build configuration to compile source files into a build/ directory and store generated JAR files in jalog/release/.

Additional Notes:

    Dependencies are manually managed.

These changes were made to improve build processes, maintainability, and accuracy. The original licensing under the GNU GPL v2.0 remains in effect.
Release Notes (Version 1.0.6.2)

Release Date: 2025-02-20
Released by: Campbell Reed
Summary of Changes:

    Code Reorganization:
        Java source code has been reformatted and reorganized into structured folders under src/ for enhanced readability and project clarity.
    Notes:
        No changes in functionality or dependencies.
        Original licensing under GNU General Public License v2.0 (GPL-2.0) remains unchanged.

Release Notes (Version 1.0.6.3)

Recommended Version Update: Following previous numbering, next version is 1.0.6.3.

Release Date: 2025-02-21
Released by: Campbell Reed
Summary of Changes:
📡 Callook Lookup URL Update

    Issue Fixed: Callsign lookups failing due to outdated Callook API URL.
    Change: Updated API URL from:

http://callook.info/index.php?callsign=[callsign]&display=xml

to the new format:

    https://callook.info/[callsign]/xml

    Impact: Callsign lookups are now functional and accurate.

🛠 Build System Enhancement

    New Feature: Compiler warnings (-Xlint:deprecation, -Xlint:unchecked) now stored in separate logs/ folder.
    Benefit: Provides clearer build outputs and facilitates easier debugging.

📁 Codebase Organization Improvements

    Enhancement: Further refined source code directory structure. Compiled classes now in build/, distributable JAR files in jalog/release/.
    Impact: Improves maintenance and project navigation.

🧹 Minor Fixes

    .DS_Store Removal:
        Removed unwanted .DS_Store files from repository and updated .gitignore.
    Documentation:
        Updated comments within modified files to clearly indicate changes.
