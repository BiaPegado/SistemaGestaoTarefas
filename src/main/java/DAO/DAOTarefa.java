package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.Tarefa;
import Util.JpaUtil;

public class DAOTarefa implements Serializable {
	private EntityManager manager;

	public DAOTarefa() {
		this.manager = JpaUtil.getEntityManagerPostgre();
	}

	public void adicionarTarefa(Tarefa tarefa) {
		try {
			manager.getTransaction().begin();
			manager.persist(tarefa);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			//manager.close();
		}
	}

	public List<Tarefa> listarTarefas() {
		List<Tarefa> tarefas = null;

		try {
	        TypedQuery<Tarefa> lista = manager.createQuery("SELECT t FROM Tarefa t ORDER BY t.codigo", Tarefa.class);
	        tarefas = lista.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return tarefas;
	}
	
	public List<Tarefa> listarTarefasComCampo(int cod, String titulo, String responsavel, String situacao) {
		List<Tarefa> tarefas = null;
		String query;
		try {
	        query = "SELECT t FROM Tarefa t WHERE 1=1";

	        if (cod != 0) {
	            query += " AND t.codigo = :cod";
	        }
	        if (titulo != null && !titulo.isEmpty()) {
	        	query += " AND t.titulo LIKE :titulo";
	        }
	        if (responsavel != null && !responsavel.isEmpty()) {
	        	query += " AND t.responsavel LIKE :responsavel";
	        }
	        if (situacao != null && !situacao.isEmpty()) {
	        	query += " AND t.situacao = :situacao";
	        }
	        
	        TypedQuery<Tarefa> query2 = manager.createQuery(query, Tarefa.class);
			
	        if (cod != 0) {
	            query2.setParameter("cod", cod);
	        }
	        if (titulo != null && !titulo.isEmpty()) {
	            query2.setParameter("titulo", "%" + titulo + "%");  
	        }
	        if (responsavel != null && !responsavel.isEmpty()) {
	            query2.setParameter("responsavel", "%" + responsavel + "%");  
	        }
	        if (situacao != null && !situacao.isEmpty()) {
	            query2.setParameter("situacao", situacao);
	        }
	        tarefas = query2.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return tarefas;
	}
	
	public void modTarefa(int cod, String situacao) {
		Tarefa tarefaModificada = new Tarefa();
		
		TypedQuery<Tarefa> tarefa = manager.createQuery("SELECT t FROM Tarefa t WHERE t.codigo = :cod", Tarefa.class);
        tarefa.setParameter("cod", cod);
		tarefaModificada = tarefa.getSingleResult();
		tarefaModificada.setSituacao(situacao);
		
		try {
			manager.getTransaction().begin();
			manager.merge(tarefaModificada);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			//manager.close();
		}
		
	}

	public EntityManager getManager() {
		return manager;
	}

}
