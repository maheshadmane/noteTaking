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


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public UpdateServlet() {
        super();
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			 	String title=request.getParameter("title");
			 	String content=request.getParameter("content");
			 	int nId=Integer.parseInt(request.getParameter("noteId").trim());
			 	
			 	System.out.println(title);
			 	System.out.println(content);
			 	System.out.println(nId);
//			 	Session s=FactoryProvider.getFactory().openSession();
//			 	Transaction tx = s.beginTransaction();
			 	
			 	Session s=FactoryProvider.getFactory().openSession();
			 	Transaction tx = s.beginTransaction();
			 	
			 	Note note=s.get(Note.class, nId);
			 	
			 	note.setTitle(title);
			 	note.setContent(content);
			 	note.setAddeDate(new Date());
			 	PrintWriter o = response.getWriter();
			 	o.print(note);
			 	tx.commit(); 
			 	s.close();
//			 	
			 	response.sendRedirect("all_notes.jsp");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
