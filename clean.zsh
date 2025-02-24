#!/bin/zsh

# Define project root
PROJECT_ROOT="./jalog"

echo "Starting project cleanup and restructuring..."

# Ensure PROJECT_ROOT exists
if [ ! -d "$PROJECT_ROOT" ]; then
    echo "Error: $PROJECT_ROOT does not exist!"
    exit 1
fi

# 1. Remove macOS metadata files
echo "Removing macOS metadata files..."
find "$PROJECT_ROOT" -name ".DS_Store" -type f -delete
find "$PROJECT_ROOT" -name "__MACOSX" -type d -exec rm -rf {} +

# 2. Replace package.html with package-info.java
echo "Replacing package.html files with package-info.java..."

find "$PROJECT_ROOT" -name "package.html" | while read -r PACKAGE_HTML; do
    PACKAGE_DIR=$(dirname "$PACKAGE_HTML")
    PACKAGE_INFO="$PACKAGE_DIR/package-info.java"
    
    # Extract package description from package.html
    DESCRIPTION=$(grep -oP '(?<=<body>).*?(?=</body>)' "$PACKAGE_HTML" | sed -e 's/<[^>]*>//g' -e 's/&nbsp;/ /g' | tr -d '\n' | sed 's/  */ /g')

    # Extract package name from directory structure
    PACKAGE_NAME=$(echo "$PACKAGE_DIR" | sed "s|$PROJECT_ROOT/src/||" | tr '/' '.')

    # Create package-info.java
    echo "/**" > "$PACKAGE_INFO"
    echo " * $DESCRIPTION" >> "$PACKAGE_INFO"
    echo " *" >> "$PACKAGE_INFO"
    echo " * @since jlog 1.0" >> "$PACKAGE_INFO"
    echo " */" >> "$PACKAGE_INFO"
    echo "package $PACKAGE_NAME;" >> "$PACKAGE_INFO"

    # Remove old package.html
    rm -f "$PACKAGE_HTML"

    echo "Converted: $PACKAGE_HTML -> $PACKAGE_INFO"
done

# 3. Move all .class files to the build/ directory
BUILD_DIR="$PROJECT_ROOT/build"
mkdir -p "$BUILD_DIR"

echo "Moving compiled .class files to build/ directory..."
find "$PROJECT_ROOT/src" -name "*.class" -type f -exec mv {} "$BUILD_DIR" \;

# 4. Ensure required directories exist
echo "Creating necessary directories..."
mkdir -p "$PROJECT_ROOT/dist"
mkdir -p "$PROJECT_ROOT/resources"
mkdir -p "$PROJECT_ROOT/tests"

# 5. Ensure all source files are in src/jalog/ and move misplaced files
echo "Reorganizing source files..."
SRC_DIR="$PROJECT_ROOT/src/jalog"
mkdir -p "$SRC_DIR"

# Find misplaced Java files (not in src/jalog/) and move them
find "$PROJECT_ROOT/src" -type f -name "*.java" ! -path "$SRC_DIR/*" -exec mv {} "$SRC_DIR/" \;

# 6. Verify Gradle build setup
if [ -f "$PROJECT_ROOT/build.gradle" ]; then
    echo "Gradle build file found. You can now run 'gradle clean build'."
else
    echo "Warning: No build.gradle found. Ensure Gradle is set up correctly."
fi

echo "Cleanup and restructuring complete!"
