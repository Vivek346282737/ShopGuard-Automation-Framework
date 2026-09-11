# ShopGuard – Automated Test Execution Matrix (13 Scenarios)

| ID | Layer | Test Name | Method / File | Assertion / Verification Target | Status |
|---|---|---|---|---|---|
| TC-01 | UI | Invalid Login - Locked Out User | `testInvalidLoginScenarios` | Verify dynamic alert: `Epic sadface: Sorry, this user has been locked out.` | ✅ PASS |
| TC-02 | UI | Invalid Login - Wrong Password | `testInvalidLoginScenarios` | Verify error banner: `Username and password do not match` | ✅ PASS |
| TC-03 | UI | Invalid Login - Empty Username | `testInvalidLoginScenarios` | Verify validation warning: `Username is required` | ✅ PASS |
| TC-04 | UI | Invalid Login - Empty Password | `testInvalidLoginScenarios` | Verify validation warning: `Password is required` | ✅ PASS |
| TC-05 | UI | Invalid Login - Non-existent User | `testInvalidLoginScenarios` | Assert authentication rejection | ✅ PASS |
| TC-06 | UI | Valid User Login Journey | `testValidLogin` | Verify landing page redirection to `/inventory.html` and header title | ✅ PASS |
| TC-07 | UI | User Session Logout | `testLogout` | Assert session invalidation and navigation back to login screen | ✅ PASS |
| TC-08 | UI | Checkout Negative Validation | `testCheckoutMissingPostalCode` | Assert inline postal code missing error in multi-step form | ✅ PASS |
| TC-09 | UI | End-to-End E-Commerce Checkout | `testFullCheckoutJourney` | Assert full order journey, item calculation, and thank you confirmation | ✅ PASS |
| TC-10 | API | Create User Endpoint | `testCreateUser` | HTTP POST validation, status 201 Created & non-null response payload ID | ✅ PASS |
| TC-11 | API | Fetch User Directory | `testGetUsersList` | HTTP GET verification, status 200 OK & root data array validation | ✅ PASS |
| TC-12 | API | Negative Resource Retrieval | `testUserNotFound` | HTTP GET 404 Not Found schema & empty response verification | ✅ PASS |
| TC-13 | SQL/DB | Transaction Persistence | `testOrderDatabaseRecord` | In-memory JDBC query (`SELECT COUNT(*) FROM orders WHERE order_id='ORD-9821'`) | ✅ PASS |

**Suite Result: 13 Passed, 0 Failed, 0 Skipped (100% Suite Pass Rate)**