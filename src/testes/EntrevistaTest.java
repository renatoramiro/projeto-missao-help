package testes;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.CidadeDao;
import daos.EmpresaDao;
import daos.EntrevistaDao;
import daos.EstadoDao;
import daos.EstudanteDao;
import daos.SGEDao;
import entities.Cidade;
import entities.Empresa;
import entities.Entrevista;
import entities.Estado;
import entities.Estudante;
import entities.SGE;

public class EntrevistaTest{

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
		EmpresaDao empresaDao = new EmpresaDao();
		Empresa empresa = empresaDao.encontrarEmpresaPeloCnpj("12345678901234");
		empresaDao.apagarEmpresa(empresa);
		
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
	public void testCrudEntrevista() {
		EntrevistaDao entrevistaDao = new EntrevistaDao();
		assertEquals(0, entrevistaDao.listarEntrevistas().size());
		
		Entrevista entrevista = new Entrevista();
		entrevista.setDataEntrevista(new Date());
		entrevista.setEmpresa(new EmpresaDao().encontrarEmpresaPeloNome("Minha Empresa"));
		entrevista.setEstudante(new EstudanteDao().encontrarEstudantePeloCpf("12345678901"));
		entrevista.setHorarioEntrevista(new Date());
		entrevista.setSge(new SGEDao().encontrarSgePeloCnpj("12345678901234"));
		
		entrevistaDao.cadastrarEntrevista(entrevista);
		assertEquals(1, entrevistaDao.listarEntrevistas().size());
		
		Entrevista entrevistaModificada = entrevistaDao.encontrarEntrevistaPeloId(1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			entrevistaModificada.setDataEntrevista(sdf.parse("31/12/2012"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		entrevistaDao.editarEntrevista(entrevistaModificada);
		assertEquals(1, entrevistaDao.listarEntrevistas().size());
		
		Entrevista entrevistaPesquisada = entrevistaDao.encontrarEntrevistaPeloId(1);
		
		assertEquals(1, entrevistaPesquisada.getId());
		assertEquals("12345678901", entrevistaPesquisada.getEstudante().getCpf());
		
		assertEquals(1, entrevistaDao.listarEntrevistas().size());
		
		Entrevista entrevistaApagada = entrevistaDao.encontrarEntrevistaPeloId(1);
		
		entrevistaDao.apagarEntrevista(entrevistaApagada);
		assertEquals(0, entrevistaDao.listarEntrevistas().size());
	}
}
