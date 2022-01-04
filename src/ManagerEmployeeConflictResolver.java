import java.util.*;

public class ManagerEmployeeConflictResolver {
    public static void main(String[] args) {
        Map<Integer, Integer> organizationHierarchy = new HashMap<>();
        getData(organizationHierarchy);
        System.out.println(findManagerToResolveConflict(organizationHierarchy, 300, 30, 1100));
    }

    private static Integer findManagerToResolveConflict(Map<Integer, Integer> organizationHierarchy, Integer... employees) {
        LinkedHashMap<Integer, Integer> managerToConflictCount = new LinkedHashMap<>();
        Arrays.stream(employees).forEach(e -> traverseAndUpdateTheConflictCount(organizationHierarchy.get(e), organizationHierarchy, managerToConflictCount));
        return managerToConflictCount.keySet().stream().filter(key -> managerToConflictCount.get(key) == employees.length).findFirst().orElse(null);
    }

    private static void traverseAndUpdateTheConflictCount(Integer employee, Map<Integer, Integer> organizationHierarchy,
                                                          Map<Integer, Integer> managerToConflictCount) {
        Optional.ofNullable(employee).ifPresent(emp -> {
            Optional.ofNullable(managerToConflictCount.get(employee))
                    .ifPresentOrElse(count -> managerToConflictCount.put(employee, count+1),
                            () -> managerToConflictCount.put(employee, 1));
            Optional.ofNullable(organizationHierarchy.get(employee))
                    .ifPresent(parent -> traverseAndUpdateTheConflictCount(parent, organizationHierarchy, managerToConflictCount));
        });
    }

    private static void getData(Map<Integer, Integer> employeeToManager) {
        employeeToManager.put(82, 80);
        employeeToManager.put(15, 2);
        employeeToManager.put(1001, 4);
        employeeToManager.put(1100, 30);
        employeeToManager.put(860, 21);
        employeeToManager.put(80, 100);
        employeeToManager.put(2, 100);
        employeeToManager.put(4, 100);
        employeeToManager.put(30, 300);
        employeeToManager.put(21, 1550);
        employeeToManager.put(31, 1550);
        employeeToManager.put(11, 1550);
        employeeToManager.put(22, 155524);
        employeeToManager.put(155524, 1550);
        employeeToManager.put(100, 1);
        employeeToManager.put(300, 1);
        employeeToManager.put(1550, 1);
        employeeToManager.put(1, null);
    }

}
