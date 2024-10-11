package Control;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String titulo;
	private String descricao;
	private String responsavel;
	private String deadline;
	private String prioridade;
	private Map<String, Boolean> situacao;
	private List<Tarefa> listaTarefas;
	private List<Tarefa> listaTarefasComCampo;
	private String situacaoChave;
	private DAOTarefa dao;
	
	public String getSituacaoChave() {
		return situacaoChave;
	}

	public void setSituacaoChave(String situacaoChave) {
		this.situacaoChave = situacaoChave;
	}
	
	public TarefasBean() {
		dao = new DAOTarefa();
		this.situacao = new HashMap<>();
		situacao.put("EM ANDAMENTO", false);
		situacao.put("CONCLUIDA", true);
	}
	
	public  Map<String, Boolean> getSituacao() {
		return situacao;
	}

	public void setSituacao (Map<String, Boolean> situacao) {
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
		
		listaTarefasComCampo = dao.listarTarefasComCampo(codigo, titulo, responsavel, situacaoChave);
		
		dao.getManager().close();
	}

	public void adicionarTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setResponsavel(responsavel);
		tarefa.setDeadline(deadline);
		tarefa.setDescricao(descricao);
		tarefa.setPrioridade(prioridade);
		tarefa.setSituacao(false);			
		tarefa.setTitulo(titulo);
		
		dao.adicionarTarefa(tarefa);
		}
	
	public void modificarTarefa() {
		dao.modificarTarefa(codigo, situacaoChave);
		listarTarefas();
		
	}
	
	public void deletarTarefa() {
		dao.deletarTarefa(codigo);
		listarTarefas();
		
	}

	

}
