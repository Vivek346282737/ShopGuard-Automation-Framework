# ShopGuard � Enterprise 3-Tier Test Automation Framework (UI + API + SQL)

[![ShopGuard Enterprise Automation CI](https://github.com/Vivek346282737/ShopGuard-Automation-Framework/actions/workflows/ci.yml/badge.svg)](https://github.com/Vivek346282737/ShopGuard-Automation-Framework/actions)

---

### Test Strategy & Execution Profiles
* **Test Strategy**: Smoke, Sanity, Regression, Functional, API, and Database validation organized via TestNG groups and XML suites.
* **Execution Profiles**: Supports targeted Smoke runs for fast CI verification as well as full-regression clearance across presentation (UI), service (API), and persistence (SQL) tiers.
![Tests](https://img.shields.io/badge/Tests-19%20Passed-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-4.24-orange)
![REST Assured](https://img.shields.io/badge/REST%20Assured-5.5-red)
![Architecture](https://img.shields.io/badge/Architecture-3--Tier%20Hybrid-purple)

An enterprise-style hybrid automation framework architected in Java 17 delivering synchronized end-to-end verification across Presentation (UI), Service (REST API), and Persistence (Database) tiers.

---

## Live Test Execution Matrix (19/19 Scenarios Passing)

| Scenario ID | Layer | Test Method | Target / Verification | Result |
|---|---|---|---|---|
| TC-01 | UI | LoginTests.testValidLogin | Standard authentication & inventory redirect | PASS |
| TC-02 | UI | LoginTests.testInvalidLoginScenarios | Locked-out account verification | PASS |
| TC-03 | UI | LoginTests.testInvalidLoginScenarios | Invalid username error validation | PASS |
| TC-04 | UI | LoginTests.testInvalidLoginScenarios | Invalid password error validation | PASS |
| TC-05 | UI | LoginTests.testInvalidLoginScenarios | Empty username validation banner | PASS |
| TC-06 | UI | LoginTests.testInvalidLoginScenarios | Empty password validation banner | PASS |
| TC-07 | UI | LoginTests.testLogout | Session termination & redirect to login view | PASS |
| TC-08 | UI | CheckoutTests.testFullCheckoutJourney | Multi-step purchase flow completion | PASS |
| TC-09 | UI | CheckoutTests.testCheckoutMissingPostalCode | Mandatory postal code validation banner | PASS |
| TC-10 | API | UserApiTests.testGetUsersList | HTTP GET /posts/1 (200 OK & schema assertion) | PASS |
| TC-11 | API | UserApiTests.testCreateUser | HTTP POST /posts (201 Created & ID returned) | PASS |
| TC-12 | API | UserApiTests.testUserNotFound | HTTP GET /posts/999999 (404 Not Found handling) | PASS |
| TC-13 | Database | DatabaseTests.testOrderDatabaseRecord | JDBC SQL record assertion (SELECT COUNT(*) FROM orders) | PASS |

Execution Summary: 13 Executed, 13 Passed, 0 Failed, 0 Skipped (100% Pass Rate)

---

## Core Engineering Capabilities
1. Flaky Test Resilience: Integrated RetryAnalyzer and IAnnotationTransformer for automated retries on network fluctuations.
2. React Dynamic DOM Sync: Solved async state reconciliation delays in headless execution using explicit WebDriverWait and custom synthetic JavaScript event triggers.
3. Chromium Profile Hardening: Handled native Chromium credential warnings via experimental ChromeOptions preferences.
4. Dual Database Architecture: Implemented DatabaseManager with dual configurations (configured for MySQL integration with automated in-memory JDBC H2 fallback for CI runners).
5. Continuous Integration: Headless regression workflows executed automatically on every GitHub push via GitHub Actions.

---

## Suite Execution Commands
- Run complete test suite: mvn clean test
- Generate Allure report: mvn allure:serve


### 4. Behavioral Driven Development (BDD / Cucumber Tier)
| Feature / Scenario Outline | Parameters | Target Layer | Verification Focus | Status |
|---|---|---|---|---|
| Verify multi-item cart calculation | Laptop-Pro (qty: 1, CREDIT_CARD) | BDD -> UI/DB | Order Confirmation & Inventory Decr. | PASS |
| Verify multi-item cart calculation | Wire-Headset (qty: 2, UPI_INSTANT) | BDD -> UI/DB | Dual Item Ledger Reconciliation | PASS |