# ShopGuard – Defect Tracking & Bug Lifecycle Log

### BUG-SG-001 (P1 - High) | Resolved
- **Title**: React DOM reconciliation race condition on synthetic checkout inputs.
- **Root Cause**: Asynchronous React SPA state updates missed native browser events dispatched solely by `element.sendKeys()`.
- **Resolution**: Implemented custom synthetic JavaScript event triggers (`window.dispatchEvent(new Event('input'))`) combined with explicit `WebDriverWait` synchronization.
- **Regression Verification**: Confirmed 0 failures across 15 headless runs.

### BUG-SG-002 (P2 - Medium) | Resolved
- **Title**: Native Chromium password breach security overlays obstructing automated interactions.
- **Root Cause**: Chromium profile security alerts intercepted clicks on automated authentication tests.
- **Resolution**: Initialized experimental `ChromeOptions` preferences (`credentials_enable_service=false`, `password_leak_detection=false`).
- **Regression Verification**: Tests execute cleanly without profile-level interceptions.