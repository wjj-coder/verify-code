package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyCodeServlet() {
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String code = (String) request.getSession().getAttribute("rand");
		String verifyCode = request.getParameter("imgCode");
		PrintWriter pw = response.getWriter();
		System.out
				.println("post 验证 getparam" + verifyCode + " session:" + code);
		if (verifyCode == null || "".equals(verifyCode)) {
			pw.println("验证码为空");
		} else {
			if (verifyCode.equals(code)) {
				pw.println("验证码正确");
			} else {
				pw.println("验证码错误");
			}
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
