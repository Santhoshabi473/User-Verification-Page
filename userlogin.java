import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/userlogin")
public class userlogin extends HttpServlet {
    
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/sys", "root", "santhosh010624@BI" );
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect("index.html");
            return;
        }
        
        switch (action) {
            case "add":
                addUser(request, response, out);
                break;
            case "delete":
                deleteUser(request, response, out);
                break;
            case "update":
                updateUser(request, response, out);
                break;
            case "view":
                viewUser(request, out);
                break;
            case "viewall":
                viewAllUsers(out);
                break;
//            case "search":
//                searchUser(request, out);
//                break;
            default:
                response.sendRedirect("index.html");
        }
    }                           

    //  ADD USER 
    private void addUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String userid = request.getParameter("id");
        String uname = request.getParameter("uname");
        String password = request.getParameter("pwd");
        
        try {
            Connection conn = getConnection();
            String query = "INSERT INTO userlogin (id, name, userpassword) VALUES (?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            pstmt.setString(2, uname);
            pstmt.setString(3, password);
            
            int rowsInserted = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            
            showSuccessPopup(response, "User Added Successfully!", "index.html");
            
        } catch (Exception ex) {
           
            showErrorPopup(response, "Error: " + ex.getMessage(), "index.html");
        }
    }
    
    // DELETE USER 
    private void deleteUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String userid = request.getParameter("id");
        
        try {
            Connection conn = getConnection();
            String query = "DELETE FROM userlogin WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            
            int rowsDeleted = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            if (rowsDeleted > 0) {
                
                showSuccessPopup(response, "User Deleted Successfully!", "index.html");
            } else {
              
                showErrorPopup(response, "User Not Found!", "index.html");
            }
            
        } catch (Exception ex) {
            showErrorPopup(response, "Error: " + ex.getMessage(), "index.html");
        }
    }
    
    //  UPDATE USER 
    private void updateUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String userid = request.getParameter("id");
        String uname = request.getParameter("uname");
        String password = request.getParameter("pwd");
        
        try {
            Connection conn = getConnection();
            String query = "UPDATE userlogin SET name = ?, userpassword = ? WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, uname);
            pstmt.setString(2, password);
            pstmt.setString(3, userid);
            
            int rowsUpdated = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            if (rowsUpdated > 0) {
                
                showSuccessPopup(response, "User Updated Successfully!", "index.html");
            } else {
               
                showErrorPopup(response, "User Not Found for Update!", "index.html");
            }
            
        } catch (Exception ex) {
            showErrorPopup(response, "Error: " + ex.getMessage(), "index.html");
        }
    }
    
    //  VIEW SINGLE USER
    private void viewUser(HttpServletRequest request, PrintWriter out) {
        String userid = request.getParameter("id");
        
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM userlogin WHERE id = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                out.println("User Found:<br>");
                out.println("ID: " + rs.getString("id") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Password: " + rs.getString("userpassword") + "<br>");
                out.println("<br><a href='index.html'>Back to Main Menu</a>");
            } else {
                out.println("User Not Found<br>");
                out.println("<a href='index.html'>Back to Main Menu</a>");
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<br><a href='index.html'>Back to Main Menu</a>");
        }
    }
    
    //  VIEW ALL USERS
    private void viewAllUsers(PrintWriter out) {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM userlogin";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            out.println("All Users:<br><br>");
            while (rs.next()) {
                found = true;
                out.println("ID: " + rs.getString("id"));
                out.println(" | Name: " + rs.getString("name"));
                out.println(" | Password: " + rs.getString("userpassword"));
                out.println("<br>");
            }
            
            if (!found) {
                out.println("No Users in Database<br>");
            }
            
            out.println("<br><a href='index.html'>Back to Main Menu</a>");
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<br><a href='index.html'>Back to Main Menu</a>");
        }
    }
    
 /*   
    // 6. SEARCH USER
    private void searchUser(HttpServletRequest request, PrintWriter out) {
        String searchName = request.getParameter("name");
        
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM userlogin WHERE name LIKE ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + searchName + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            out.println("Search Results:<br><br>");
            while (rs.next()) {
                found = true;
                out.println("ID: " + rs.getString("id"));
                out.println(" | Name: " + rs.getString("name"));
                out.println(" | Password: " + rs.getString("userpassword"));
                out.println("<br>");
            }
            
            if (!found) {
                out.println("No Users Found<br>");
            }
            
            out.println("<br><a href='index.html'>Back to Main Menu</a>");
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception ex) {
            out.println("Error: " + ex.getMessage());
            out.println("<br><a href='index.html'>Back to Main Menu</a>");
        }
    }
    */
    
    // HELPER METHOD
    private void showSuccessPopup(HttpServletResponse response, String message, String redirectPage) throws IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");  // Success pop-up
        out.println("window.location.href = '" + redirectPage + "';"); // Redirect
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }
    
    // HELPER METHOD
    private void showErrorPopup(HttpServletResponse response, String message, String redirectPage) 
            throws IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");  // Error pop-up
        out.println("window.location.href = '" + redirectPage + "';"); // Redirect
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}