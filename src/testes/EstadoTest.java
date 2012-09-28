package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import daos.EstadoDao;
import entities.Estado;

public class EstadoTest {
	
	@Test
	public void testCrudEstado(){
		EstadoDao dao = new EstadoDao();
		assertEquals(0, dao.listarEstados().size());
		
		Estado estado = new Estado();
		estado.setNome("Paraiba");
		estado.setUf("PB");
		dao.cadatrarEstado(estado);
		
		assertEquals(1, dao.listarEstados().size());
		
		Estado estadoModificado = dao.encontrarEstadoPeloNome("Paraiba");
		estadoModificado.setNome("Pernambuco");
		estadoModificado.setUf("PE");
		dao.editarEstado(estadoModificado);
		
		assertEquals(1, dao.listarEstados().size());
		
		Estado estadoPesquisado = dao.encontrarEstadoPeloUf("PE");
		assertEquals("Pernambuco", estadoPesquisado.getNome());
		
		Estado estadoApagado = dao.encontrarEstadoPeloNome("Pernambuco");
		dao.apagarEstado(estadoApagado);
		
		assertEquals(0, dao.listarEstados().size());
	}
}
