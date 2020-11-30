import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This ShowEmployeeInfoByID class is to query the employees table in your Oracle database for the employee full name, email and 
 * salary by given employee ID using Oracle OCI database driver to implement the connection
 * @author Yuhang Zhao, student#=150467199
 *
 */
public class ShowEmployeeInfoByID {

	public static void main(String[] args) {
		
		//Use Oracle OCI database driver to implement the connection
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		//String ociDriverConnect = "jdbc:oracle:oci:@//myoracle12c.senecacollege.ca:1521/neptune";
		String ociDriverConnect = "jdbc:oracle:oci:@//myoracle12c.senecacollege.ca:1521/oracle12c";
		
		
		//Constructing the String to call the Store Procedure with 5 Parameter Place Holders 
		String getEMPLOYEEByEmployeeIdSql = "{call getEMPLOYEEByEmployeeId(?,?,?,?,?)}";
		
		try {
			Class.forName(OracleInfo.DRIVER_CLASS_MYSQL);
			//Class.forName(OracleInfo.DRIVER_CLASS_MYSQL_OCI);
			connection = DriverManager.getConnection(ociDriverConnect, OracleInfo.U, OracleInfo.P);
			
			callableStatement = connection.prepareCall(getEMPLOYEEByEmployeeIdSql);
			
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter the employee ID that you want to query: ");
			int ID = input.nextInt();
			input.close();
			
			callableStatement.setInt(1, ID);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.NUMERIC);
			
			// execute getEMPLOYEEByEmployeeId store procedure
			callableStatement.executeUpdate();
			
			String firstName = callableStatement.getString(2);
			String lastName = callableStatement.getString(3);
			String email = callableStatement.getString(4);
            BigDecimal salary = callableStatement.getBigDecimal(5);
            
            System.out.println("Employee ID : " + ID);
            System.out.println("Full name : " + firstName +" "+ lastName);
			System.out.println("Email : " + email);
			System.out.println("Salary : " + salary);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}

				if (connection != null) {
					connection.close();
				}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
	}

}
