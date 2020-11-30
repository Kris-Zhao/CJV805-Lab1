import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This ShowTotalEmployeeByDeptID class is to obtain the number of employees in a department by department id through calling PL/SQL 
 * stored procedure total_emp_by_dept_id. 
 * Using Oracle Thin and OCI database driver to implement the connection respectively. 
 * @author Yuhang Zhao, student#=150467199
 *
 */
public class ShowTotalEmployeeByDeptID {
	
	public static void main(String[] args) throws SQLException {
		
		//Using Oracle Thin to implement the connection. 
		System.out.println("The 1st part is using Oracle thin database driver to implement the connection. ");
		try {
			oracle_storedProcedure(OracleInfo.DB_THIN_CONNECTION);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//using OCI database driver to implement the connection.
		System.out.println("The 2nd part is using Oracle OCI database driver to implement the connection. ");
		try {
			oracle_storedProcedure(OracleInfo.DB_OCI_CONNECTION);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	//get connection to the Oracle database
	public static Connection getDBConnection(String connectionDriver) {
		Connection dbConnection = null;
		try {
			Class.forName(OracleInfo.DRIVER_CLASS_MYSQL);
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			dbConnection = DriverManager.getConnection(connectionDriver, OracleInfo.U, OracleInfo.P);
			return dbConnection;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	
	
	/**
	 * Parameter "connectionDriver" represents THIN and OCI driver's URL.
	 * Then using callablestatements and predefined PL/SQL procedure "total_emp_by_dept_id" to obtain the number of employees 
	 * in a department by department id.
	 * @param connectionDriver
	 * @throws SQLException
	 */
	public static void oracle_storedProcedure(String connectionDriver) throws SQLException {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		String getNumOfEmpsByDepIdSql = "{call total_emp_by_dept_id(?, ?)}";
		
		try {
			dbConnection = getDBConnection(connectionDriver);
			callableStatement = dbConnection.prepareCall(getNumOfEmpsByDepIdSql);
			
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter the department ID that you want to query: ");
			int ID = input.nextInt();
			
			
			callableStatement.setInt(1, ID);
			callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
			
			callableStatement.executeUpdate();
			
			int numOfEmpsByDepId = callableStatement.getInt(2);
			
			System.out.println("The number of employees in department "+ ID + " is " + numOfEmpsByDepId + ".");
			System.out.println();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
			if(dbConnection != null) {
				dbConnection.close();
			}
		}
 	}
	
}
