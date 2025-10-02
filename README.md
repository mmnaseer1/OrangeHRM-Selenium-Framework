# 🚀 Selenium Test Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange)  
![TestNG](https://img.shields.io/badge/TestNG-7.x-brightgreen)  
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)  
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.x-blue)  
![ApachePOI](https://img.shields.io/badge/Apache--POI-Excel-yellow)

---

## 📖 Overview
This is a **Java + Selenium WebDriver + TestNG** automation framework built with industry best practices:

- ✅ **Page Object Model (POM)** for maintainable UI interactions  
- ✅ **Thread-safe WebDriver** with `ThreadLocal` for parallel execution  
- ✅ **Configurable via `qa_config.properties`**  
- ✅ **Data-driven testing** with Apache POI (Excel-based data providers)  
- ✅ **Custom utilities** for waits, screenshots, selects, alerts  
- ✅ **ExtentReports integration** with screenshots and step-by-step storytelling  
- ✅ **Maven build** → ready for CI/CD pipelines

---

## 🗂 Project Structure

```
src
 └── test
     ├── java
     │   ├── BasePackage
     │   │    ├── BaseTest.java
     │   │    └── DriverFactory.java
     │   ├── configReader
     │   │    └── ConfigReader.java
     │   ├── dataProviders
     │   │    └── LoginDataProvider.java
     │   ├── listenersPack
     │   │    └── ExtentReportListener.java
     │   ├── pageObjectsPackage
     │   │    ├── OrangeLoginPageObjects.java
     │   │    └── OrangeDashboardPageObjects.java
     │   ├── testPackage
     │   │    └── LoginRegressionTest.java
     │   └── utilitiesPackage
     │        └── UtilitiesClass.java
     └── resources
         ├── qa_config.properties
         └── testData
              └── OrangeHRM_LoginData.xlsx
```

---

## ⚙️ Setup

### Prerequisites
- [Java 17+](https://adoptium.net/)  
- [Maven 3.8+](https://maven.apache.org/)  
- Chrome / Firefox installed (Selenium Manager handles drivers automatically)

### Clone
```bash
git clone https://github.com/mmnaseer1/OrangeHRM-Selenium-Framework.git
cd OrangeHRM-Selenium-Framework
```

### Install Dependencies
```bash
mvn clean install
```

---

## ▶️ Running Tests

### Default (QA, Chrome, headed)
```bash
mvn test
```

### Headless mode (for CI)
```bash
mvn -Dbrowser=chrome -DrunMode=headless test
```

### Run specific suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reports

After a run, open:

- **Extent Report** → `test-output/ExtentReport.html`  
  - Shows test steps (narrative logs)  
  - Screenshots for failures & skips  

- **TestNG HTML Report** → `test-output/index.html`

---

## 🧩 Features

- **Single Config** (`qa_config.properties`)  
- **Utilities**
  - Explicit waits (`waitForVisibility`, `safeClick`)  
  - Screenshot with timestamp & return path for reporting  
  - Dropdown selects (by index, text, value)  
  - Alert handling (accept/dismiss)

- **Data Provider**
  - Excel-driven via Apache POI.  
  - Test method name maps to sheet name (e.g., `validLogin` → sheet `validLogin`).

---

## 📸 Sample Report (ExtentReports)

```
✔️ validLogin
   → Navigating to login page
   → Entered username: admin
   → Entered password
   → Clicked Login button
   → Dashboard displayed successfully

❌ invalidLogin
   → Entered username: fakeUser
   → Entered password
   → Clicked Login button
   → Test failed – Invalid credentials error displayed
```

---

## 🤝 Contributing
1. Fork this repo  
2. Create a feature branch (`git checkout -b feature/xyz`)  
3. Commit changes (`git commit -m "Added XYZ"`)  
4. Push and open a PR 🎉

---
