package testes;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EmpresaDao;
import daos.EstadoDao;
import daos.SGEDao;
import daos.VagaDao;
import entities.Cidade;
import entities.Empresa;
import entities.Estado;
import entities.SGE;
import entities.Vaga;

public class VagaTest {

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
		
		EmpresaDao empresaDao = new EmpresaDao();
		Empresa empresa = new Empresa();
		empresa.setCidade(cidade);
		empresa.setCNPJ("12345678901234");
		empresa.setEndereco("Endereco da Empresa");
		empresa.setNome("Minha Empresa");
		empresa.setSge(sge);
		empresaDao.cadastrarEmpresa(empresa);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EmpresaDao empresaDao = new EmpresaDao();
		Empresa empresa = empresaDao.encontrarEmpresaPeloNome("Minha Empresa");
		empresaDao.apagarEmpresa(empresa);
		
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
	public void testCrudVaga() {
		VagaDao vagaDao = new VagaDao();
		assertEquals(0, vagaDao.listarVagas().size());
		
		Vaga vaga = new Vaga();
		vaga.setAreaConhecimento("Minha area");
		vaga.setEmpresa(new EmpresaDao().encontrarEmpresaPeloCnpj("12345678901234"));
		vaga.setFuncao("Minha Funcao");
		vaga.setJornadaTrabalho(new Date());
		vaga.setSalario(100.0);
		
		vagaDao.cadastrarVaga(vaga);
		assertEquals(1, vagaDao.listarVagas().size());
		
		Vaga vagaModificada = vagaDao.encontrarVagaPeloCodigo(1);
		vagaModificada.setAreaConhecimento("Nossa area");
		vagaModificada.setSalario(222.2);
		
		vagaDao.editarVaga(vagaModificada);
		assertEquals(1, vagaDao.listarVagas().size());
		
		Vaga vagaPesquisada = vagaDao.encontrarVagaPeloCodigo(1);
		
		assertEquals("Nossa area", vagaPesquisada.getAreaConhecimento());
		assertEquals(1, vagaPesquisada.getCodVaga());
		assertEquals("Minha Funcao", vagaPesquisada.getFuncao());
		
		assertEquals(1, vagaDao.listarVagas().size());
		
		Vaga vagaApagada = vagaDao.encontrarVagaPeloCodigo(1);
		
		vagaDao.apagarVaga(vagaApagada);
		assertEquals(0, vagaDao.listarVagas().size());
	}
}
