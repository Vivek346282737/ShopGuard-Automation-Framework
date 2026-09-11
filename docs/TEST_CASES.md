# ShopGuard – Automated Test Execution Matrix (15 Core Scenarios)

| ID | Layer | Group | Method / Class | Target Verification / Assertions | Status |
|---|---|---|---|---|---|
| TC-01 | UI | Smoke, Reg | `LoginTests.testValidLogin` | Standard user login & inventory URL redirection | ✅ PASS |
| TC-02 | UI | Regression | `LoginTests.testInvalidLoginScenarios[0]` | Locked-out account authentication rejection banner | ✅ PASS |
| TC-03 | UI | Regression | `LoginTests.testInvalidLoginScenarios[1]` | Invalid username credential rejection | ✅ PASS |
| TC-04 | UI | Regression | `LoginTests.testInvalidLoginScenarios[2]` | Invalid password credential rejection | ✅ PASS |
| TC-05 | UI | Regression | `LoginTests.testInvalidLoginScenarios[3]` | Empty username inline validation message | ✅ PASS |
| TC-06 | UI | Regression | `LoginTests.testInvalidLoginScenarios[4]` | Empty password inline validation message | ✅ PASS |
| TC-07 | UI | Regression | `LoginTests.testLogout` | User session invalidation & redirect to login view | ✅ PASS |
| TC-08 | UI | Smoke, Reg | `CheckoutTests.testFullCheckoutJourney` | Full end-to-end checkout & "Thank you for your order!" | ✅ PASS |
| TC-09 | UI | Regression | `CheckoutTests.testAddAndRemoveFromCart` | Dynamic badge update on item addition & removal | ✅ PASS |
| TC-10 | UI | Regression | `CheckoutTests.testProductSortingLowToHigh` | Dropdown sort: Price (low to high) asserts $7.99 item | ✅ PASS |
| TC-11 | UI | Regression | `CheckoutTests.testCheckoutMissingFirstName` | Form validation: Error First Name is required | ✅ PASS |
| TC-12 | UI | Regression | `CheckoutTests.testCheckoutMissingPostalCode` | Form validation: Error Postal Code is required | ✅ PASS |
| TC-13 | API | Smoke, Reg | `UserApiTests.testGetUsersList` | HTTP GET `/posts/1` - status 200 OK & non-null schema | ✅ PASS |
| TC-14 | API | Smoke, Reg | `UserApiTests.testCreateUser` | HTTP POST `/posts` - status 201 Created & ID returned | ✅ PASS |
| TC-15 | API | Regression | `UserApiTests.testUserNotFound` | HTTP GET `/posts/999999` - negative HTTP 404 handled | ✅ PASS |
| TC-16 | DB | Smoke, Reg | `DatabaseTests.testOrderDatabaseRecord` | PreparedStatement query on orders table (`ORD-9821`) | ✅ PASS |

**Suite Summary: 16 Core Automated Scenarios (100% Pass Rate Verified)**