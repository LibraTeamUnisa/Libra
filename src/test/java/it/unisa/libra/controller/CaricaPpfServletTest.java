package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;

import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CaricaPpfServletTest {

  CaricaPpfServlet servlet = new CaricaPpfServlet();
  HttpServletRequest request;
  HttpServletResponse response;
  PrintWriter responseWriter;

  IUtenteDao utenteDao;
  ITutorInternoDao tutorInternoDao;
  IProgettoFormativoDao propostaDao;
  String fileName;
  File path;
  ProgettoFormativo proposta;
  Studente studente;
  FileUtils util;
  String ambitoA = "Informatica";
  String ambitoB = "Medicina";
  String ambitoControl = " ";

  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
    responseWriter = mock(PrintWriter.class);
    utenteDao = mock(IUtenteDao.class);
    propostaDao = mock(IProgettoFormativoDao.class);
    path = mock(File.class);
    tutorInternoDao = mock(ITutorInternoDao.class);
    studente = mock(Studente.class);
    proposta = mock(ProgettoFormativo.class);
    util = mock(FileUtils.class);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void tearDown() {
    request = null;
    response = null;
    responseWriter = null;
  }

  @Test
  public void caricaPropostaDaAzienda() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("android@google.com");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(request.getParameter("file")).thenReturn("file");
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("note")).thenReturn("il progetto è bello");
    when(request.getParameter("ambito")).thenReturn(ambitoA);
    when(request.getParameter("ambitoControl")).thenReturn(ambitoControl);
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(new ProgettoFormativo());
    servlet.setUtenteDao(utenteDao, propostaDao);
    servlet.doPost(request, response);
    verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaStudente() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("android@google.com");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Studente");
    when(request.getParameter("file")).thenReturn("file");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(new ProgettoFormativo());
    when(request.getParameter("note")).thenReturn("il progetto è bello");
    when(request.getParameter("tutorInterno")).thenReturn("pippo@unisa.it");
    when(tutorInternoDao.findById(TutorInterno.class, "pippo@unisa.it")).thenReturn(new TutorInterno());
    servlet.setUtenteDao(utenteDao, propostaDao, tutorInternoDao);
    servlet.doPost(request, response);
    verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaTutorInterno() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("android@google.com");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getParameter("file")).thenReturn("base64");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(new ProgettoFormativo());
    servlet.setUtenteDao(utenteDao, propostaDao);
    servlet.doPost(request, response);
    verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaPresidente() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("android@google.com");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getParameter("file")).thenReturn("file");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(new ProgettoFormativo());
    servlet.setUtenteDao(utenteDao, propostaDao);
    servlet.doPost(request, response);
    verify(response.getWriter()).write("ok");
  }
}
