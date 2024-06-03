package com.codegym.firstjavaweb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Set UTF-8 encoding for the response
        response.setCharacterEncoding("UTF-8");

        // Set content type for HTML response
        response.setContentType("text/html; charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
        try {
            String filePath = "C:\\Users\\anhnb\\Downloads\\getCourseDetail.json";
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(fileContent);
            JSONArray sections = obj.getJSONObject("result").getJSONArray("sections");

            StringBuilder markdownTable = new StringBuilder("| Section | Activity | Duration |\n| --- | --- | --- |\n");
            int totalDuration = 0;

            for (int i = 0; i < sections.length(); i++) {
                JSONObject section = sections.getJSONObject(i);
                String sectionTitle = section.optString("title", "Default Title");
                JSONArray activities = section.getJSONArray("activities");

                for (int j = 0; j < activities.length(); j++) {
                    JSONObject activity = activities.getJSONObject(j);
                    String activityTitle = activity.optString("title", "Default Title");
                    int duration = activity.optInt("durations", 0);

                    totalDuration += duration;
                    markdownTable.append("| ").append(sectionTitle).append(" | ").append(activityTitle).append(" | ").append(duration).append(" |<br>");
                }
            }

            markdownTable.append("| Total | | ").append(totalDuration).append(" |<br>");
            out.println(markdownTable.toString());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}