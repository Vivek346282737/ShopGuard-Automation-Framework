# ShopGuard – Enterprise Test Automation Plan

## 1. Scope & Objective
Automated multi-tier regression and smoke verification across Saucedemo e-commerce flows:
- **UI Tier**: Selenium WebDriver 4.24 with Page Object Model & dynamic explicit synchronization.
- **API Tier**: REST Assured 5.5 for status code, schema contracts, and negative response codes.
- **Database Tier**: JDBC SQL query assertions for transactional persistence validation.

## 2. Test Execution Strategy
- **Suite Runs**: Complete regression suite executed headlessly via GitHub Actions on Ubuntu runner.
- **Flaky Handling**: Integrated `RetryAnalyzer` through TestNG `IAnnotationTransformer` to retry any intermittent network flakiness once automatically.
- **Reporting**: Full Allure visual reporting with screenshot capture on any test deviation.