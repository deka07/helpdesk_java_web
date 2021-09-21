package br.com.cactusbits.bean;

import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;

import br.com.cactusbits.dao.UsuarioDao;
import br.com.cactusbits.model.Usuarios;

@Named()
@SessionScoped
public class UsuariosBean {
	
	private UsuarioDao usuarioDAO = new UsuarioDao();
	private Usuarios usuario = new Usuarios();
	
	public String envia() {
	    usuario = usuarioDAO.autenticar(usuario.getNome(), usuario.getSenha());
	    if (usuario == null) {
	      usuario = new Usuarios();
	      FacesContext.getCurrentInstance().addMessage(
	    		  null, new FacesMessage(
	    				  FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", 
	    				  "Erro no Login!"));
	      return null;
	    } else {
	          return "/main";
	    	}
	  }
	
	public Usuarios autenticar() {
	    return usuario;
	  }
	
	 public void autenticar(Usuarios usuario) {
		    this.usuario = usuario;
		  }

}
