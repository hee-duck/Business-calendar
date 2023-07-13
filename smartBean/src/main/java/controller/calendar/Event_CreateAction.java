package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.event.EventDao;
import model.event.EventRequestDto;
import model.user.UserRequestDto;

/**
 * Servlet implementation class Event_CreateAction
 */
@WebServlet("/Event_CreateAction")
public class Event_CreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_CreateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    EventDao eventDao = EventDao.getInstance();
	    
	    try {
	        int calendarCode = Integer.parseInt(request.getParameter("calendarCode"));
	        int taskNo = Integer.parseInt(request.getParameter("taskNo"));
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String taskTitle = request.getParameter("tastTitle");
	        String teakContent = request.getParameter("teakContent");
	        String startDate = request.getParameter("startDate");
	        String endDate = request.getParameter("endDate");
	        String all_day = request.getParameter("all_day");

	        if(name == null || email == null || taskTitle == null || teakContent == null || startDate == null || endDate == null || all_day == null) {
	            // One or more parameters were missing
	            response.getWriter().write("{\"status\": \"error\", \"message\": \"Missing parameters.\"}");
	            return;
	        }
	        
	        EventRequestDto event = new EventRequestDto(0, calendarCode, taskNo, name, email, taskTitle, teakContent, startDate, endDate, all_day);
	        boolean result = eventDao.createEvent(event);
	        
	        if(result) {
	            response.getWriter().write("{\"상태\": \"event create 성공\"}");
	        } else {
	            response.getWriter().write("{\"상태\": \"event create 실패\", \"message\": \"event create 실패\"}");
	        }
	    } catch(NumberFormatException e) {
	        response.getWriter().write("{\"status\": \"error\", \"message\": \"Invalid number format.\"}");
	    }

	}


}
