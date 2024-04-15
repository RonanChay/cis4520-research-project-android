# CIS 4520 Research Project - PHAs on Android

> Refer to [this repository](https://github.com/RonanChay/cis4520-research-project) for the PC implementation

This project is the code implementation on Android for my CIS*4520 research project for
Winter 2024. I implement the SHA-512, BCrypt, and Argon2id algorithms using
Java Security package and the BouncyCastle cryptography library to analyze the
relationship between the parameter inputs of a PHA on the cost to hash a password, and the
impact of hardware on the cost.

This is an Android application built using Android Studio. Running the application will
require Android Studio.

## Dependencies

You will need to have the following installed before running the program:
1. Android Studio Iguana | 2023.2.1 Patch 1
2. Java 8+
3. Gradle v7.5.1+

## Porting to Android with Android Studio

The algorithm implementation and result generation classes from the PC implementation have been copied
to a generic "Basic Activity" project template. A simple UI was built to run the tests on an Android
device. See the written paper for more details.

Below is the general file structure for the project:
```
app/
├─ src/main/
│  ├─ java/com/example/cis4520researchprojectphone/
│  │  ├─ code/
│  │  │  ├─ algorithms/
│  │  │  │  ├─ Algorithm.java
│  │  │  │  ├─ Argon2idAlgo.java
│  │  │  │  ├─ BcryptAlgo.java
│  │  │  │  ├─ SHA512Algo.java
│  │  │  ├─ ResultGenerator.java
│  │  │  ├─ Utils.java
│  │  ├─ ProjectDisplayFragment.java
│  │  ├─ ResearchProject.java
│  ├─ res/
│  │  ├─ layout/
│  │  │  ├─ project_display_fragment.xml
│  │  ├─ values/
│  │  │  ├─ colors.xml
│  │  │  ├─ string.xml
├─ build.gradle
```
`code` contains the implementations and classes needed to run the tests that were copied from the
PC implementation. `ProjectDisplayFragment.java` contains the logic for the `project_display_fragment`
fragment while `ResearchProject.java` contains boilerplate code from the template to create and run 
the application on Android. `project_display_fragment.xml` contains the UI layout and styling for
the fragment, while `colors.xml` and `string.xml` contain the color values and text strings used in
the application.

Note that not all project files have been shown here, only the important ones were highlighted.

### Compiling and Running
You will need Java 8 and Android Studio Iguana to run the application.

If you have an Android device, connect it to your computer and select the device from the device 
drop-down selection in the top right corner. If not, you can also emulate a device on Android Studio.
The minimum Android SDK is 24 but the target SDK is 34

> You will need to enable Developer Settings on your device first to connect it to Android Studio. 
> Follow Step 5 and 6 of this guide for more details: https://developer.android.com/codelabs/build-your-first-android-app#2

Once the device is ready, run the application with the Run App button (the green "play" button in the
top right). You can open the Running Devices tab in the right sidebar to view your device screen. You 
can also open the Device Explorer tab also in the right sidebar to view the files on your device.


