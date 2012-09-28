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
import daos.ExperienciaDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Curriculo;
import entities.Estado;
import entities.Estudante;
import entities.Experiencia;
import entities.SGE;

public class ExperienciaTest {

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
		
		CurriculoDao curriculoDao = new CurriculoDao();
		Curriculo curriculo = new Curriculo();
		curriculo.setEstudante(estudante);
		curriculo.setObjetivo("Meu Objetivo");
		curriculo.setSge(sge);
		curriculoDao.cadastrarCurriculo(curriculo);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CurriculoDao curriculoDao = new CurriculoDao();
		Curriculo curriculo = curriculoDao.encontrarCurriculoPeloId(1);
		curriculoDao.apagarCurrriculo(curriculo);
		
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
		SGE sge = sgeDao.encontrarSgePeloNome("Minha SGE");
		sgeDao.apagarSGE(sge);
	}

	@Test
	public void testCrudExperiencia() {
		ExperienciaDao dao = new ExperienciaDao();
		assertEquals(0, dao.listarExperiencia().size());
		
		Experiencia experiencia = new Experiencia();
		experiencia.setCurriculo(new CurriculoDao().encontrarCurriculoPeloId(1));
		experiencia.setDescricao("Minha descricao");
		
		dao.cadastrarExperiencia(experiencia);
		assertEquals(1, dao.listarExperiencia().size());
		
		Experiencia experienciaModificada = dao.encontrarExperienciaPeloId(1);
		experienciaModificada.setDescricao("Nossa descricao");
		
		dao.editarExperiencia(experienciaModificada);
		assertEquals(1, dao.listarExperiencia().size());
		
		Experiencia experienciaPesquisada = dao.encontrarExperienciaPeloId(1);
		
		assertEquals("Nossa descricao", experienciaPesquisada.getDescricao());
		assertEquals("Meu Objetivo", experienciaPesquisada.getCurriculo().getObjetivo());
		
		assertEquals(1, dao.listarExperiencia().size());
		
		Experiencia experienciaApagada = dao.encontrarExperienciaPeloId(1);
		
		dao.apagarExperiencia(experienciaApagada);
		assertEquals(0, dao.listarExperiencia().size());
	}

}
