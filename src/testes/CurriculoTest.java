package testes;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.CurriculoDao;
import daos.EstadoDao;
import daos.EstudanteDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Curriculo;
import entities.Estado;
import entities.Estudante;
import entities.SGE;

public class CurriculoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = new Estado();
		estado.setNome("Paraiba");
		estado.setUf("PB");
		estadoDao.cadatrarEstado(estado);
		
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("Joao Pessoa");
		cidadeDao.cadatrarCidade(cidade);
		
		SGEDao sgeDao = new SGEDao();
		SGE sge = new SGE();
		sge.setCNPJ("12345678901234");
		sge.setNome("Minha SGE");
		sgeDao.cadastrarSGE(sge);
		
		EstudanteDao estudanteDao = new EstudanteDao();
		Estudante estudante = new Estudante();
		estudante.setCpf("12345678901");
		estudante.setCidade(cidade);
		estudante.setDataNascimento(new Date());
		estudante.setEndereco("Meu endereco");
		estudante.setNome("Meu nome");
		estudante.setSge(sge);
		estudanteDao.cadastrarEstudante(estudante);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EstudanteDao estudanteDao = new EstudanteDao();
		Estudante estudante = estudanteDao.encontrarEstudantePeloCpf("12345678901");
		estudanteDao.apagarEstudante(estudante);
		
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.encontrarCidadePeloNome("Joao Pessoa");
		cidadeDao.apagarCidade(cidade);
		
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.encontrarEstadoPeloUf("PB");
		estadoDao.apagarEstado(estado);
		
		SGEDao sgeDao = new SGEDao();
		SGE sge = sgeDao.encontrarSgePeloCnpj("12345678901234");
		sgeDao.apagarSGE(sge);
	}

	@Test
	public void testCrudCurriculo() {
		CurriculoDao dao = new CurriculoDao();
		assertEquals(0, dao.listarCurriculos().size());
		
		Curriculo curriculo = new Curriculo();
		curriculo.setEstudante(new EstudanteDao().encontrarEstudantePeloCpf("12345678901"));
		curriculo.setObjetivo("Meu Objetivo");
		curriculo.setSge(new SGEDao().encontrarSgePeloCnpj("12345678901234"));
		
		dao.cadastrarCurriculo(curriculo);
		
		assertEquals(1, dao.listarCurriculos().size());
		
		Curriculo curriculoEditado = dao.encontrarCurriculoPeloId(1);
		curriculoEditado.setObjetivo("Nosso Objetivo");
		
		dao.editarCurriculo(curriculoEditado);
		assertEquals(1, dao.listarCurriculos().size());
		
		Curriculo curriculoPesquisado = dao.encontrarCurriculoPeloId(1);
		
		assertEquals("Nosso Objetivo", curriculoPesquisado.getObjetivo());
		assertEquals("Minha SGE", curriculoPesquisado.getSge().getNome());
		
		assertEquals(1, dao.listarCurriculos().size());
		
		Curriculo curriculoApagado = dao.encontrarCurriculoPeloId(1);
		
		dao.apagarCurrriculo(curriculoApagado);
		assertEquals(0, dao.listarCurriculos().size());
	}
}
