package it.unisa.libra.filter;

import com.mysql.jdbc.StringUtils;
import it.unisa.libra.util.JspPagesIndex;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroUtente. Garantisce l'accesso alla risorsa richiesta
 * solo se l'utente è loggato.
 * 
 * @see javax.servlet.Filter
 */
public class FiltroUtente implements Filter {

  /** Default constructor. */
  public FiltroUtente() {}

  /**
   * Override.
   * 
   * @see Filter#destroy()
   */
  public void destroy() {}

  /**
   * Override. Garantisce l'accesso alla risorsa richiesta solo se l'utente è loggato. In caso
   * contrario, rimanda a una pagina di errore.
   * 
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
	  
    // evita il bug: dopo il logout, tornando indietro nel browser posso vedere ancora le pagine
    ((HttpServletResponse) response).setHeader("Cache-Control",
        "no-cache,no-store,must-revalidate");
    ((HttpServletResponse) response).setHeader("Pragma", "no-cache");
    ((HttpServletResponse) response).setDateHeader("Expires", 0);

    String utenteEmail =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteEmail");
    if (!(StringUtils.isNullOrEmpty(utenteEmail))) {
      // l'utente è loggato e può proseguire nella navigazione
      chain.doFilter(request, response);
      return;
    } else {
      // ad alcune pagine possono accedere tutti, anche gli utenti non loggati
      // quindi questo filtro non deve essere applicato a queste pagine
      String path = ((HttpServletRequest) request).getRequestURI();
      
      if (path.endsWith(JspPagesIndex.ACCESSO_NEGATO) || path.endsWith(JspPagesIndex.HOME)
          || path.endsWith(JspPagesIndex.REGISTRAZIONE) || path.equals("/Libra/")) {
        chain.doFilter(request, response);
        return;
      } else {

        // l'utente non è loggato ma desidera accedere a una pagina protetta
        ((HttpServletResponse) response).sendRedirect(
            ((HttpServletRequest) request).getContextPath() + JspPagesIndex.ACCESSO_NEGATO);
        return;
        
      }
    }
    

  }

  /**
   * Override.
   * 
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig) throws ServletException {}
}
