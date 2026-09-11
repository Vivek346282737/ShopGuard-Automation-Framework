# ShopGuard – Enterprise Test Automation Plan

## 1. Objective & Scope
The objective of this framework is to provide automated continuous regression and smoke validation across the e-commerce transaction lifecycle:
- **Presentation Layer**: Web UI journey automation via Selenium WebDriver 4.24 (Page Object Model).
- **Service Layer**: REST API contract and status code verification using REST Assured.
- **Persistence Layer**: SQL transactional assertions via JDBC directly against database tables.

## 2. Test Execution Tiers & Strategy
- **Smoke Suite (`groups = {"smoke"}`)**: 5 high-criticality tests executed on every PR trigger (valid login, checkout, API status, DB query).
- **Regression Suite (`groups = {"regression"}`)**: All 13 scenarios running on scheduled nightly CI builds.
- **Flaky Test Resilience**: Automatic single-retry policy managed by `RetryAnalyzer` and TestNG `IAnnotationTransformer`.

## 3. Toolchain & Stack
- **Languages**: Java 17
- **Test Orchestrator**: TestNG 7.10
- **Web Driver**: Selenium 4.24 (ThreadLocal driver management)
- **API Engine**: REST Assured 5.5
- **Database Connector**: JDBC with H2 and MySQL profiles
- **CI/CD**: Headless GitHub Actions on Ubuntu
- **Reporting**: Allure Reports with failure screenshot attachments