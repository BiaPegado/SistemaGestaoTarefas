package Testes;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.DAOTarefa; 
import Model.Tarefa;
import javax.persistence.TypedQuery;


public class TesteIntegracao {
	private Tarefa tarefa;
	private DAOTarefa dao;
	private EntityManagerFactory managerTestes;
	private EntityManager manager;
	private String tituloTarefa = "Dar bom dia";
	
	public TesteIntegracao() {
		this.tarefa = new Tarefa();
		tarefa.setResponsavel("responsavel");
		tarefa.setDeadline("deadline");
		tarefa.setDescricao("descricao");
		tarefa.setPrioridade("prioridade");
		tarefa.setSituacao(false);			
		tarefa.setTitulo(tituloTarefa);
	}
	
	@Test
	public void adicionarTarefaComSucesso() {
		dao.adicionarTarefa(tarefa);
		Tarefa tarefaTeste = acharTarefa();
		assertNotNull(tarefaTeste);
	}
	
	@Test
	public void deletarTarefaComSucesso() {
		if(acharTarefa()==null) {
			dao.adicionarTarefa(tarefa);
		}
		tarefa = acharTarefa();
		
		dao.deletarTarefa(tarefa.getCodigo());
		assertNull(manager.find(Tarefa.class, tarefa.getCodigo()));
	}
	
	@Test
	public void modificarTarefaComSucesso() {
		if(acharTarefa()==null) {
			dao.adicionarTarefa(tarefa);
		}
		tarefa = acharTarefa();
		
		dao.modificarTarefa(tarefa.getCodigo(), "CONCLUIDA");
		assertEquals(manager.find(Tarefa.class, tarefa.getCodigo()).getSituacao(), true);
	}
	
	@Test
	public void deletarInvalidoSemErro() {
		int codigo = -1;
		dao.deletarTarefa(codigo);
	}
	
	public Tarefa acharTarefa() {
		TypedQuery<Tarefa> query = manager.createQuery("SELECT t FROM Tarefa t WHERE t.titulo = :tituloTarefa", Tarefa.class);
		query.setParameter("tituloTarefa", tituloTarefa);
		List<Tarefa> listaTarefas = query.getResultList();
		
		return listaTarefas.isEmpty() ? null : listaTarefas.get(0);
	}
	
	@BeforeEach
	public void abrirManager() {
		dao = new DAOTarefa();
		managerTestes = Persistence.createEntityManagerFactory("Testes");
		manager = managerTestes.createEntityManager();
		dao.setManager(manager);
	}
	
	@AfterEach
	public void fecharManager() {
		manager.close();
	}
	
}
