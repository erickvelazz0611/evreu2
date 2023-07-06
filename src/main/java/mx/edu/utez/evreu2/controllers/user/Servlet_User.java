package mx.edu.utez.evreu2.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.evreu2.models.user.Daouser;
import mx.edu.utez.evreu2.models.user.User;
import mx.edu.utez.evreu2.models.user.Daouser;
import mx.edu.utez.evreu2.models.user.User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "users", urlPatterns = {
        "/user/users",
        "/user/user",
        "/user/user-view",
        "/user/save",
        "/user/user-view-update",
        "/user/update",
        "/user/delete"
}) //endpoints
public class Servlet_User extends HttpServlet {
    private String action;
    private String redirect = "/user/users";
    private String name;
    private String surname;
    private String lastname;
    private String birthday;
    private String email;
    private String id;
    private User userr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Modificacón
        action = req.getServletPath(); //Trae la ultima linea de nuestro url
        switch (action){
            case "/user/users":
                List<User> users = new Daouser().findAll();
                req.setAttribute("users", users);
                redirect = "/views/user/index.jsp";
                break;
            case "/user/user-view":
                // Consultas catalogos
                redirect = "/views/user/create.jsp";
                break;
            case "/user/user-view-update":
                // Modificacion de usuarios
                id = req.getParameter("id");
                userr = new Daouser().findOne(id != null ? Long.parseLong(id) : 0);
                if (userr != null){
                    req.setAttribute("user", userr);
                    redirect = "/views/user/update.jsp";
                }
                else {
                    redirect = "/user/users?result" + false + "&message" + URLEncoder.encode("", StandardCharsets.UTF_8);
                }
                break;
            default:
                System.out.println(action);
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action){
            case "/user/user-view-update":
                String id = req.getParameter("id");
                User User = new Daouser().findOne(
                        id != null ? Long.parseLong(id) : 0
                );
                if (User != null){
                    req.setAttribute("user", User);
                    redirect = "/views/user/update.jsp";
                }else{
                    redirect = "/user/users";
                }
                break;
            case "/user/save":
                name = req.getParameter("name");
                surname = req.getParameter("surname");
                lastname = req.getParameter("lastname");
                birthday = req.getParameter("birthday");
                email = req.getParameter("email");
                //status = req.getParameter("status");
                User user1 = new User(0, name, surname, lastname, birthday, email);
                boolean result = new Daouser().save(user1);
                if (result){
                    redirect = "/user/users?result= " + result
                            + "&message" + URLEncoder.encode("Exito! Usuario registrado correctamente", StandardCharsets.UTF_8);
                }else{
                    redirect = "/user/users?result= " + result
                            + "&message" + URLEncoder.encode("Error :/ Acción no realizada correctamente", StandardCharsets.UTF_8);
                }
                break;
            case "/user/update":
                name = req.getParameter("name");
                surname = req.getParameter("surname");
                lastname = req.getParameter("lastname");
                birthday = req.getParameter("birthday");
                email = req.getParameter("email");
                id = req.getParameter("id");
                User user2 = new User(Long.parseLong(id), name, surname, lastname, birthday, email);
                //boolean result = new Daouser().save(user1);
                if (new Daouser().update(user2)){
                    redirect = "/user/users?result= " + true
                            + "&message" + URLEncoder.encode("Exito! Usuario registrado correctamente", StandardCharsets.UTF_8);
                }else{
                    redirect = "/user/users?result= " + false
                            + "&message" + URLEncoder.encode("Error :/ Acción no realizada correctamente", StandardCharsets.UTF_8);
                }
                break;
            case "/user/delete":
                id = req.getParameter("id");
                if(new Daouser().delete(Long.parseLong(id))){
                    redirect = "/user/users?result= " + true
                            + "&message" + URLEncoder.encode("Exito! Usuario eliminado correctamente", StandardCharsets.UTF_8);
                }else{
                    redirect = "/user/users?result= " + false
                            + "&message" + URLEncoder.encode("Error :/ Acción no realizada correctamente", StandardCharsets.UTF_8);
                }
                redirect = "/user/users";
                break;
            default:
                redirect = "/user/users";
        }
        resp.sendRedirect(req.getContextPath() + redirect);

    }

}

