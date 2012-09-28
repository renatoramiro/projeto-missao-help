package testes;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EstadoDao;
import entities.Cidade;
import entities.Estado;

public class CidadeTest{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = new Estado();
		estado.setNome("Paraiba");
		estado.setUf("PB");
		estadoDao.cadatrarEstado(estado);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EstadoDao dao = new EstadoDao();
		Estado estado = dao.encontrarEstadoPeloUf("PB");
		dao.apagarEstado(estado);
	}

	@Test
	public void testCrudCidade() {
		CidadeDao cidadeDao = new CidadeDao();
		assertEquals(0, cidadeDao.listarCidades().size());
		
		Cidade cidade = new Cidade();
		cidade.setEstado(new EstadoDao().encontrarEstadoPeloUf("PB"));
		cidade.setNome("Joao Pessoa");
		cidadeDao.cadatrarCidade(cidade);
		
		assertEquals(1, cidadeDao.listarCidades().size());
		
		Cidade cidadeModificada = cidadeDao.encontrarCidadePeloNome("Joao Pessoa");
		cidadeModificada.setNome("Guarabira");
		cidadeDao.editarCidade(cidadeModificada);
		
		assertEquals(1, cidadeDao.listarCidades().size());
		
		Cidade cidadePesquisada = cidadeDao.encontrarCidadePeloNome("Guarabira");
		
		assertEquals("Guarabira", cidadePesquisada.getNome());
		assertEquals("Paraiba", cidadePesquisada.getEstado().getNome());
		
		assertEquals(1, cidadeDao.listarCidades().size());
		
		Cidade cidadeApagada = cidadeDao.encontrarCidadePeloNome("Guarabira");
		cidadeDao.apagarCidade(cidadeApagada);
		
		assertEquals(0, cidadeDao.listarCidades().size());
	}
}
