package br.com.cactusbits.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.cactusbits.model.Usuarios;

public class UsuarioDao {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("help_desk");
	private EntityManager em = factory.createEntityManager();
	
	
	public Usuarios autenticar(String nome, String senha) {

	      try {
	    	  Usuarios usuario = (Usuarios) em.createQuery("SELECT u from Usuario u where u.nome = :name and u.senha = :senha")
	         .setParameter("name", nome)
	         .setParameter("senha", senha).getSingleResult(); 
	    	  
	    	  return usuario;
	        
	      } catch (NoResultException e) {
	            return null;
	      	}
	 }
	
	@SuppressWarnings("unchecked")
	public List<Usuarios> findAll() {
        return em.createQuery("FROM " +
        Usuarios.class.getName()).getResultList();
      }
	
	public Usuarios getById(final int id) {
        return em.find(Usuarios.class, id);
      }
	
	public void salvar(Usuarios usuario) {
        try {
        	em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();    
        } catch (Exception e) {
              e.printStackTrace();
              em.getTransaction().rollback();
        }
	 }
	
	public void deletarUsuario(Usuarios usuario) {
		try {
            em.getTransaction().begin();
            usuario = em.find(Usuarios.class, usuario.getId());
            em.remove(usuario);
            em.getTransaction().commit();
            
         } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
         }
	}
	
	public void removeById(final int id) {
        try {
           Usuarios usuario = getById(id);
           em.remove(usuario);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
      }

}
