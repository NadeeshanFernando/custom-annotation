# 🧩 Custom Logging Annotation with AOP in Spring Boot

This project demonstrates how to build a reusable custom logging annotation (`@MyLogger`) using Spring Boot, AOP (Aspect-Oriented Programming), and Lombok.

It automatically logs:
- Method start and finish
- Method arguments
- Exceptions (with stack trace)
- Return values (optional)

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3+
- Spring AOP
- Lombok
- Maven

---

## 📁 Project Structure

```
com.anton.custom_annotation
├── annotation             # @MyLogger annotation
├── aspect                 # AOP logic to handle annotation
├── controller             # REST controller to test logging
├── service
│   ├── OrderService       # Interface
│   └── impl
│       └── OrderServiceImpl # Business logic implementation
└── CustomAnnotationApplication.java
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- Maven
- IDE with Lombok support (IntelliJ recommended)

---

### 🔧 Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/custom-logger-springboot.git
   cd custom-logger-springboot
   ```

2. Import as a **Maven project** into IntelliJ or any IDE.

3. Enable annotation processing (in IntelliJ):
   - `File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors`
   - ✅ Check "Enable annotation processing"

4. Run the app:
   ```bash
   mvn spring-boot:run
   ```

5. Access the API:
   ```http
   GET http://localhost:8080/api/order?productId=ABC123&quantity=2
   ```

---

## 🧪 Sample Outputs

### ✅ Successful Request

```
GET /api/order?productId=ABC123&quantity=2
```

**Console Output:**

```
Controller: received API call
```

**Application Logs:**

```
▶️ [START] OrderController.placeOrder() | Args: [ABC123, 2]
▶️ [START] OrderServiceImpl.placeOrder() | Args: [ABC123, 2]
Order placed for product: ABC123, quantity: 2
✅ [SUCCESS] OrderServiceImpl.placeOrder() | Result: Order placed for product: ABC123, quantity: 2
✅ [SUCCESS] OrderController.placeOrder() | Result: Order placed for product: ABC123, quantity: 2
```

---

### ❌ Failing Request (Triggers Exception)

```
GET /api/order?productId=ABC123&quantity=0
```

**Console Output:**

```
Controller: received API call
```

**Application Logs:**

```
▶️ [START] OrderController.placeOrder() | Args: [ABC123, 0]
▶️ [START] OrderServiceImpl.placeOrder() | Args: [ABC123, 0]
❌ [ERROR] OrderServiceImpl.placeOrder() | Exception: Quantity must be greater than 0
java.lang.IllegalArgumentException: Quantity must be greater than 0
    at com.anton.custom_annotation.service.impl.OrderServiceImpl.placeOrder(OrderServiceImpl.java:...
❌ [ERROR] OrderController.placeOrder() | Exception: Quantity must be greater than 0
```

**HTTP Response:**
```
❗ Error: Quantity must be greater than 0
```

---

## 🔍 How It Works

### `@MyLogger` Annotation

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLogger {}
```

### `MyLoggerAspect` AOP

- Intercepts any method with `@MyLogger`
- Logs method name, class, arguments
- Catches and logs exceptions
- Can be extended to log return values and execution time

---

## ✅ Example Usage

```java
@GetMapping
@MyLogger
public String placeOrder(@RequestParam String productId, @RequestParam int quantity) {
    log.info("Controller: received API call");
    return orderService.placeOrder(productId, quantity);
}

@Override
@MyLogger
public String placeOrder(String productId, int quantity) {
    if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
    log.info("Order placed for product: " + productId + ", quantity: " + quantity);
    return "Order placed for product: " + productId + ", quantity: " + quantity;
}
```

---

## 💡 Extendable Features

You can easily add:
- Logging return values
- Execution time tracking
- Conditional logging by environment (e.g., only in `dev`)
- Role-based logging or tagging per team/module

---

## 🧹 License

This project is open source. Feel free to modify and use it in your own projects.

---

## 🙋‍♂️ Author

Developed by [Your Name / GitHub](https://github.com/yourusername)