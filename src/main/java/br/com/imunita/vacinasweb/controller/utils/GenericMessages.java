package br.com.imunita.vacinasweb.controller.utils;

import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@RequestScoped
public class GenericMessages implements Serializable {

    @Inject
    private FacesProducer facesProducer;

    private static final long serialVersionUID = 1L;

    /* -------- Mensagens de ERROR -------- */
    public void addErrorMessage(String componentId, String errorMessage) {
        addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
    }

    public void addErrorMessage(String componentId, String summary, String detail) {
        addMessage(componentId, summary, detail, FacesMessage.SEVERITY_ERROR);
    }

    public void addErrorMessage(String errorMessage) {
        addErrorMessage("messages", errorMessage);
    }

    /* -------- Mensagens de INFO -------- */
    public void addInfoMessage(String componentId, String infoMessage) {
        addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
    }

    public void addInfoMessage(String componentId, String summary, String detail) {
        addMessage(componentId, summary, detail, FacesMessage.SEVERITY_INFO);
    }

    public void addInfoMessage(String infoMessage) {
        addInfoMessage("messages", infoMessage);
    }

    /* -------- Mensagens de FATAL -------- */
    public void addFatalMessage(String componentId, String message) {
        addMessage(componentId, message, FacesMessage.SEVERITY_FATAL);
    }

    public void addFatalMessage(String componentId, String summary, String detail) {
        addMessage(componentId, summary, detail, FacesMessage.SEVERITY_FATAL);
    }

    public void addFatalMessage(String message) {
        addFatalMessage("messages", message);
    }

    /* -------- Mensagens de WARN -------- */
    public void addWarnMessage(String componentId, String message) {
        addMessage(componentId, message, FacesMessage.SEVERITY_WARN);
    }

    public void addWarnMessage(String componentId, String summary, String detail) {
        addMessage(componentId, summary, detail, FacesMessage.SEVERITY_WARN);
    }

    public void addWarnMessage(String message) {
        addWarnMessage("messages", message);
    }

    private void addMessage(String componentId, String errorMessage, Severity severity) {

        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(severity);

        facesProducer.getFacesContext().addMessage(componentId, message);
//        context.addMessage(componentId, message);
    }

    private void addMessage(String componentId, String summary, String detail, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(severity, summary, detail);
        facesProducer.getFacesContext().addMessage(componentId, message);
//        context.addMessage(componentId, message);
    }

}
