package roberto.cursospringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.domain.Cidade;
import roberto.cursospringboot.domain.Cliente;
import roberto.cursospringboot.domain.Endereco;
import roberto.cursospringboot.domain.Estado;
import roberto.cursospringboot.domain.Produto;
import roberto.cursospringboot.domain.enums.TipoCliente;
import roberto.cursospringboot.repositories.CategoriaRepository;
import roberto.cursospringboot.repositories.CidadeRepository;
import roberto.cursospringboot.repositories.ClienteRepository;
import roberto.cursospringboot.repositories.EnderecoRepository;
import roberto.cursospringboot.repositories.EstadoRepository;
import roberto.cursospringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringbootApplication implements CommandLineRunner { // permite implementar um método auxiliar pra
																		// executar uma ação quando a aplicação iniciar
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática"); // lembrando que o banco cria automaticamente o número!
		Categoria cat2 = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//Associando categorias aos produtos:
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); //A lista estava vazia...adicionando
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Associando os produtos às categorias:
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//Instanciando e associando...
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2); //muitos pra 1, no próprio construtor faz a relaçao
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//Associando os estados às cidades
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		//OBS: de acordo com diagrama: salvar Estado primeiro pois cada cidade tem obrigatoriamente um estado
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	
	

}
