package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mvc.model.Candidate;

public class DBCandidato extends DBConnection {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DBCandidato(String url, String user, String  password){
        super(url, user, password);
    }

    public boolean insertarcandidate(Candidate candidate) {
        Connection connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO candidato (nombre,cantVotos) values (?,?)");
            preparedStatement.setString(2, candidate.getName());
            preparedStatement.setLong(3, candidate.getNumVotes());

            int result = preparedStatement.executeUpdate();

            return result != 0; // If result is different from 0, return "true".

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

}
