import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        int totalTests = 0;
        int failedTests = 0;

        // Helper function to print the result of each test
        record printTestResult(String testName, boolean result, String expected, String actual) {
            totalTests++;
            if (result) {
                System.out.println(testName + " PASSED");
            } else {
                failedTests++;
                System.out.println(testName + " FAILED: Expected " + expected + " but got " + actual);
            }
        }

        // Test 1: Testing add() and traverse()
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.print("Test 1: Adding elements and traversing: ");
        list1.traverse(); // Expected output: 1 -> 2 -> 3 -> null
        printTestResult("Adding elements", list1.search(2), "true", "true");

        // Test 2: Testing remove()
        boolean removeTest = list1.remove(2);
        System.out.print("Test 2: Removing element 2: ");
        list1.traverse(); // Expected output: 1 -> 3 -> null
        printTestResult("Removing element 2", removeTest, "true", "true");

        // Test 3: Search for removed element
        boolean searchResult = list1.search(2);
        System.out.println("Test 3: Searching for 2 after removal: " + searchResult); // Expected: false
        printTestResult("Search after removal", !searchResult, "true", "true");

        // Test 4: Edge case - remove from empty list
        LinkedList<Integer> list2 = new LinkedList<>();
        boolean removeEmptyTest = list2.remove(1);
        System.out.println("Test 4: Removing from empty list: " + removeEmptyTest); // Expected: false
        printTestResult("Removing from empty list", !removeEmptyTest, "true", "true");

        // Test 5: Edge case - search in empty list
        boolean searchEmptyTest = list2.search(1);
        System.out.println("Test 5: Searching in empty list: " + searchEmptyTest); // Expected: false
        printTestResult("Search in empty list", !searchEmptyTest, "true", "true");

        // Final results
        System.out.println("\nTest Results Summary:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Failed tests: " + failedTests);
    }
}

