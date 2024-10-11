package Control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DAO.DAOTarefa;
import Model.Tarefa;

@ManagedBean
@ViewScoped
public class TarefasBean implements Serializable {
	private int codigo;
	private String situacao;
	private String titulo;
	private String descricao;
	private String responsavel;
	private String deadline;
	private String prioridade;
	private List<Tarefa> listaTarefas;
	private List<Tarefa> listaTarefasComCampo;
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
	
	public List<Tarefa> getListaTarefasComCampo() {
		return listaTarefasComCampo;
	}

	public void setListaTarefasComCampo(List<Tarefa> listaTarefasComCampo) {
		this.listaTarefasComCampo = listaTarefasComCampo;
	}

	public void listarTarefas() {
		DAOTarefa dao = new DAOTarefa();	
		
		listaTarefas = dao.listarTarefas();
		
		dao.getManager().close();
	}
	
	public void listarTarefasComCampo() {
		DAOTarefa dao = new DAOTarefa();
		
		listaTarefasComCampo = dao.listarTarefasComCampo(codigo, titulo, responsavel, situacao);
		
		dao.getManager().close();
	}

	public void adicionarTarefa() {
		DAOTarefa dao = new DAOTarefa();
		Tarefa tarefa = new Tarefa();

		tarefa.setResponsavel(responsavel);
		tarefa.setDeadline(deadline);
		tarefa.setDescricao(descricao);
		tarefa.setPrioridade(prioridade);
		tarefa.setSituacao("EM_ANDAMENTO");			//enum
		tarefa.setTitulo(titulo);
		
		dao.adicionarTarefa(tarefa);
		
		dao.getManager().close();
		
	}
	
	public void modificaTarefa() {
		DAOTarefa dao = new DAOTarefa();
		
		dao.modTarefa(codigo, situacao);
		listarTarefas();
		dao.getManager().close();
	}

	

}
