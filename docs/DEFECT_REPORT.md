# ShopGuard – Defect Tracking & Bug Lifecycle Documentation

### Defect ID: BUG-SG-001 (High - Flaky Execution in Headless Mode)
- **Component**: UI Automation / React SPA Event Propagation
- **Environment**: Chrome 152 / Chromium Headless on Linux & Windows
- **Severity**: High | **Priority**: P1 | **Status**: CLOSED / RESOLVED
- **Description**: Standard Selenium `sendKeys()` intermittently failed to trigger React synthetic state updates on dynamic checkout input fields, leading to checkout validation false-positives.
- **Root Cause Analysis (RCA)**: React synthetic event system requires discrete `input` and `change` event dispatches on the DOM node.
- **Fix Implemented**: Integrated JavaScript synthetic dispatcher `window.dispatchEvent(new Event('input'))` alongside explicit `WebDriverWait` synchronizations.
- **Verification**: Verified across 10 consecutive headless regression cycles with zero failures.

### Defect ID: BUG-SG-002 (Medium - Chromium Security Notification Overlays)
- **Component**: Driver Configuration / Browser Security Layer
- **Severity**: Medium | **Priority**: P2 | **Status**: CLOSED / RESOLVED
- **Description**: Default Chrome profiles triggered native modal overlays warning about test credentials compromised in third-party data breaches, blocking DOM element interaction.
- **Fix Implemented**: Configured ChromeOptions experimental preferences (`credentials_enable_service=false`, `password_leak_detection=false`) to isolate test automation profiles.
- **Verification**: Tests run cleanly in both headed and headless modes without UI interception.