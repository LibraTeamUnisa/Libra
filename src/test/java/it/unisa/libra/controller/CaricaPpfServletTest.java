package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;

import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaricaPpfServletTest {

  CaricaPpfServlet servlet = new CaricaPpfServlet();
  HttpServletRequest request;
  HttpServletResponse response;
  PrintWriter responseWriter;

  IUtenteDao utenteDao;
  ITutorInternoDao tutorInternoDao;
  IProgettoFormativoDao propostaDao;
  Part filePart;
  String fileName;
  File file;
  Path path;
  InputStream fileStream;
  ProgettoFormativo proposta;
  Studente studente;
  String ambitoA = "Informatica";
  String ambitoB = "Medicina";
  String ambitoControl = " ";

  List<ProgettoFormativo> listaProposte;

  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
    responseWriter = mock(PrintWriter.class);
    utenteDao = mock(IUtenteDao.class);
    propostaDao = mock(IProgettoFormativoDao.class);
    filePart = mock(Part.class);
    file = mock(File.class);
    path = mock(Path.class);
    fileStream = mock(InputStream.class);
    listaProposte = mock(List.class);
    tutorInternoDao = mock(ITutorInternoDao.class);
    studente = mock(Studente.class);
    proposta = mock(ProgettoFormativo.class);
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

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(request.getParameter("studente")).thenReturn("1");
    when(file.toPath()).thenReturn(path);
    when(request.getParameter("note")).thenReturn("il progetto è bello");
    when(request.getParameter("ambito")).thenReturn(ambitoA);
    when(request.getParameter("ambitoControl")).thenReturn(ambitoControl);
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(new ProgettoFormativo());
    when(filePart.getInputStream()).thenReturn(fileStream);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("ok");
  }
  
  public void caricaPropostaDaAziendaErrato() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(request.getParameter("studente")).thenReturn("1");
    when(file.toPath()).thenReturn(path);
    when(request.getParameter("note")).thenReturn("il progetto è bello");
    when(request.getParameter("ambito")).thenReturn(ambitoB);
    when(request.getParameter("ambito")).thenReturn(ambitoControl);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("ambito non valido");
  }

  @Test
  public void caricaPropostaDaStudente() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Studente");
    listaProposte.add(new ProgettoFormativo());
    when(propostaDao.findAll(ProgettoFormativo.class)).thenReturn(listaProposte);
    when(proposta.getStudente()).thenReturn(studente);
    when(studente.getUtenteEmail()).thenReturn("alfredo@unisa.it");
    when(proposta.getStato()).thenReturn(1);
    when(proposta.getDocumento()).thenReturn("documento.txt");
    when(filePart.getInputStream()).thenReturn(fileStream);
    when(file.toPath()).thenReturn(path);
    when(request.getParameter("note")).thenReturn("il progetto è bello");
    when(request.getParameter("tutorInterno")).thenReturn("giovanni@unisa.it");
    when(tutorInternoDao.findById(TutorInterno.class,"giovanni@unisa.it")).thenReturn(new TutorInterno());
    when(listaProposte.size()).thenReturn(1);
    when(listaProposte.get(0)).thenReturn(proposta);
    //servlet.setUtenteDao(utenteDao, propostaDao, tutorInternoDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaStudenteErrato() throws IOException, ServletException {

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Studente");
    listaProposte.add(new ProgettoFormativo());
    when(propostaDao.findAll(ProgettoFormativo.class)).thenReturn(listaProposte);
    when(proposta.getStudente()).thenReturn(studente);
    when(studente.getUtenteEmail()).thenReturn("alfredo@unisa.it");
    when(proposta.getStato()).thenReturn(2);
    //servlet.setUtenteDao(utenteDao, propostaDao, tutorInternoDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("errore");
  }

  @Test
  public void caricaPropostaDaTutorInterno() throws IOException, ServletException {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(proposta);
    when(proposta.getStato()).thenReturn(2);
    when(proposta.getDocumento()).thenReturn("documento.txt");
    when(filePart.getInputStream()).thenReturn(fileStream);
    when(file.toPath()).thenReturn(path);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaTutorInternoErrato() throws IOException, ServletException {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(proposta);
    when(proposta.getStato()).thenReturn(3);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("errore");
  }

  @Test
  public void caricaPropostaDaPresidente() throws IOException, ServletException {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(proposta);
    when(proposta.getStato()).thenReturn(3);
    when(proposta.getDocumento()).thenReturn("documento.txt");
    when(filePart.getInputStream()).thenReturn(fileStream);
    when(file.toPath()).thenReturn(path);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("ok");
  }

  @Test
  public void caricaPropostaDaPresidenteErrato() throws IOException, ServletException {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("alfredo@unisa.it");
    when(utenteDao.findById(Utente.class,"alfredo@unisa.it")).thenReturn(new Utente());
    when(request.getPart("file")).thenReturn(filePart);
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getParameter("id")).thenReturn("1");
    when(propostaDao.findById(ProgettoFormativo.class,1)).thenReturn(proposta);
    when(proposta.getStato()).thenReturn(4);
    //servlet.setUtenteDao(utenteDao, propostaDao);
    //servlet.doPost(request, response);
    //verify(response.getWriter()).write("errore");
  }
}
