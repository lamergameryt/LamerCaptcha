<h1 style="text-align: center;">Welcome to LamerCaptcha 👋</h1>
<p>
  <a href="https://jitpack.io/#lamergameryt/LamerCaptcha">
    <img alt="JitPack Version" src="https://img.shields.io/jitpack/v/github/lamergameryt/LamerCaptcha?style=flat-square" />
  </a>
  <img alt="GitHub Issues" src="https://img.shields.io/github/issues/lamergameryt/LamerCaptcha?style=flat-square" />
  <a href="https://www.apache.org/licenses/LICENSE-2.0.txt" target="_blank">
    <img alt="License: The Apache License, Version 2.0" src="https://img.shields.io/badge/License-The Apache License, Version 2.0-yellow.svg?style=flat-square" />
  </a>
</p>

> Simple Image/Audio captcha generation library for Java.

### ✨ [Captcha Outputs](https://github.com/lamergameryt/LamerCaptcha/examples/)

Example:
<img alt="Captcha Output" src="./examples/captcha_cp2b3f.png" />

For an audio output, visit: [Audio Output](https://github.com/lamergameryt/LamerCaptcha/examples/)

## 📖 Usage

Image Based Captcha:

```java
Captcha captcha = new Captcha.Builder(150, 50)  // 150x50 image dimensions.
        .addBackground()  // Default: Pink Gradient
        .addBorder()  // Add a black border.
        .addText()  // Generate a captcha of five characters.
        .addNoise()  // Create a curved line noise.
        .build();
```

Audio Based Captcha:

```java
AudioCaptcha captcha = new AudioCaptcha.Builder()
        .addNoise()  // Add background noise.
        .addAnswer()  // Add randomized captcha audio.
        .addVoice()  // Add voices used for speaking out the captcha.
        .build();
```

## 📩 Installation

You will need to add this project as a dependency (via Maven or Gradle). LamerCaptcha requires Java 8 and above.

With Maven:

```xml
<dependency>
    <groupId>com.github.lamergameryt</groupId>
    <artifactId>LamerCaptcha</artifactId>
    <version>JITPACK-VERSION</version>
</dependency>
```

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

With Gradle:

```groovy
dependencies {
    implementation 'com.github.lamergameryt:LamerCaptcha:JITPACK-VERSION'
}
repositories {
    maven { url 'https://jitpack.io' }
}
```

## 💖 Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2021 [Harsh Patil](https://github.com/lamergameryt). \
This project licensed under [The Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).

***
