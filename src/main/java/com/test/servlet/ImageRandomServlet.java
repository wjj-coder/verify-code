package com.test.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageRandomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ImageRandomServlet() {
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("verify code..............start");
		response.setHeader("Pragam", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		response.setCharacterEncoding("utf-8");
		int width = 80, height = 26;
		//获取画板
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		//输出流
		OutputStream os = response.getOutputStream();
		//获取画笔
		Graphics g = image.getGraphics();
		//设置当前画笔颜色
		g.setColor(getRandColor(200, 250));
		//设置图片大小
		g.fillRect(0, 0, width, height);
		//设置字体样式大小
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		//设置当前画笔颜色
		g.setColor(getRandColor(150, 200));
		Random random = new Random();
		//划模糊线
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(10);
			int yl = random.nextInt(10);
			g.drawLine(x, y, xl, yl);
		}
		String sRand = "";
		//设置每个验证码的颜色
		for (int j = 0; j < 4; j++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 16 * j + 6, 20);
		}
		System.out.println("验证码:" + sRand);
		//把验证码 存到session
		request.getSession().setAttribute("rand", sRand);
		g.dispose();
		ImageIO.write(image, "jpeg", os);
		os.flush();
		os.close();
		response.flushBuffer();
		System.out.println(".........end");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	//生产颜色
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
