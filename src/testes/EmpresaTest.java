package testes;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EmpresaDao;
import daos.EstadoDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Empresa;
import entities.Estado;
import entities.SGE;

public class EmpresaTest {
	
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
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	public void testCrudEmpresa() {
		EmpresaDao empresaDao = new EmpresaDao();
		assertEquals(0, empresaDao.listarEmpresas().size());
		
		Empresa empresa = new Empresa();
		empresa.setCidade(new CidadeDao().encontrarCidadePeloNome("Joao Pessoa"));
		empresa.setCNPJ("12345678901234");
		empresa.setEndereco("Endereco da Empresa");
		empresa.setNome("Minha Empresa");
		empresa.setSge(new SGEDao().encontrarSgePeloNome("Minha SGE"));
		
		empresaDao.cadastrarEmpresa(empresa);
		assertEquals(1, empresaDao.listarEmpresas().size());
		
		Empresa empresaModificada = empresaDao.encontrarEmpresaPeloCnpj("12345678901234");
		empresaModificada.setNome("Nossa Empresa");
		empresaDao.editarEmpresa(empresaModificada);
		
		assertEquals(1, empresaDao.listarEmpresas().size());
		
		Empresa empresaPesquisada = empresaDao.encontrarEmpresaPeloCnpj("12345678901234");
		
		assertEquals("12345678901234", empresaPesquisada.getCnpj());
		assertEquals("Endereco da Empresa", empresaPesquisada.getEndereco());
		assertEquals("Nossa Empresa", empresaPesquisada.getNome());
		
		assertEquals(1, empresaDao.listarEmpresas().size());
		
		Empresa empresaApagada = empresaDao.encontrarEmpresaPeloCnpj("12345678901234");
		empresaDao.apagarEmpresa(empresaApagada);
		
		assertEquals(0, empresaDao.listarEmpresas().size());
	}
}
