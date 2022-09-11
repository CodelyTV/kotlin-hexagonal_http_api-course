package shared.database

import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.Statement
import org.springframework.beans.factory.annotation.Autowired

class PostgresTestUtils {

    @Autowired
    private lateinit var datasource: HikariDataSource

    fun clean() {
        val connection: Connection = getConnection()
        val statement = connection.createStatement()

        val tables: List<String> = getTables(statement)
        truncate(tables, statement)

        datasource.evictConnection(connection)
        connection.close()
    }

    private fun getTables(statement: Statement): List<String> {
        val tables: MutableList<String> = java.util.ArrayList()
        val rs = statement.executeQuery(
            """
            SELECT table_schema, table_name
            FROM information_schema.tables WHERE
            table_schema = 'public' AND table_name NOT IN ('flyway_schema_history')
            """
        )
        while (rs.next()) {
            tables.add(rs.getString("table_schema") + "." + rs.getString("table_name"))
        }
        return tables
    }

    private fun truncate(tables: List<String>, statement: Statement) {
        for (p in tables) {
            statement.addBatch("TRUNCATE TABLE $p;")
        }
        statement.executeBatch()
    }

    private fun getConnection(): Connection {
        return datasource.connection
    }
}
