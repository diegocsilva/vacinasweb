package br.com.imunita.vacinasweb.controller.utils.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestScoped
public class FacesProducer {

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) getExternalContext().getRequest());
    }

    public HttpServletResponse getHttpServletResponse() {
        return ((HttpServletResponse) getExternalContext().getResponse());
    }

    public Object getFlash(String nomeParametro) {
        return getExternalContext().getFlash().get(nomeParametro);
    }

    public void putFlash(Object parametro, String nomeParametro) {
        getExternalContext().getFlash().put(nomeParametro, parametro);
    }

}
