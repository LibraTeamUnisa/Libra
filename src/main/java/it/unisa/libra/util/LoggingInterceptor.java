package it.unisa.libra.util;

import java.util.Date;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Loggable
public class LoggingInterceptor {

  /**
   * Metodo per il logging in entrata e uscita dei metodi.
   * 
   * @param ic Invocation Context
   * @return La classe successiva da eseguire
   * @throws Exception Eccezione generale del logger
   */
  @AroundInvoke
  public Object logMethod(InvocationContext ic) throws Exception {
    Date enterDate = new Date();
    SimpleLog.getInstance().entering(ic.getTarget().getClass().getSimpleName(),
        ic.getMethod().getName());
    try {
      return ic.proceed();
    } finally {
      Date exitDate = new Date();

      SimpleLog.getInstance().exiting(ic.getTarget().getClass().getSimpleName(),
          ic.getMethod().getName(), exitDate.getTime() - enterDate.getTime());
    }
  }

}
