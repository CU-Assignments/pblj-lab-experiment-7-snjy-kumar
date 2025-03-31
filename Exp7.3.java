### Instructions for Java JDBC MVC Student Management System  

1. **Setup MySQL Database:**  
   - Install and start MySQL.  
   - Create a database (e.g., `StudentDB`).  
   - Create a table:  
     ```sql
     CREATE TABLE Student (
         StudentID INT PRIMARY KEY,
         Name VARCHAR(100),
         Department VARCHAR(50),
         Marks DOUBLE
     );
     ```

2. **Update Database Credentials:**  
   - Modify `URL`, `USER`, and `PASSWORD` in the code to match your MySQL database credentials.

3. **Add MySQL JDBC Driver:**  
   - Download and add `mysql-connector-java.jar` to your project's classpath.

4. **Compile and Run the Program:**  
   - Compile: `javac StudentManagementApp.java`  
   - Run: `java StudentManagementApp`  

5. **Menu-Driven Operations:**  
   - **Add Student:** Enter StudentID, Name, Department, and Marks.  
   - **View Students:** Displays all students in the table.  
   - **Update Student:** Modify Name, Department, or Marks using StudentID.  
   - **Delete Student:** Remove a student using StudentID.  
   - **Exit:** Quit the program.

6. **Transaction Handling:**  
   - Ensures data integrity by using `conn.setAutoCommit(false)` and `conn.commit()`.  
   - Rolls back changes in case of errors.

7. **Verify Database Changes:**  
   - Use `SELECT * FROM Student;` in MySQL to confirm modifications.



==============================================================================================================================
   Code
==============================================================================================================================

   class Student { 
int studentID; 
String name; 
String department; 
double marks; 
Student(int studentID, String name, String department, double marks) { 
this.studentID = studentID; 
this.name = name; 
this.department = department; 
this.marks = marks; 
} 
} 
import java.sql.*; 
import java.util.*; 
class StudentController { 
private static final String URL = "jdbc:mysql://localhost:3306/student"; 
private static final String USER = "root"; 
private static final String PASSWORD = "Mysql@1234"; 
public void addStudent(Student student) throws SQLException { 
String query = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES 
(?, ?, ?, ?)"; 
try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
PreparedStatement ps = conn.prepareStatement(query)) { 
ps.setInt(1, student.studentID); 
ps.setString(2, student.name); 
ps.setString(3, student.department); 
ps.setDouble(4, student.marks); 
ps.executeUpdate(); 
System.out.println("Student added successfully."); 
} 
} 
public void viewStudents() throws SQLException { 
String query = "SELECT * FROM Student"; 
try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
Statement stmt = conn.createStatement(); 
ResultSet rs = stmt.executeQuery(query)) { 
while (rs.next()) { 
System.out.println("ID: " + rs.getInt("StudentID") + ", Name: " + rs.getString("Name") 
+ 
rs.getDouble("Marks")); 
} 
} 
} 
", Department: " + rs.getString("Department") + ", Marks: " + 
public void updateStudent(int id, double marks) throws SQLException { 
String query = "UPDATE Student SET Marks = ? WHERE StudentID = ?"; 
try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
PreparedStatement ps = conn.prepareStatement(query)) { 
ps.setDouble(1, marks); 
ps.setInt(2, id); 
ps.executeUpdate(); 
System.out.println("Student record updated successfully."); 
} 
} 
public void deleteStudent(int id) throws SQLException { 
String query = "DELETE FROM Student WHERE StudentID = ?"; 
try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
PreparedStatement ps = conn.prepareStatement(query)) { 
ps.setInt(1, id); 
ps.executeUpdate(); 
System.out.println("Student record deleted successfully."); 
} 
} 
} 
// View (Menu-Driven) 
public class StudentApp { 
public static void main(String[] args) { 
StudentController controller = new StudentController(); 
Scanner sc = new Scanner(System.in); 
boolean exit = false; 
while (!exit) { 
System.out.println("1. Add Student\n2. View Students\n3. Update Student\n4. Delete 
Student\n5. Exit"); 
System.out.print("Choose an option: "); 
int choice = sc.nextInt(); 
try { 
switch (choice) { 
case 1 -> { 
System.out.print("Enter ID: "); 
int id = sc.nextInt(); 
sc.nextLine(); 
System.out.print("Enter Name: "); 
String name = sc.nextLine(); 
System.out.print("Enter Department: "); 
String dept = sc.nextLine(); 
System.out.print("Enter Marks: "); 
double marks = sc.nextDouble(); 
controller.addStudent(new Student(id, name, dept, marks)); 
} 
case 2 -> controller.viewStudents(); 
case 3 -> { 
System.out.print("Enter Student ID to update: "); 
int id = sc.nextInt(); 
System.out.print("Enter New Marks: "); 
double marks = sc.nextDouble(); 
controller.updateStudent(id, marks); 
} 
case 4 -> { 
System.out.print("Enter Student ID to delete: "); 
int id = sc.nextInt(); 
controller.deleteStudent(id); 
} 
case 5 -> exit = true; 
default -> System.out.println("Invalid option. Try again."); 
} 
} catch (SQLException e) { 
e.printStackTrace(); 
} 
} 
} 
}

==============================================================================================================================
