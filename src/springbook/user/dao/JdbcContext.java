package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import springbook.user.domain.User;

public class JdbcContext {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void executeSql(final String query, final String ...arg) throws SQLException {
		workWithStatementStrategy(
			new StatementStrategy() {
				@Override
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement(query);
					int i = 1;
					for (String a:arg) {
						ps.setString(i, a);
						i++;
					}
					return ps;
				}
			}
		);
	}

	public void workWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = dataSource.getConnection();
			ps = statementStrategy.makePreparedStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {}
			}
		}
	}

}
