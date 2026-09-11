# ShopGuard – Enterprise Hybrid Test Automation Framework (UI + API + SQL)

[![ShopGuard Enterprise Automation CI](https://github.com/Vivek346282737/ShopGuard-Automation-Framework/actions/workflows/ci.yml/badge.svg)](https://github.com/Vivek346282737/ShopGuard-Automation-Framework/actions)
![Tests](https://img.shields.io/badge/Tests-13%20Passed-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-4.24-orange)
![REST Assured](https://img.shields.io/badge/REST%20Assured-5.5-red)

An enterprise-grade hybrid test automation solution architected in Java 17 delivering synchronized coverage across Web UI (Page Object Model), Backend REST APIs, and Database Validation (JDBC/SQL) with automated Allure reporting and GitHub Actions CI/CD pipeline.

## Framework Architecture (3-Tier Validation)
- Web UI: Selenium WebDriver 4.24, Page Object Model, dynamic explicit synchronization.
- Backend API: REST Assured 5.5, status verification, JSON payload assertions.
- Database: JDBC In-Memory SQL persistence assertions.
- Continuous Integration: Headless GitHub Actions workflow on Ubuntu.

## Execution
mvn test
