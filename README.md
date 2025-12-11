# User-Verification-Page
A complete CRUD (Create, Read, Update, Delete) web application built using Java Servlets, MySQL, and Apache Tomcat with secure database connectivity.

📋 Project Overview
A fully functional user management system with authentication, session handling, and secure database operations using Prepared Statements to prevent SQL injection attacks.

🚀 Features
✅ User Registration & Login with secure authentication

✅ Full CRUD Operations (Create, Read, Update, Delete)

✅ Database Connectivity using JDBC and MySQL

✅ Session Management for user tracking

✅ Secure Prepared Statements to prevent SQL injection

✅ Responsive HTML Interface

✅ Auto-redirect with success pop-ups

🛠️ Tech Stack
Backend: Java Servlets (Jakarta EE)

Database: MySQL

Server: Apache Tomcat 10+

Frontend: HTML

Build Tool: NetBeans IDE

Database Driver: MySQL Connector/J

📁 Project Structure
text
UserManagementSystem/
│
├── src/
│   └── userlogin.java          # Main Servlet with CRUD operations
│
├── WebContent/
│   ├── index.html              # Main menu
│   ├── add.html                # Add user form
│   ├── delete.html             # Delete user form
│   ├── update.html             # Update user form
│   ├── view.html               # View single user
│   ├── viewall.html            # View all users
│   ├── search.html             # Search user
│   └── WEB-INF/
│       └── web.xml             # Servlet configuration
│
├── lib/
│   └── mysql-connector-java-8.0.33.jar  # MySQL driver
│
└── README.md                   # This file
🗄️ Database Setup
sql
-- Create database
CREATE DATABASE sys;

-- Use database
USE sys;

-- Create users table
CREATE TABLE userlogin (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    userpassword VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
⚙️ Configuration
Update database credentials in userlogin.java:

java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/sys", 
    "root", 
    "your_password"
);
Add MySQL connector JAR to WEB-INF/lib/ folder

🎯 How to Run
Clone the repository

Set up MySQL database using the SQL script above

Configure database credentials in the servlet

Deploy on Tomcat server

Access application: http://localhost:8080/ProjectName/

📊 CRUD Operations
Operation	Method	URL	Parameters
Add User	POST	/userlogin?action=add	id, uname, pwd
Delete User	POST	/userlogin?action=delete	id
Update User	POST	/userlogin?action=update	id, uname, pwd
View User	GET	/userlogin?action=view	id
View All	GET	/userlogin?action=viewall	-
Search User	GET	/userlogin?action=search	name
🔒 Security Features
Prepared Statements: All database queries use parameterized queries

Session Management: User session tracking

Input Validation: Client-side form validation

Error Handling: Comprehensive exception handling

📸 Screenshots
(Add your application screenshots here)

🎓 Learning Outcomes
Java Servlet programming

MySQL database integration

CRUD operations implementation

Web application security

Session and cookie management

Tomcat server deployment

🤝 Contributing
Fork the repository

Create a feature branch (git checkout -b feature/AmazingFeature)

Commit changes (git commit -m 'Add some AmazingFeature')

Push to branch (git push origin feature/AmazingFeature)

Open a Pull Request

📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

👨‍💻 Author
SANTHOSH M

LinkedIn: www.linkedin.com/in/santhoshmuthukumaran

🙏 Acknowledgments
Java Servlet Documentation

MySQL Official Documentation

Apache Tomcat Community

NetBeans IDE Team

⭐ Star this repo if you find it useful! ⭐

Quick Start Commands
bash
# Clone repository
git clone [https://github.com/yourusername/UserManagementSystem.git](https://github.com/Santhoshabi473/User-Verification-Page)

# Import into NetBeans/Eclipse
# Configure Tomcat server
# Run on localhost
Support
For support, santhosh473abi@gmail.com or create an issue in the repository.
