# Design Notes

## Why ArrayList Instead of Array?

We chose `ArrayList` over traditional arrays for several important reasons:

### 1. **Dynamic Size**
- **Arrays**: Fixed size defined at creation. Cannot grow or shrink.
  ```java
  Student[] students = new Student[10]; // Stuck with 10 slots
  ```
- **ArrayList**: Automatically grows as needed.
  ```java
  ArrayList<Student> students = new ArrayList<>(); // Grows dynamically
  students.add(newStudent); // No size limit
  ```

### 2. **Built-in Methods**
ArrayList provides convenient methods:
- `add()`, `remove()`, `size()`, `contains()`
- `isEmpty()`, `clear()`, `indexOf()`

With arrays, you'd have to implement these yourself.

### 3. **Type Safety with Generics**
```java
ArrayList<Student> students = new ArrayList<>(); // Only Students allowed
```
This prevents runtime errors from mixing types.

### 4. **Real-World Applications**
Most production code uses collections like ArrayList because:
- Requirements change (user adds more data than initially expected)
- Easier to maintain and extend
- More robust error handling

## Where We Used Static Members and Why

### IdGenerator Class
```java
private static int studentIdCounter = 1;
public static int getNextStudentId() { ... }
```

**Why static?**
- Need a **single shared counter** across all instances
- Ensures unique IDs system-wide
- No need to create IdGenerator objects - use `IdGenerator.getNextStudentId()`

**Alternative without static**: Each IdGenerator object would have its own counter, causing duplicate IDs!

### Constants in MenuOptions
```java
public static final int STUDENT_MANAGEMENT = 1;
```

**Why static?**
- Constants are the same for all code - no need for instances
- `final` makes them immutable
- Accessible as `MenuOptions.STUDENT_MANAGEMENT` anywhere

## Where We Used Inheritance and Benefits

### Person → Student Hierarchy

```java
public class Person { ... }
public class Student extends Person { ... }
```

**Benefits:**

### 1. **Code Reuse**
Person defines common attributes (id, firstName, lastName, email).
Student inherits these without rewriting:
```java
// Instead of duplicating code in Student:
private int id;
private String firstName;
// ...we simply extend Person
```

### 2. **Polymorphism**
We can override methods for specialized behavior:
```java
// Person.java
public String getDisplayName() {
    return firstName + " " + lastName;
}

// Student.java
@Override
public String getDisplayName() {
    return super.getDisplayName() + " (" + batch + ")";
}
```

### 3. **Extensibility**
Easy to add new types (e.g., Trainer extends Person) without changing existing code.

### 4. **Logical Organization**
Reflects real-world relationship: "A Student IS-A Person with additional properties"

## Architecture: Repository-Service Pattern

We separated concerns into layers:

### Repository Layer
- **Purpose**: Data storage (in-memory ArrayList)
- **Responsibility**: CRUD operations (Create, Read, Update, Delete)
- **Example**: `StudentRepository` manages the ArrayList of students

### Service Layer
- **Purpose**: Business logic and validation
- **Responsibility**: Enforce rules, coordinate operations, handle exceptions
- **Example**: `StudentService` validates input, checks business rules, calls repository

### UI Layer (Main)
- **Purpose**: User interaction
- **Responsibility**: Display menus, get input, call services
- **Example**: `Main.java` handles console I/O

**Benefits:**
- **Separation of Concerns**: Each layer has a clear purpose
- **Maintainability**: Changes in one layer don't affect others
- **Testability**: Can test business logic separately from UI
- **Scalability**: Easy to swap in-memory storage for a database later

## Exception Handling Strategy

We use custom exceptions to provide clear, meaningful error messages:

### EntityNotFoundException
Thrown when looking up non-existent entities:
```java
Student student = findStudentById(999);
// throws: "Student with ID 999 not found"
```

### InvalidInputException
Thrown for validation failures:
```java
addStudent("", "Doe", "invalid-email", "Batch1");
// throws: "Invalid email format"
```

**Benefits:**
- User-friendly error messages
- Program doesn't crash on bad input
- Distinction between "not found" vs "invalid input" errors

## Key OOP Principles Demonstrated

1. **Encapsulation**: Private fields with public getters/setters
2. **Inheritance**: Student extends Person
3. **Polymorphism**: Overridden `getDisplayName()` method
4. **Abstraction**: Service layer hides complexity from UI
5. **Constructor Overloading**: Multiple ways to create Students
6. **DRY (Don't Repeat Yourself)**: Shared code in base classes and utility classes
