package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductionDBConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager
			               .getConnection("jdbc:mysql://localhost/jay?allowPublicKeyRetrieval=true&useSSL=false", "han5517", "1234");
		return c;
	}

}
