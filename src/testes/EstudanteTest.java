package testes;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EstadoDao;
import daos.EstudanteDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Estado;
import entities.Estudante;
import entities.SGE;

public class EstudanteTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = new Estado();
		estado.setNome("Amazonas");
		estado.setUf("AM");
		estadoDao.cadatrarEstado(estado);
		
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("Manaus");
		cidadeDao.cadatrarCidade(cidade);
		
		SGEDao sgeDao = new SGEDao();
		SGE sge = new SGE();
		sge.setCNPJ("12345678902222");
		sge.setNome("Sua SGE");
		sgeDao.cadastrarSGE(sge);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.encontrarCidadePeloNome("Manaus");
		cidadeDao.apagarCidade(cidade);
		
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.encontrarEstadoPeloUf("AM");
		estadoDao.apagarEstado(estado);
		
		SGEDao sgeDao = new SGEDao();
		SGE sge = sgeDao.encontrarSgePeloNome("Sua SGE");
		sgeDao.apagarSGE(sge);
	}

	@Test
	public void testCrudEstudante() {
		EstudanteDao dao = new EstudanteDao();
		assertEquals(0, dao.listarEstudantes().size());
		
		Estudante estudante = new Estudante();
		estudante.setCpf("12345678902");
		estudante.setCidade(new CidadeDao().encontrarCidadePeloNome("Manaus"));
		estudante.setDataNascimento(new Date());
		estudante.setEndereco("Meu endereco");
		estudante.setNome("Meu nome");
		estudante.setSge(new SGEDao().encontrarSgePeloNome("Sua SGE"));
		
		dao.cadastrarEstudante(estudante);
		assertEquals(1, dao.listarEstudantes().size());
			
		Estudante estudanteModificado = dao.encontrarEstudantePeloCpf("12345678902");
		estudanteModificado.setEndereco("Nosso endereco");
		
		dao.editarEstudante(estudanteModificado);
		assertEquals(1, dao.listarEstudantes().size());
		
		Estudante estudantePesquisado = dao.encontrarEstudantePeloCpf("12345678902");
		
		assertEquals("12345678902", estudantePesquisado.getCpf());
		assertEquals("Nosso endereco", estudantePesquisado.getEndereco());
		assertEquals("Amazonas", estudantePesquisado.getCidade().getEstado().getNome());
		
		assertEquals(1, dao.listarEstudantes().size());
		
		Estudante estudanteApagado = dao.encontrarEstudantePeloCpf("12345678902");
		
		dao.apagarEstudante(estudanteApagado);
		assertEquals(0, dao.listarEstudantes().size());
	}
}
