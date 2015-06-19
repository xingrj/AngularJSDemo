package com.tdsecurities.jetty.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tdsecurities.jetty.CMPPnL;
import com.tdsecurities.jetty.CMPPnLDao;

public class CMPPnLServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CMPPnLDao pnlDao = new CMPPnLDao();
		List<CMPPnL> pnls = pnlDao.retrievePnL();
		System.out.println("pnls: " + pnls.size() + pnls.get(0));
		response.setContentType("application/json");

		Gson gson = new Gson();
		String json = "{\"pnls\" : " + gson.toJson(pnls) + "}";
		
		//output the json in servlet
//		PrintWriter out = response.getWriter();
//		out.print(json);
//		out.flush();

		//output JSON in jsp page.
		request.setAttribute("json", json);
	
		//do not use response.sendRedirect() which is client side redirection and will lose request attributes.
		request.getRequestDispatcher("pnlResult.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
