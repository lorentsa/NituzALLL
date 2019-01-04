package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeOrPayModel implements IModel
{
    @Override
    public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Users.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void payCash(Message message){
        String sql = "INSERT INTO Messages(id, sender, reciever ,seen, vacation_ID_source,vacation_ID_dest, kind) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(message.getId()));
            pstmt.setString(2, message.getSender());
            pstmt.setString(3, message.getReciever());
            pstmt.setString(4, String.valueOf(message.getSeen()));
            pstmt.setString(5, String.valueOf(message.getVacationIDSource()));
            pstmt.setString(6, String.valueOf(message.getVacationIDDest()));
            pstmt.setString(7, String.valueOf(message.getKind()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeVacation(Message message){
        String sql = "INSERT INTO Messages(id, sender, reciever ,seen, vacation_ID_source, vacation_ID_dest, kind) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(message.getId()));
            pstmt.setString(2, message.getSender());
            pstmt.setString(3, message.getReciever());
            pstmt.setString(4, String.valueOf(message.getSeen()));
            pstmt.setString(5, String.valueOf(message.getVacationIDSource()));
            pstmt.setString(6, String.valueOf(message.getVacationIDDest()));
            pstmt.setString(7, String.valueOf(message.getKind()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
