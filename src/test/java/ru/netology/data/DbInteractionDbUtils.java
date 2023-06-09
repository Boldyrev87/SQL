package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteractionDbUtils {
    public DbInteractionDbUtils() {

    }

    public static String getVerificationCode() {
        val codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass")) {
            val code = runner.query(conn, codeSQL, new ScalarHandler<String>());

            return code;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void clearTables() {
        val deleteCode = "DELETE FROM auth_codes";
        val deleteTransaction = "DELETE FROM card_transactions";
        val deleteCard = "DELETE FROM cards";
        val deleteUser = "DELETE FROM users";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, deleteCode);
            runner.update(conn, deleteTransaction);
            runner.update(conn, deleteCard);
            runner.update(conn, deleteUser);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
