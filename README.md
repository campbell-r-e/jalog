# Jalog - Amateur Radio Logging Software

Jalog is a free, open-source QSO logging application for amateur radio operators. Built with Java and Swing (FlatLaf), it runs on Windows, macOS, and Linux.

## Features

- QSO logging with callsign, frequency, band, mode, RST, QSL, satellite, and contest fields
- ADIF import and export
- Remote callsign lookup via Callook.info
- Customizable table columns
- FlatLaf theme support (Light, Dark, IntelliJ, Darcula)
- Integrated notepad

## Requirements

- Java 21 or later (JDK — [Adoptium/Temurin](https://adoptium.net) recommended)
- Gradle is not required separately — the included wrapper (`./gradlew`) handles everything

## Build

```bash
git clone https://github.com/septantrionalis/jalog.git
cd jalog/jalog
./gradlew jar          # macOS / Linux
gradlew.bat jar        # Windows
```

The JAR is produced at `build/libs/jalog-1.07.jar`, with runtime dependencies copied to `build/libs/lib/`.

> **Note:** Gradle requires a JDK 17+ daemon to drive the build. The Java toolchain is pinned to JDK 21 (downloaded automatically via the Foojay resolver on first build). You can override the daemon JVM with `JAVA_HOME=/path/to/jdk17 ./gradlew jar`.

## Run

```bash
cd build/libs
java -jar jalog-1.07.jar
```

The app reads and writes a `jalog.ini` file in the working directory.

## Test

```bash
./gradlew test jacocoTestReport
```

Reports are generated at `build/reports/tests/test/index.html` (test results) and `build/reports/jacoco/test/html/index.html` (coverage).

The test suite uses JUnit 5 with Mockito and runs headlessly (`-Djava.awt.headless=true`). GUI, debug, and network-dependent classes are excluded from the JaCoCo coverage denominator.

## Project Structure

```
jalog/                   Gradle project root
  src/
    main/java/
      jalog/             Application entry point (Genesis.java)
      jalog/jlog/        Public interfaces and abstract classes
      jalog/jlogimpl/    Concrete implementations
      jalog/factory/     Factory classes
      jalog/debug/       Debug/monitoring framework
      jalog/util/        Shared utilities
      jalog/tester/      Test data helpers
    main/resources/      jalog.ini default config
    test/java/           JUnit 5 test suite (383+ tests)
  build/libs/            Compiled JAR + runtime deps (after build)
  docs/                  Developer documentation
```

## License

GNU General Public License v2.0 (GPL-2.0)

Original author: minex123 (2013)  
Contributor: Ron Kinney / KC0ZPS (2019)  
Updated and maintained by: Campbell Reed (2025)
