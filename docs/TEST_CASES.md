# ShopGuard – 13 Core Test Scenarios Matrix

| ID | Group | Layer | Test Method | Target / Verification | Pass/Fail |
|---|---|---|---|---|---|
| TC-01 | Smoke, Reg | UI | `LoginTests.testValidLogin` | Standard authentication & inventory redirect | PASS |
| TC-02 | Regression | UI | `LoginTests.testInvalidLoginScenarios[0]` | Locked-out account error validation | PASS |
| TC-03 | Regression | UI | `LoginTests.testInvalidLoginScenarios[1]` | Invalid username assertion | PASS |
| TC-04 | Regression | UI | `LoginTests.testInvalidLoginScenarios[2]` | Invalid password assertion | PASS |
| TC-05 | Regression | UI | `LoginTests.testInvalidLoginScenarios[3]` | Empty username validation banner | PASS |
| TC-06 | Regression | UI | `LoginTests.testInvalidLoginScenarios[4]` | Empty password validation banner | PASS |
| TC-07 | Regression | UI | `LoginTests.testLogout` | Session termination & redirect to login view | PASS |
| TC-08 | Smoke, Reg | UI | `CheckoutTests.testFullCheckoutJourney` | Multi-step purchase flow completion | PASS |
| TC-09 | Regression | UI | `CheckoutTests.testCheckoutMissingPostalCode` | Form validation: Missing postal code assertion | PASS |
| TC-10 | Smoke, Reg | API | `UserApiTests.testGetUsersList` | HTTP GET /users, 200 OK & schema consistency | PASS |
| TC-11 | Smoke, Reg | API | `UserApiTests.testCreateUser` | HTTP POST /users, 201 Created & ID returned | PASS |
| TC-12 | Regression | API | `UserApiTests.testUserNotFound` | HTTP GET /users/23, 404 resource not found | PASS |
| TC-13 | Smoke, Reg | DB | `DatabaseTests.testOrderDatabaseRecord` | SQL query: In-memory JDBC record persistence | PASS |

**Suite Result: 13 Tests Executed, 13 Passed (100% Pass Rate)**