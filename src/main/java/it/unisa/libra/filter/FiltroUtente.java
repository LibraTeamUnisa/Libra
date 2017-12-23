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
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroUtente. Garantisce l'accesso alla risorsa richiesta
 * solo se l'utente � loggato.
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
    if (utenteEmail != null) {
      // l'utente � loggato e pu� proseguire nella navigazione
      chain.doFilter(request, response);
      return;
    } else {
      // ad alcune pagine possono accedere tutti, anche gli utenti non loggati
      // quindi questo filtro non deve essere applicato a queste pagine
      String path = ((HttpServletRequest) request).getRequestURI();
      if (path.endsWith(JspPagesIndex.ACCESSO_NEGATO) || path.endsWith(JspPagesIndex.HOME)
          || path.endsWith(JspPagesIndex.REGISTRAZIONE)) {
        chain.doFilter(request, response);
        return;
      } else {
        // l'utente non � loggato ma desidera accedere a una pagina protetta
        ((HttpServletResponse) response).sendRedirect(
            ((HttpServletRequest) request).getContextPath() + JspPagesIndex.ACCESSO_NEGATO);
        return;
      }
    }
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
