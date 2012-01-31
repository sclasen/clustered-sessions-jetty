package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SessionObj s = (SessionObj) req.getSession().getAttribute("x");
        if (s == null) {
            s = new SessionObj();
        }
        String weight = req.getParameter("weight");
        if (weight != null) {
            System.out.println("weight:" + weight);
            int w = Integer.parseInt(weight) * 1024;
            byte[] heavy = new byte[w];
            int set = new Random().nextInt(w);
            heavy[set] = (byte) set;
            s.weight = heavy;

        }
        Long current = System.currentTimeMillis();
        Long last = s.last;
        Integer count = s.count;
        s.count += 1;
        s.last = current;
        req.getSession().setAttribute("x", s);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"count\":" + count + "}");
        writer.close();
    }

}