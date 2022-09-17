package com.acks.servletpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.acks.entities.Note;
import com.acks.helper.FactoryProvider;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public SaveNoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// title ,content is fetche
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
			
			
			Note note=new Note(title, content, new Date());
			
			
			//save work using Hibernate
			
			Session session = FactoryProvider.getFactory().openSession();
			
			Transaction tx = session.beginTransaction();
			session.save(note);
			
//			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2 style='text-align:center;'>  Note is added Successfuly!</h2>");
			out.println("<h2 style='text-align:center;'><a href='all_notes.jsp'>View all notes</a></h2>");

			tx.commit(); 
			session.close();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
