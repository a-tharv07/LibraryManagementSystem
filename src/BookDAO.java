import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {

    public static void addBook(String title, String author) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBook(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Book removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> listBooks() {
        ArrayList<String> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(rs.getInt("id") + ": " + rs.getString("title") + " by " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
