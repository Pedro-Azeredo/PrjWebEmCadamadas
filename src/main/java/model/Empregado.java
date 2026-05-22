package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Empregado {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length = 11)
    private String cpf;

    @Column(nullable=false, length=60)
    private String nome;

    @ManyToOne(optional=false, fetch=FetchType.LAZY) 
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    public Empregado() {
    	super();
    }

	public Empregado(String cpf, String nome, Departamento departamento) throws ModelException {
		super();
		this.setCpf(cpf);
		this.setNome(nome);
		this.setDepartamento(departamento);
	}

	public Long getIdEmpregado() {
		return id;
	}

	public void setIdEmpregado(Long idEmpregado) {
		this.id = idEmpregado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws ModelException {
		Empregado.validarCpf(cpf);
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ModelException{
		Empregado.validarNome(nome);
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) throws ModelException {
		Empregado.validarDepartamento(departamento);
		this.departamento = departamento;
		departamento.adicionarEmpregado(this);
	}
	
	public static void validarCpf(String cpf) throws ModelException {
		if (cpf == null || cpf.length() == 0)
			throw new ModelException("O cpf não pode ser nulo!");
		if (cpf.length() != 11)
			throw new ModelException("O cpf deve ter até 11 dígitos");
		String regex = "[0-9]{11}";
		if(!cpf.matches(regex))
			throw new ModelException("O cpf está em um formato inválido!");
	}
	
	public static void validarNome(String nome) throws ModelException {
		if (nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		if (nome.length() > 40)
			throw new ModelException("O nome deve ter até 40 caracteres");
		String regex = "[A-Za-zÀ-ÿ -']{5,40}";
		if(!nome.matches(regex))
			throw new ModelException("O nome contém caracteres inválidos!");
	}
	
	public static void validarDepartamento(Departamento departamento) throws ModelException {
		if (departamento == null)
			throw new ModelException("O departamento não pode ser nulo!");
	}

	@Override
	public String toString() {
		return "Empregado [cpf=" + cpf + ", nome=" + nome + ", departamento=" + departamento.getNome() + "]";
	}
}