import java.util.*;

enum Priority {
    LOW, MEDIUM, HIGH
}

class Task {
    private String name;
    private boolean isCompleted;
    private Priority priority;
    private String category;

    public Task(String name, Priority priority, String category) {
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " [" + category + "] - Priority: " + priority + (isCompleted ? " [Completed]" : " [Pending]");
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name, Priority priority, String category) {
        tasks.add(new Task(name, priority, category));
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

  public void removeTask(String name) {
      tasks.removeIf(task -> task.getName().equals(name));
  }

 public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

   // 3. List tasks sorted by name
   public void sortTasksByName() {
       tasks.sort(Comparator.comparing(Task::getName));
   }
  public void sortTasksByPriority() {
      tasks.sort(Comparator.comparing(Task::getPriority));
  }

  public List<Task> filterByCategory(String category) {
      List<Task> filteredTasks = new ArrayList<>();
      for (Task task : tasks) {
          if (task.getCategory().equalsIgnoreCase(category)) {
              filteredTasks.add(task);
          }
      }
      return filteredTasks;
  }

 // 6. Find the highest-priority unfinished task
 public List<Task> getMostUrgentTasks() {
     List<Task> urgentTasks = new ArrayList<>();
     for (Task task : tasks) {
         if (!task.isCompleted()) {
             if (urgentTasks.isEmpty() || task.getPriority().compareTo(urgentTasks.get(0).getPriority()) < 0) {
                 urgentTasks.clear();
                 urgentTasks.add(task);
             } else if (task.getPriority().compareTo(urgentTasks.get(0).getPriority()) == 0) {
                 urgentTasks.add(task);
             }
         }
     }
     return urgentTasks;
 }

public Map<String, Integer> countTasksPerCategory() {
    Map<String, Integer> categoryCount = new HashMap<>();
    for (Task task : tasks) {
        categoryCount.put(task.getCategory(), categoryCount.getOrDefault(task.getCategory(), 0) + 1);
    }
    return categoryCount;
}

  public void markTaskCompleted(String name) {
      for (Task task : tasks) {
          if (task.getName().equals(name)) {
              task.complete();
              break;
          }
      }
  }

public void markCategoryCompleted(String category) {
    for (Task task : tasks) {
        if (task.getCategory().equalsIgnoreCase(category)) {
            task.complete();
        }
    }
}
}

public class SI2025Lab1Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("Write report", Priority.HIGH, "Work");
        manager.addTask("Submit assignment", Priority.MEDIUM, "School");
        manager.addTask("Buy groceries", Priority.LOW, "Personal");

        // MISSING: Calls to the new methods that will be implemented
        // 1. Remove a task by name
        manager.removeTask("Buy groceries");
        // 2. List completed tasks
        List<Task> completedTasks = manager.getCompletedTasks();
        for (Task task : completedTasks) {
            System.out.println(task);
        }
        // 3. List tasks sorted by name
        manager.sortTasksByName();
        // 4. List tasks sorted by priority
        manager.sortTasksByPriority();

        // 5. Filter tasks by category
        List<Task> filteredTasks = manager.filterByCategory("School");
        for (Task task : filteredTasks) {
            System.out.println(task);
        }
        // 6. Find the highest-priority unfinished task
        List<Task> urgentTasks = manager.getMostUrgentTasks();
        for (Task task : urgentTasks) {
            System.out.println(task);
        }
        // 7. Count tasks per category
        Map<String, Integer> categoryCount = manager.countTasksPerCategory();

        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        // 8. Mark a task as completed
        manager.markTaskCompleted("Write report");
        // 9. Mark all tasks in a category as completed
        manager.markCategoryCompleted("School");
        manager.printTasks();
    }
}