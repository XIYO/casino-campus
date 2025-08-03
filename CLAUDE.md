# MyBatis Learning Platform - Claude Instructions

Claude-specific guidelines for the MyBatis learning platform project.

## Project Overview

**Target**: Java beginner students learning MyBatis and Spring Boot
**Design**: Dual implementation architecture for progressive learning

## Dual Implementation Architecture

### File Naming
- **Learning**: `MemberMapper.java`, `ProfileMapper.java` (TODO comments, no implementations)
- **Reference**: `MemberMapperRef.java`, `ProfileMapperRef.java` (complete implementations)

### Profile Behavior
```yaml
Default Profile (Learning Mode):
  Build: SUCCESS (compiles)
  Tests: FAILURE (BindingException expected)
  Purpose: Show what needs implementation

Ref Profile (Reference Mode):
  Build: SUCCESS
  Tests: SUCCESS
  Purpose: Verify implementations work
```

## Technology Stack

- Spring Boot 3.5.4 + Java 24
- MyBatis 3.0.4 (annotation-based)
- H2 Database (in-memory, AUTO_INCREMENT)
- JUnit 5 + AssertJ
- Swagger/OpenAPI

## Educational TODO Structure

```java
/**
 * TODO: Write SQL using {@code @Insert} annotation
 * <ul>
 *   <li>Purpose: Practice INSERT and auto-generated keys</li>
 *   <li>Success: Data saved with auto-generated ID</li>
 *   <li>Hint: Use {@code @Options(useGeneratedKeys = true, keyProperty = "id")}</li>
 * </ul>  
 * Reference: {@code MemberMapperRef.java}
 */
```

## Test Patterns

### Profile-Dependent Tests
```java
@Test
void insertMember_success() {
    assumeTrue(memberMapper != null, "MyBatis mapper missing - run with ref profile");
    // Test implementation
}
```

### Production-Safe Tests
- Relative comparisons, not absolute values
- Work with existing data
- Conditional execution with `assumeTrue()`

## Execution

### Learning Mode
```bash
./gradlew test
# Build: SUCCESS, Tests: FAILURE (shows TODO items)
```

### Reference Mode
```bash
SPRING_PROFILES_ACTIVE=ref ./gradlew test  
# Build: SUCCESS, Tests: SUCCESS (verification)
```

### Find TODOs
`Shift + Shift` → Search "TODO"

## Code Rules

### Learning Files
1. TODO comments required with educational structure
2. `@Profile("!ref")` annotation
3. Method signatures only, no implementations
4. Reference file mentioned in JavaDoc
5. Build success guaranteed, test failures expected

### Reference Files
1. Complete MyBatis implementations with proper annotations
2. `@Profile("ref")` annotation
3. All tests pass when active
4. Serve as answer key for students

## File Structure

```
src/main/java/campus/membercampusstudy/
├── controller/
│   ├── JpaMemberController.java      # JPA comparison
│   └── MyBatisMemberController.java  # MyBatis learning
├── entity/
│   ├── Member.java                   # Member entity
│   └── Profile.java                  # Profile entity  
├── mapper/
│   ├── IMemberMapper.java            # Interface
│   ├── MemberMapper.java             # Learning (TODO)
│   ├── MemberMapperRef.java          # Reference (complete)
│   ├── IProfileMapper.java           # Interface
│   ├── ProfileMapper.java            # Learning (TODO)
│   └── ProfileMapperRef.java         # Reference (complete)
└── repository/
    ├── MemberRepository.java         # JPA repository
    └── ProfileRepository.java        # JPA repository
```

## MyBatis Learning Focus

### Annotations
- `@Select`, `@Insert`, `@Update`, `@Delete`
- `@Results`, `@Result` (result mapping)
- `@Options` (auto-generated keys)
- `@Param` (parameter binding)

### SQL Patterns
- CRUD operations
- Search queries: `LIKE`, `BETWEEN`, `WHERE`
- Foreign key handling: `member_id`
- Aggregate functions: `COUNT(*)`

### Common Issues
- BindingException (expected in learning mode)
- Profile confusion (when to use `ref`)
- SQL syntax: MyBatis `#{param}` format
- Result mapping: column vs field names

## Success Criteria

- Build always succeeds (students can compile/run)
- Progressive learning (TODO → implementation path)
- Immediate feedback (test failures show work needed)
- Reference validation (ref profile confirms correctness)

## Critical Rules

### Never
- Complete implementations in learning files
- All tests pass without ref profile
- Methods without TODO comments
- Missing profile annotations

### Always
- Educational TODO comments with learning value
- Dual implementation pattern maintained
- Production-safe tests
- Clear learning objectives

## Development Workflow

### New Features
1. Create learning version: TODO comments + `@Profile("!ref")`
2. Create reference version: complete implementation + `@Profile("ref")`
3. Write tests using `assumeTrue()` pattern
4. Verify: default fails, ref passes

### Bug Fixes
1. Fix reference version first
2. Update TODO comments if needed
3. Maintain production-safe tests

### Documentation
1. Structured JavaDoc with educational elements
2. Cross-reference learning/reference files
3. Include objectives and success criteria

---

This platform teaches MyBatis through hands-on implementation while providing safety nets and clear guidance for Java beginners.