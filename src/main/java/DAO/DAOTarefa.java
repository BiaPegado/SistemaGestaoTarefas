package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.Tarefa;
import Util.JpaUtil;

public class DAOTarefa implements Serializable {
	private EntityManager manager;
	private Map<String, Boolean> situacao;

	public DAOTarefa() {
		this.manager = JpaUtil.getEntityManagerPostgre();
		this.situacao = new HashMap<>();
		situacao.put("EM ANDAMENTO", false);
		situacao.put("CONCLUIDA", true);
	}

	public void adicionarTarefa(Tarefa tarefa) {
		try {
			manager.getTransaction().begin();
			manager.persist(tarefa);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
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
	
	public List<Tarefa> listarTarefasComCampo(int cod, String titulo, String responsavel, String situacaoChave) {
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
	        if (situacaoChave != null) {
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
	        if (situacaoChave != null) {
	            query2.setParameter("situacao", this.situacao.get(situacaoChave));
	        }
	        tarefas = query2.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return tarefas;
	}
	
	public void modificarTarefa(int cod,  String situacaoChave) {
		Tarefa tarefaModificada = manager.find(Tarefa.class, cod);
		tarefaModificada.setSituacao(situacao.get(situacaoChave));
		System.out.println(situacaoChave);
		
		try {
			manager.getTransaction().begin();
			manager.merge(tarefaModificada);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public EntityManager getManager() {
		return manager;
	}
	
	public void deletarTarefa(int cod) {
		Tarefa tarefa = manager.find(Tarefa.class, cod);
		try {
			manager.getTransaction().begin();
			manager.remove(tarefa);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	

}
