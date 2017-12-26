package it.unisa.libra.filter;

import it.unisa.libra.util.JspPagesIndex;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FiltroUtente. Garantisce l'accesso alla risorsa richiesta
 * solo se l'utente è loggato.
 * 
 * @see javax.servlet.Filter
 */
public class FiltroUtente implements Filter {

  /** Default constructor. */
  public FiltroUtente() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
	  
    String utenteEmail =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteEmail");
    // l'utente non loggato può accedere ad alcune pagine
    // quindi questo filtro non deve essere applicato a queste pagine
    String path = ((HttpServletRequest) request).getRequestURI();
    if (path.endsWith(JspPagesIndex.ACCESSO_NEGATO) || path.endsWith(JspPagesIndex.HOME)
        || path.endsWith(JspPagesIndex.REGISTRAZIONE)) {
      chain.doFilter(request, response);
    }
    // l'utente non è loggato
    if (utenteEmail == null) {
    	/*
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
          */
    }
    chain.doFilter(request, response);
    
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
