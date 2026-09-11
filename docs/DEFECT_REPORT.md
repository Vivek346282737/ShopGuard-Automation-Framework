# ShopGuard – Defect Tracking & Bug Lifecycle Log

### BUG-SG-001 (High - Flaky Execution in Headless Mode)
- **Component**: UI Automation / React SPA Event Propagation
- **Root Cause Analysis (RCA)**: React synthetic event dispatcher missed input state changes during headless Selenium typing.
- **Resolution**: Integrated synthetic JavaScript event triggers (`window.dispatchEvent(new Event('input'))`) with explicit waits.
- **Status**: CLOSED & VERIFIED

### BUG-SG-002 (Medium - Chromium Security Notification Overlays)
- **Component**: Driver Configuration / Browser Security Layer
- **Root Cause Analysis (RCA)**: Native Chromium alerts about test credentials compromised in third-party data breaches.
- **Resolution**: Added experimental preferences (`credentials_enable_service=false`, `password_leak_detection=false`) in ChromeOptions.
- **Status**: CLOSED & VERIFIED