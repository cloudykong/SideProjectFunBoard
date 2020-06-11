package com.microsoft.kdh.cotroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.kdh.command.CalculatorCommand;
import com.microsoft.kdh.command.CalculatorUICommand;
import com.microsoft.kdh.command.Command;
import com.microsoft.kdh.command.CommandAction;
import com.microsoft.kdh.command.InsertCommand;
import com.microsoft.kdh.command.InsertUICommand;
import com.microsoft.kdh.command.KDHHomeCommand;
import com.microsoft.kdh.command.ListCommand;
import com.microsoft.kdh.command.LoginCommand;
import com.microsoft.kdh.command.LoginUICommand;

/**
 * Servlet implementation class KDHFrontController
 */
@WebServlet("*.kdh")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String ctxp = request.getContextPath();
		String sp = uri.substring(ctxp.length());

		Command com = null;

		if (sp.equalsIgnoreCase("/home.kdh")) {
			com = new KDHHomeCommand();
		} else if (sp.equalsIgnoreCase("/calculatorui.kdh")) {
			com = new CalculatorUICommand();
		} else if (sp.equalsIgnoreCase("/calculator.kdh")) {
			com = new CalculatorCommand();
		} else if (sp.equalsIgnoreCase("/loginui.kdh")) {
			com = new LoginUICommand();
		} else if (sp.equalsIgnoreCase("/insertui.kdh")) {
			com = new InsertUICommand();
		} else if (sp.equalsIgnoreCase("/insert.kdh")) {
			com = new InsertCommand();
		} else if (sp.equalsIgnoreCase("/list.kdh")) {
			com = new ListCommand();
		} else if (sp.equalsIgnoreCase("/loginui.kdh")) {
			com = new LoginUICommand();
		} else if (sp.equalsIgnoreCase("/login.kdh")) {
			com = new LoginCommand();
		} else {
			System.out.println("제공하지 않는 서비스 입니다.");
		}

		if (com != null) {
			CommandAction action = com.execute(request, response);
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			} else {
				request.getRequestDispatcher(action.getWhere()).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}