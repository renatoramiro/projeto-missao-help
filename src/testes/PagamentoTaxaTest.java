package testes;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EmpresaDao;
import daos.EstadoDao;
import daos.PagamentoTaxaDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Empresa;
import entities.Estado;
import entities.PagamentoTaxa;
import entities.SGE;

public class PagamentoTaxaTest {

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
		empresa.setCNPJ("12345678901111");
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
	public void testCrudPagamentoTaxa() {
		PagamentoTaxaDao dao = new PagamentoTaxaDao();
		assertEquals(0, dao.listarPagamentoTaxa().size());
		
		PagamentoTaxa taxa = new PagamentoTaxa();
		taxa.setEmpresa(new EmpresaDao().encontrarEmpresaPeloCnpj("12345678901111"));
		taxa.setSge(new SGEDao().encontrarSgePeloCnpj("12345678901234"));
		taxa.setValor(11.22);
		
		dao.cadastrarPagamentoTaxa(taxa);
		assertEquals(1, dao.listarPagamentoTaxa().size());
		
		PagamentoTaxa taxaModificada = dao.encontrarPagamentoTaxaPeloCodigo(1);
		taxaModificada.setValor(333.55);
		
		dao.editarPagamentoTaxa(taxaModificada);
		assertEquals(1, dao.listarPagamentoTaxa().size());
		
		PagamentoTaxa taxaPesquisada = dao.encontrarPagamentoTaxaPeloCodigo(1);
		
		assertEquals("12345678901234", taxaPesquisada.getSge().getCnpj());
		assertEquals("12345678901111", taxaPesquisada.getEmpresa().getCnpj());
		
		assertEquals(1, dao.listarPagamentoTaxa().size());
		
		PagamentoTaxa taxaApagada = dao.encontrarPagamentoTaxaPeloCodigo(1);
		dao.apagarPagamentoTaxa(taxaApagada);
		assertEquals(0, dao.listarPagamentoTaxa().size());
	}
}
