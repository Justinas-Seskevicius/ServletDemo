package com.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countryCode = request.getParameter("countryCode");
		String bankCode = request.getParameter("bankCode");
		boolean errors = false;
		
		if (!BankCodeFormatter.isCountryCodeValid(countryCode)) {
			request.setAttribute("countryCodeError", true);
			errors = true;
		}
		
		if (!BankCodeFormatter.isBankCodeValid(bankCode)) {
			request.setAttribute("bankCodeError", true);
			errors = true;
		}
		
		if(!errors) {
			request.setAttribute("formatedBankCode", BankCodeFormatter.formatBankCode(countryCode, bankCode));
		}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
