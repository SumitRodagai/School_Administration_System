# School_Administration_System

# 🎓 School Administration System

A **web-based School Administration System** developed using **Spring Boot, Thymeleaf, and MySQL** to manage day-to-day school operations such as student records, attendance, performance, fees, and reports.

---

## 🚀 Features

### 👤 User Management

* Role-based login system
* Roles: **ADMIN, CLERK, TEACHER, PRINCIPAL**
* Secure session handling

---

### 🎓 Student Management

* Add new students
* View student list
* Edit & delete student details

---

### 📅 Attendance Management

* Mark student attendance (Present/Absent)
* View attendance records
* Edit & delete attendance
* Attendance report generation

---

### 📊 Performance Management

* Enter marks for multiple subjects at once
* View subject-wise marks
* Automatic **Total & Percentage calculation**
* Edit & delete marks
* Student performance report

---

### 💰 Fee Management

* Record fee payments
* View payment history
* Generate fee reports

---

### 📚 Syllabus Management

* Add syllabus by class & subject
* View syllabus report

---

### 🗓️ Class Scheduling

* Assign subjects to classes
* Assign teachers
* Edit & delete schedules

---

### 📈 Reports Module

* Student Admission Report
* Attendance Report
* Performance Report
* Fee Payment Report

---

### 📊 Dashboard (Analysis)

* Total students
* Total fees collected
* Attendance count
* Average marks

---

## 🛠️ Technologies Used

* **Backend:** Java, Spring Boot
* **Frontend:** HTML, CSS, Thymeleaf
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Server:** Apache Tomcat

---

## 🗂️ Project Structure

```
com.school
│
├── controller
├── service
├── repository
├── model
│
└── templates (Thymeleaf HTML files)
```

---

## 🗄️ Database Tables

* `employee`
* `student`
* `attendance`
* `performance`
* `fee_payment`
* `syllabus`
* `class_schedule`

---

## ⚙️ Setup Instructions

1. Clone the repository

```bash
git clone https://github.com/your-username/school-admin-system.git
```

2. Configure MySQL in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/school_admin
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

3. Run the project

```bash
mvn spring-boot:run
```

4. Open in browser:

```
http://localhost:8080
```

---

## 🔑 Sample Login Credentials

| Role      | Username  | Password     |
| --------- | --------- | ------------ |
| Admin     | admin     | admin123     |
| Clerk     | clerk     | clerk123     |
| Teacher   | teacher   | teacher123   |
| Principal | principal | principal123 |

---

## 📌 Key Highlights

* Role-based access control
* Full CRUD operations
* Clean UI with Thymeleaf
* Automatic calculations (marks & percentage)
* Modular architecture (MVC pattern)

---

## 🎯 Future Enhancements

* Spring Security authentication
* PDF report generation
* Charts & analytics dashboard
* Email notifications
* REST API integration

---

## 📄 License

This project is for educational purposes.
