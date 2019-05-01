package br.com.contass.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.contass.model.TarefaDTO;

public class TarefaDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("dadostarefa");
	private EntityManager em = factory.createEntityManager();

	public TarefaDAO() {

	}

	public void adicionarTarefa(TarefaDTO tarefa) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(tarefa);
		em.getTransaction().commit();
		em.close();
	}

	public TarefaDTO getTarefa(int id) {

		return buscarTarefas().get(id);
	}

	public void removerTarefa(Integer idTarefa) {

		em = factory.createEntityManager();
		TarefaDTO tarefaEncontrada = em.find(TarefaDTO.class, idTarefa);

		em.getTransaction().begin();
		em.remove(tarefaEncontrada);
		em.getTransaction().commit();
		em.close();

	}

	public void atualizarTarefa(TarefaDTO tarefa) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(tarefa);
		em.getTransaction().commit();
		em.close();

	}

	public List<TarefaDTO> buscarTarefas() {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		ArrayList<TarefaDTO> tarefas = new ArrayList<TarefaDTO>();
		tarefas = (ArrayList<TarefaDTO>) em.createQuery("select t from tarefa t").getResultList();
		em.close();
		return tarefas;
	}

	public TarefaDTO findPorId(Integer id){
		TarefaDTO tarefaEncontrada = new TarefaDTO();
		em = factory.createEntityManager();

		tarefaEncontrada = em.find(TarefaDTO.class, id);

		return tarefaEncontrada;


	}


}
