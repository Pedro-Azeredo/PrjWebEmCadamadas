package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique=true, length = 2)
	private String sigla;

	@Column(nullable = false, length = 20)
	private String nome;

	// Relacionamento bidirecional (opcional)
	@OneToMany(mappedBy = "departamento")
	private List<Empregado> listaEmpregados = new ArrayList<>();

	//
	// MÉTODOS
	//
	public Departamento() {
		super();
	}

	public Departamento(String s, String n) throws ModelException {
		super();
		this.setSigla(s);
		this.setNome(n);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String s) throws ModelException {
		Departamento.validarSigla(s);
		this.sigla = s;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String n) throws ModelException {
		Departamento.validarNome(n);
		this.nome = n;
	}

	public List<Empregado> getListaEmpregados() {
		// Retorna uma cópia da lista de empregados
		return new ArrayList<Empregado>(this.listaEmpregados);
	}
	
	public void setListaEmpregados(List<Empregado> novaLista) throws ModelException {
		if(novaLista == null)
			throw new ModelException("A lista de empregados não pode ser nula!");
		this.listaEmpregados = novaLista;
	}
	
	public boolean adicionarEmpregado(Empregado novo) {
		return this.listaEmpregados.add(novo);		
	}
	
	public boolean removerEmpregado(Empregado ex) {
		return this.listaEmpregados.remove(ex);		
	}

	@Override
	public String toString() {
		return "Departamento [sigla=" + sigla + ", nome=" + nome + "]";
	}

	public int compareTo(Departamento outro) {
		return this.nome.compareTo(outro.nome);
	}

	public static void validarSigla(String sigla) throws ModelException {
		if (sigla == null || sigla.length() != 2)
			throw new ModelException("A sigla passada é inválida: " + sigla);
	}

	public static void validarNome(String nome) throws ModelException {
		if (nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		if (nome.length() > 20)
			throw new ModelException("O nome deve ter até 20 caracteres");
		String regex = "[A-Za-zÀ-ÿ \\-\\']{5,20}";
		if(!nome.matches(regex))
			throw new ModelException("O nome contém caracteres inválidos!");
	}
}
