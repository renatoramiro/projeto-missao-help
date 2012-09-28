package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import daos.SGEDao;
import entities.SGE;

public class SgeTest {

	@Test
	public void testCrudSge() {
		SGEDao dao = new SGEDao();
		assertEquals(0, dao.listarSGE().size());
		
		SGE sge = new SGE();
		sge.setCNPJ("12345678901234");
		sge.setNome("Minha SGE");
		
		dao.cadastrarSGE(sge);
		assertEquals(1, dao.listarSGE().size());
		
		SGE sgeModificada = dao.encontrarSgePeloCnpj("12345678901234");
		sgeModificada.setCNPJ("12345678901234");
		sgeModificada.setNome("Nossa SGE");
		
		dao.editarSGE(sgeModificada);
		assertEquals(1, dao.listarSGE().size());
		
		SGE sgePesquisada = dao.encontrarSgePeloNome("Nossa SGE");
		
		assertEquals("12345678901234", sgePesquisada.getCnpj());
		assertEquals("Nossa SGE", sgePesquisada.getNome());
		
		assertEquals(1, dao.listarSGE().size());
		
		SGE sgeApagada = dao.encontrarSgePeloCnpj("12345678901234");
		
		dao.apagarSGE(sgeApagada);
		assertEquals(0, dao.listarSGE().size());
	}
}
