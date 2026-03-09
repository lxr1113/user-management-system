# AGENTS.md - Developer Guidelines for user-management

## Project Overview

This is a **Spring Boot + MyBatis + MySQL** user management system providing RESTful CRUD APIs.

**Tech Stack:** Java 11, Spring Boot 2.6.13, MyBatis 2.2.2, MySQL, Lombok

---

## 1. Build, Test & Run Commands

### Build
```bash
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```

### Run Single Test
```bash
mvn test -Dtest=UserManagementApplicationTests                    # specific class
mvn test -Dtest=UserManagementApplicationTests#testMethodName    # specific method
mvn test -X                                                         # verbose output
```

---

## 2. Code Style Guidelines

### 2.1 General Principles

- **No comments** unless required for complex business logic
- Keep methods short and focused (single responsibility)
- Use meaningful variable and method names

### 2.2 Imports

**Order (IntelliJ default):** `java.*` / `javax.*` → third-party (`org.*`, `com.*`) → project (`com.example.*`)

```java
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.mapper.UserMapper;
```

### 2.3 Formatting

- Use **4 spaces** for indentation (no tabs)
- Line length: max 120 characters
- One blank line between import groups
- Opening brace on same line

### 2.4 Naming Conventions

| Type | Convention | Example |
|------|------------|---------|
| Class/Interface | PascalCase | `UserService`, `UserMapper` |
| Method | camelCase | `findById()`, `addUser()` |
| Variable | camelCase | `userMapper`, `userList` |
| Package | lowercase | `com.example.usermanagement.entity` |

### 2.5 Type Guidelines

- Use **interface** for service layer
- Use **class** for entity, DTO, Mapper
- Use wrapper classes for nullable numbers: `Integer` not `int`
- Use `List<T>` for collections, not arrays
- Use `LocalDateTime` for timestamps

### 2.6 Entity Rules

- Use **Lombok `@Data`** for entities
- Match field names to database columns

```java
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private LocalDateTime createTime;
}
```

### 2.7 Service Layer

- Use **interface + implementation** pattern
- Use `@Service` on implementation
- Use `@Autowired` for dependency injection
- Use `@Transactional` for write operations
- Validate input in service layer

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        userMapper.insert(user);
    }
}
```

### 2.8 Mapper Layer (MyBatis)

- Use `@Mapper` annotation
- Use annotation-based SQL (`@Select`, `@Insert`, `@Update`, `@Delete`)
- Use `@Options` for auto-generated keys
- Use `#{}` for parameters, never `${}` (SQL injection risk)

```java
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Insert("INSERT INTO user(username, password, email, age) VALUES(#{username}, #{password}, #{email}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
}
```

### 2.9 Controller Layer

- Use `@RestController` for REST APIs
- Use `@RequestMapping` for base path
- Use appropriate HTTP method annotations: `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- Use `@PathVariable` for URL parameters, `@RequestBody` for request body

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public String add(@RequestBody User user) {
        userService.addUser(user);
        return "添加成功，ID：" + user.getId();
    }
}
```

### 2.10 Error Handling

- Throw `IllegalArgumentException` for validation errors (returns 400)
- Throw `RuntimeException` for business errors (returns 500)
- Use `GlobalExceptionHandler` with `@RestControllerAdvice` for centralized exception handling
- Return `Result<T>` wrapper for consistent API responses

---

## 3. Project Structure

```
src/main/java/com/example/usermanagement/
├── UserManagementApplication.java    # Spring Boot main class
├── controller/                       # REST controllers
├── service/                          # Service interfaces + impl/
├── mapper/                           # MyBatis mappers
├── entity/                           # Domain entities
├── dto/                              # Data transfer objects
└── exception/                        # Exception handlers
```

---

## 4. Best Practices

1. **Always validate input** in service layer
2. **Use transactions** for multi-table operations
3. **Write unit tests** for service layer
4. **Use DTOs** for API request/response (separate from entities)
5. **Keep controllers thin** - delegate all logic to services

---

## 5. Common Tasks

### Add a new entity
1. Create entity class in `entity/` with `@Data`
2. Create mapper interface in `mapper/` with `@Mapper`
3. Add MyBatis annotations for CRUD
4. Create service interface in `service/`
5. Create service implementation in `service/impl/`
6. Create controller in `controller/`

### Add validation
Throw `IllegalArgumentException` in service layer, handled by `GlobalExceptionHandler`.

### Add a new API endpoint
1. Add method to service interface
2. Implement in service implementation
3. Add endpoint in controller with appropriate HTTP method annotation
