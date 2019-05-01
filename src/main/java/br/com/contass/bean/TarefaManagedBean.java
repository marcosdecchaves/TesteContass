package br.com.contass.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import br.com.contass.dao.TarefaDAO;
import br.com.contass.model.TarefaDTO;

@ManagedBean
@SessionScoped
public class TarefaManagedBean {

	private TarefaDTO tarefaDTO;
	private ArrayList<TarefaDTO>listaTarefas = new ArrayList<TarefaDTO>();
	private Integer idTarefaPesquisada;


	private TarefaDAO tarefaDAO = new TarefaDAO();

	public TarefaManagedBean() {
		tarefaDTO = new TarefaDTO();
	}

	public String cadastrarTarefa()
	{
		String url = null;

		if(tarefaDTO != null) {
			System.out.println(tarefaDTO.getDescricao());


			tarefaDAO.adicionarTarefa(tarefaDTO);
			tarefaDTO = new TarefaDTO();
			url = "menu.xhtml?faces-redirect=true";
		}

		return url;
	}


	public String listarTarefas() {
		String url = null;
		listaTarefas = (ArrayList<TarefaDTO>) tarefaDAO.buscarTarefas();

		if(listaTarefas != null && listaTarefas.size() > 0) {
			tarefaDTO = new TarefaDTO();
			url = "listarTarefas.xhtml?faces-redirect=true";
		}

		return url;

	}

	public String buscarPorId() {

		String url = null;
		listaTarefas = new ArrayList<TarefaDTO>();
		if(idTarefaPesquisada != null) {
			tarefaDTO =  tarefaDAO.findPorId(idTarefaPesquisada);
			if(tarefaDTO != null && tarefaDTO.getId() != null) {
				listaTarefas.add(tarefaDTO);
				tarefaDTO = new TarefaDTO();
				url = "listarTarefas.xhtml?faces-redirect=true";
			}
		}

		idTarefaPesquisada = null;
		return url;

	}

	public void editarTarefa() {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formTarefa:tableTarefas");
		tarefaDTO = (TarefaDTO) dataTable.getRowData();

		if(tarefaDTO != null && tarefaDTO.getId() != null){
			tarefaDAO.atualizarTarefa(tarefaDTO);

		}

	}

	public void removerTarefa() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formTarefa:tableTarefas");
		tarefaDTO = (TarefaDTO) dataTable.getRowData();

		if(tarefaDTO.getId() != null) {
			tarefaDAO.removerTarefa(tarefaDTO.getId());
			listarTarefas();
		}
	}
	
	public String telaCadastro() {
		tarefaDTO = new TarefaDTO();
		
		return "cadastrarTarefa.xhtml?faces-redirect=true";
	}
	
	public String redirecionarMenu() {
		
		if(listaTarefas != null && listaTarefas.size() > 0) {
			listaTarefas = new ArrayList<TarefaDTO>();
		}
		return "menu.xhtml?faces-redirect=true";
	}


	public TarefaDTO getTarefaDTO() {
		return tarefaDTO;
	}

	public void setTarefaDTO(TarefaDTO tarefaDTO) {
		this.tarefaDTO = tarefaDTO;
	}

	public ArrayList<TarefaDTO> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(ArrayList<TarefaDTO> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public Integer getIdTarefaPesquisada() {
		return idTarefaPesquisada;
	}

	public void setIdTarefaPesquisada(Integer idTarefaPesquisada) {
		this.idTarefaPesquisada = idTarefaPesquisada;
	}

}
