# ShopGuard – 13 Core Test Scenarios Matrix

| ID | Layer | Test Method | Target / Verification | Pass Status |
|---|---|---|---|---|
| TC-01 | UI | `LoginTests.testValidLogin` | Standard authentication & landing verification | ✅ PASS |
| TC-02 | UI | `LoginTests.testInvalidLoginScenarios[0]` | Locked-out account verification | ✅ PASS |
| TC-03 | UI | `LoginTests.testInvalidLoginScenarios[1]` | Invalid username assertion | ✅ PASS |
| TC-04 | UI | `LoginTests.testInvalidLoginScenarios[2]` | Invalid password assertion | ✅ PASS |
| TC-05 | UI | `LoginTests.testInvalidLoginScenarios[3]` | Empty username assertion | ✅ PASS |
| TC-06 | UI | `LoginTests.testInvalidLoginScenarios[4]` | Empty password assertion | ✅ PASS |
| TC-07 | UI | `LoginTests.testLogout` | Session termination & redirect | ✅ PASS |
| TC-08 | UI | `CheckoutTests.testFullCheckoutJourney` | Multi-step purchase flow completion | ✅ PASS |
| TC-09 | UI | `CheckoutTests.testCheckoutMissingPostalCode` | Mandatory postal code validation banner | ✅ PASS |
| TC-10 | API | `UserApiTests.testGetUsersList` | HTTP GET /users, status 200 & data payload | ✅ PASS |
| TC-11 | API | `UserApiTests.testCreateUser` | HTTP POST /users, status 201 & ID returned | ✅ PASS |
| TC-12 | API | `UserApiTests.testUserNotFound` | HTTP GET /users/23, status 404 handled | ✅ PASS |
| TC-13 | DB | `DatabaseTests.testOrderDatabaseRecord` | In-memory JDBC SQL query record verification | ✅ PASS |

**Total Suite Result: 13 Run, 13 Passed, 0 Failed (100% Pass Rate)**