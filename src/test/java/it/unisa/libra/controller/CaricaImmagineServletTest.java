package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class CaricaImmagineServletTest {


  private IUtenteDao utentedao;

  private Utente utente;

  private CaricaImmagineServlet servlet;

  private HttpServletRequest request;

  private HttpServletResponse response;

  private HttpSession session;

  private ServletContext context;

  private ServletConfig config;

  private PrintWriter responseWriter;
  private FileUtils util;

  private File path;


  private String file = "testBase64";
  private String email = "testImgFile";
  private String emailImgInesistente = "emailInesistente";

  @Before
  public void setUp() throws Exception {
    utentedao = mock(IUtenteDao.class);
    utente = mock(Utente.class);
    servlet = new CaricaImmagineServlet();
    response = mock(HttpServletResponse.class);
    request = mock(HttpServletRequest.class);
    session = mock(HttpSession.class);
    context = mock(ServletContext.class);
    config = mock(ServletConfig.class);
    responseWriter = mock(PrintWriter.class);
    util = mock(FileUtils.class);
    path = mock(File.class);
    servlet.init(config);
    when(request.getSession()).thenReturn(session);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void terDown() throws Exception {}

  @Test
  public void visualizzazioneOk() throws ServletException, IOException {
    when(request.getParameter("action")).thenReturn("mostra");
    when(request.getParameter("email")).thenReturn(emailImgInesistente);
    when(utentedao.findById(Utente.class, emailImgInesistente)).thenReturn(utente);
    servlet.setUtenteDao(utentedao);
    servlet.doGet(request, response);
    String str = null;
    // so che lancera' eccezione e ritornera' null perche' non esiste il file email.png
    verify(response.getWriter()).write(str);
  }

  @Test
  public void visualizzazioneError() throws ServletException, IOException {
    when(request.getParameter("action")).thenReturn("mostra");
    when(request.getParameter("email")).thenReturn("");
    servlet.doGet(request, response);
    verify(response.getWriter()).write("errore");
  }

  @Test
  public void caricaOk() throws ServletException, IOException {
    when(request.getParameter("file")).thenReturn(file);
    Object mailObj;
    String mailStr = email;
    mailObj = mailStr;
    when(session.getAttribute("utenteEmail")).thenReturn(mailObj);
    when(utentedao.findById(Utente.class, mailStr)).thenReturn(utente);
    servlet.setUtenteDao(utentedao);
    servlet.doGet(request, response);
    verify(response.getWriter()).write("Salvataggio riuscito con successo");
  }

}
