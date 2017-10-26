/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.utils.GenericMessages;
import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rodolpho
 */
@Named(value = "loginAcessoMBean")
@SessionScoped
public class LoginAcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesProducer producer;

    @Inject
    private GenericMessages messages;

    private String loginUsuario;

    public void preRender() {
        HttpServletRequest request = producer.getHttpServletRequest();
        if ("true".equals(request.getParameter("invalid"))) {
            messages.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {

        RequestDispatcher dispatcher = producer.getHttpServletRequest().getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(producer.getHttpServletRequest(), producer.getHttpServletResponse());

        producer.getFacesContext().responseComplete();
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

}
