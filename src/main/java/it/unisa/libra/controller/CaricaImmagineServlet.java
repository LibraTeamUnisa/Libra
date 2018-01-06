package it.unisa.libra.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class CaricaImmagineServlet
 */
@WebServlet("/CaricaImmagineServlet")
@MultipartConfig
public class CaricaImmagineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaImmagineServlet() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("fileinput");
		String realPath = getServletContext().getRealPath("/");
		File dir = new File(realPath + "images");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(realPath + "/images/" + filePart.getSubmittedFileName());
		InputStream fileStream = filePart.getInputStream();
		Files.copy(fileStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("profilo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
