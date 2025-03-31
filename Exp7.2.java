### Instructions to Run the Java CRUD Program:

1. **Setup MySQL Database**
   - Ensure MySQL is installed and running.
   - Create a database and a `Product` table with columns `ProductID`, `ProductName`, `Price`, and `Quantity`.

2. **Update Database Credentials**
   - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

3. **Add MySQL JDBC Driver**
   - Download and add `mysql-connector-java.jar` to your project’s classpath.

4. **Compile and Run the Program**
   - Compile: `javac ProductCRUD.java`
   - Run: `java ProductCRUD`

5. **Menu-Driven Operations**
   - Select options to **Create**, **Read**, **Update**, or **Delete** products.
   - Input values as prompted.

6. **Transaction Handling**
   - Transactions ensure data integrity.
   - If an error occurs, changes are rolled back.

7. **Verify Output**
   - Ensure product records are correctly manipulated in the database.
=============================================================================================================================
   Code
=============================================================================================================================
import java.sql.*; 
class Sql { 
private Connection c; 
private static int numberOfPeople = 1; 
Sql() throws Exception { 
c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Raghav_1"); 
System.out.println("Database connected successfully!"); 
} 
public void addRecord(String name, int number) throws Exception { 
String query = "INSERT INTO java.users VALUES (" +numberOfPeople+",'"+ name + "', " 
+ number + ");"; 
System.out.println("Executing Query: " + query); 
try (Statement s = c.createStatement()) { 
int rowsInserted = s.executeUpdate(query); 
System.out.println("Rows affected: " + rowsInserted); 
if (rowsInserted > 0) { 
System.out.println("Record inserted successfully!"); 
numberOfPeople++; 
} else { 
System.out.println("Insertion failed!"); 
} 
} } 
public void update(int id, String name) throws Exception { 
String query = "UPDATE users SET name='" + name + "' WHERE id=" + id; 
System.out.println("Executing Query: " + query); 
try (Statement s = c.createStatement()) { 
int rowsUpdated = s.executeUpdate(query); 
System.out.println("Rows affected: " + rowsUpdated); 
if (rowsUpdated > 0) { 
System.out.println("Record updated successfully!"); 
} else { 
System.out.println("Update failed! Record not found."); 
} 
} } 
public void readRecords() throws Exception { 
String query = "SELECT * FROM users"; 
System.out.println("Executing Query: " + query); 
try (Statement s = c.createStatement(); ResultSet rs = s.executeQuery(query)) { 
System.out.println("\nUser Records:"); 
boolean foundRecords = false; 
while (rs.next()) { 
foundRecords = true; 
System.out.println("ID: " + rs.getInt("id") + 
", Name: " + rs.getString("name") + 
", Number: " + rs.getInt("number")); 
} 
if (!foundRecords) { 
System.out.println("No records found in the database."); 
} 
} } 
public void closeConnection() throws Exception { 
c.close(); 
System.out.println("Database connection closed."); 
} 
} 
public class CRUD { 
public static void main(String[] args) { 
try { 
Sql db = new Sql(); 
db.addRecord("Raghav", 100); 
db.readRecords(); 
db.update(1, "John Doe"); 
db.readRecords(); // Read again to confirm update 
db.closeConnection(); 
} catch (Exception e) { 
e.printStackTrace(); 
} 
} 
} 


=============================================================================================================================
