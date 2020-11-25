package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.User;

public class UserDao {

	// private DataSource dataSource;
	// private JdbcContext jdbcContext;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		// this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<User> userRowMapper =
		new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setPassword(resultSet.getString("password"));
				return user;
			}
		};

	// public void setJdbcContext(JdbcContext jdbcContext) { this.jdbcContext = jdbcContext; }

	public void add(final User user) throws SQLException {
		this.jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
		// this.jdbcContext.executeSql("insert into users (id, name, password) values (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}

	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from users");

		/*
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				return connection.prepareStatement("delete from users");
			}
		});
		*/
		// this.jdbcContext.executeSql("delete from users");
	}

	public User get(String id) throws SQLException {

		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, this.userRowMapper);

		/*Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
			if (user == null)
				throw new EmptyResultDataAccessException(1);
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}*/
	}

	public int getCountRow() {
		return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);

		/*
			return this.jdbcTemplate.query(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                return connection.prepareStatement("select count(*) from users");
	            }
	        }, new ResultSetExtractor<Integer>() {
	            @Override
	            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
	                resultSet.next();
	                return resultSet.getInt(1);
	            }
			});
		*/

		/*
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				c = dataSource.getConnection();
				ps = c.prepareStatement("select count(*) from users");
				rs = ps.executeQuery();
				rs.next();
				return rs.getInt(1);
			} catch (SQLException e) {
				throw e;
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {}
				}
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
		*/
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userRowMapper);
	}
}



