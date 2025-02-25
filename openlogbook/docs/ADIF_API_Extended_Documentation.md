# ADIF API Extended Documentation

**Version: 1.0.0**  
**Author: Campbell Reed**  
**Last Updated: 2025-02-23**  

This document provides an extensive guide to the **ADIF API**, a Java-based library for parsing, reading, writing, and handling **Amateur Data Interchange Format (ADIF)** files.

---

## Table of Contents

1. [Introduction](#introduction)
2. [Understanding ADIF Format](#understanding-adif-format)
3. [Getting Started](#getting-started)
4. [Class Documentation](#class-documentation)
    - [ADIFException](#adifexception)
    - [ADIFField](#adiffield)
    - [ADIFReader](#adifreader)
    - [ADIFRecord](#adifrecord)
    - [ADIFWriter](#adifwriter)
    - [Extending ADIF API](#extending-adif-api)
5. [Advanced Use Cases](#advanced-use-cases)
6. [Error Handling Best Practices](#error-handling-best-practices)
7. [Performance Considerations](#performance-considerations)
8. [Comparison with Other ADIF Libraries](#comparison-with-other-adif-libraries)
9. [Debugging ADIF Issues](#debugging-adif-issues)
10. [Future Enhancements](#future-enhancements)

---

## Introduction

The **ADIF API** provides a structured way to process **QSO records** in amateur radio logging software using Java. It is lightweight, efficient, and fully compliant with the ADIF 3.1.5 specification.  

---

## Understanding ADIF Format

ADIF (Amateur Data Interchange Format) is a standardized text-based format used by ham radio logging software. An ADIF file consists of records that contain various fields such as call signs, frequencies, modes, and timestamps.

### **Example ADIF Record:**
```
<call:6>W1AW <qso_date:8>20240101 <time_on:4>1830 <band:3>20M <mode:3>SSB <eor>
```

Each field is enclosed in `< >`, contains a **field name**, **length**, and **value**.

---

## Getting Started

### **1. Cloning and Building the Project**
```bash
git clone https://github.com/yourusername/adif-api.git
cd adif-api
mvn clean install
```

### **2. Including in a Java Project**
If using Maven, add:
```xml
<dependency>
    <groupId>no.la1k</groupId>
    <artifactId>adif-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### **3. Basic Usage Example**
```java
ADIFReader reader = new ADIFReader("log.adi");
ADIFRecord record = reader.next();
System.out.println("Callsign: " + record.getField("CALL").getValue());
```

---

## Class Documentation

### **ADIFException**

**Description:**  
Handles ADIF-related errors.

**Constructors:**  
```java
public ADIFException(String message)
public ADIFException(String message, Throwable throwable)
```

**Example:**  
```java
try {
    throw new ADIFException("Invalid ADIF format");
} catch (ADIFException e) {
    System.err.println(e.getMessage());
}
```

---

### **ADIFField**

**Description:**  
Represents an individual field in an ADIF record.

**Constructors:**  
```java
public ADIFField(String name, String type, String value)
```

**Example:**  
```java
ADIFField callField = ADIFField.create("CALL", "W1AW");
System.out.println(callField.getName() + ": " + callField.getValue());
```

---

### **ADIFReader**

**Description:**  
Reads ADIF data from files.

**Methods:**  
```java
public ADIFRecord next() throws ADIFException
```

**Example:**  
```java
ADIFReader reader = new ADIFReader("log.adi");
ADIFRecord record = reader.next();
System.out.println("Frequency: " + record.getField("FREQ").getValue());
```

---

### **ADIFRecord**

**Description:**  
Represents a full ADIF log entry.

**Methods:**  
```java
public void add(ADIFField field)
public void write(Writer out) throws IOException
```

**Example:**  
```java
ADIFRecord record = new ADIFRecord();
record.add(ADIFField.create("CALL", "N0CALL"));
record.add(ADIFField.create("BAND", "20M"));
record.write(new FileWriter("output.adi"));
```

---

### **ADIFWriter**

**Description:**  
Writes ADIF records to a file.

**Example:**  
```java
ADIFWriter writer = new ADIFWriter(new FileWriter("log.adi"), "ADIF Log");
ADIFRecord record = new ADIFRecord();
record.add(ADIFField.create("MODE", "FT8"));
writer.write(record);
```

---

## Advanced Use Cases

### **1. Using ADIF for Contest Logging**
- Automatically process and validate log entries.
- Generate reports for contest submissions.

### **2. DX Logging & Award Tracking**
- Track QSO confirmations using `QSL_SENT` and `QSL_RCVD` fields.

---

## Error Handling Best Practices

- Always catch `ADIFException` when parsing.
- Validate field lengths before processing.

**Example:**
```java
try {
    ADIFRecord record = reader.next();
} catch (ADIFException e) {
    System.err.println("Error processing ADIF file: " + e.getMessage());
}
```

---

## Performance Considerations

- Use buffered streams when reading large ADIF files.
- Avoid excessive memory allocation by reusing objects.

---

## Comparison with Other ADIF Libraries

| Feature        | ADIF API | Other Libraries |
|---------------|---------|----------------|
| Java-based    | ✅       | Some           |
| Lightweight   | ✅       | ❌ (some require dependencies) |
| Performance   | ✅       | Varies         |

---

## Debugging ADIF Issues

### **Common Issues**
- Incorrect field length (`<call:10>W1AW` when `W1AW` is only 6 characters).
- Missing `<eor>` markers.

### **Debugging Strategy**
- Print parsed ADIF records:
```java
System.out.println(record.getField("CALL").getValue());
```

- Validate field types:
```java
if (!record.getField("MODE").getValue().matches("SSB|CW|FT8")) {
    System.err.println("Invalid mode detected.");
}
```

---

## Future Enhancements

- **Support for ADIF 4.0 (when released)**
- **Integration with cloud-based logging services**
- **Support for multi-threaded ADIF processing**

---

## Conclusion

The **ADIF API** simplifies working with ADIF data in Java. It is designed to be **lightweight, efficient, and extensible** for amateur radio applications. Future updates will enhance support for **automated logging, QSL tracking, and real-time contest logging.**

For feedback and contributions, refer to the **GitHub repository.**

---

*This document is intended for amateur radio operators and developers integrating ADIF support into their applications.*
