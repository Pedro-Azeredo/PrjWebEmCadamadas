//
// GRUPO 12
// Componentes: Pedro Henrique de Azeredo Ramos e Matheus de Assis Gonçalves
//
package model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class ConfiguraçãoPerfilProfissionalSaude extends ConfiguraçãoPerfil {

    public static final int TAMANHO_MAX_ESPECIALIDADE = 80;
    public static final int TAMANHO_MAX_NUM_REGISTRO = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = TAMANHO_MAX_NUM_REGISTRO)
    private String numRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProfissional tipoProfissional;

    @Column(nullable = false, length = TAMANHO_MAX_ESPECIALIDADE)
    private String especialidade;

    public ConfiguraçãoPerfilProfissionalSaude(){
        super();
    }

    public ConfiguraçãoPerfilProfissionalSaude(String numRegistro, TipoProfissional tipoProfissional, String especialidade) throws ModelException {
        super();
        this.setNumRegistro(numRegistro);
        this.setTipoProfissional(tipoProfissional);
        this.setEspecialidade(especialidade);
    }
    // fazer igual em depto (fetch, jsonmanage e cascade)
    @OneToMany(mappedBy = "configuracaoPerfilProfissionalSaude")
    private List<ItemDisponibilidade> itensDisponibilidade = new ArrayList<>();

    @OneToMany(mappedBy = "configuracaoPerfilProfissionalSaude")
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "configuracaoPerfilProfissionalSaude")
    private List<Reserva> reservas = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) throws ModelException {
        ConfiguraçãoPerfilProfissionalSaude.validarNumRegistro(numRegistro);
        this.numRegistro = numRegistro;
    }

    public TipoProfissional getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(TipoProfissional tipoProfissional) throws ModelException {
        ConfiguraçãoPerfilProfissionalSaude.validarTipoProfissional(tipoProfissional);
        this.tipoProfissional = tipoProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) throws ModelException{
        ConfiguraçãoPerfilProfissionalSaude.validarEspecialidade(especialidade);
        this.especialidade = especialidade;
    }

    public List<ItemDisponibilidade> getItensDisponibilidade() {
        return new ArrayList<ItemDisponibilidade>(this.itensDisponibilidade);
    }

    public void setItensDisponibilidade(List<ItemDisponibilidade> novaLista) throws ModelException {
        if(novaLista == null)
            throw new ModelException("A lista de Itens Disponibilidade não pode ser nula!");
        this.itensDisponibilidade = novaLista;
    }

    public boolean adicionarItemDisponibilidade(ItemDisponibilidade novo) {
        return this.itensDisponibilidade.add(novo);
    }

    public boolean removerItemDisponibilidade(ItemDisponibilidade ex) {
        return this.itensDisponibilidade.remove(ex);
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<Consulta>(this.consultas);
    }

    public void setConsultas(List<Consulta> novaLista) throws ModelException{
        if (novaLista == null)
            throw new ModelException("A lista de consultas não pode ser nula!");
        this.consultas = novaLista;
    }

    public boolean adicionarConsulta(Consulta novo) {
        return this.consultas.add(novo);
    }

    public boolean removerConsulta(Consulta ex){
        return this.consultas.remove(ex);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<Reserva>(this.reservas);
    }

    public void setReservas(List<Reserva> novaLista) throws ModelException{
        if (novaLista == null)
            throw new ModelException("Lista de reservas não pode ser nula!");
        this.reservas = novaLista;
    }

    public boolean adicionarReserva(Reserva novo){
        return this.reservas.add(novo);
    }

    public boolean removerReserva(Reserva ex){
        return this.reservas.remove(ex);
    }

    public static void validarNumRegistro(String numRegistro) throws ModelException {
        if(numRegistro == null || numRegistro.length() < 5){
            throw new ModelException("Número do registro invalido! " + numRegistro);
        }
        if (numRegistro.length() > TAMANHO_MAX_NUM_REGISTRO){
            throw new ModelException("Número do registro excede o tamanho máximo por: " +  (numRegistro.length() - TAMANHO_MAX_NUM_REGISTRO) + " Caracteres!");
        }
        String regex = "[A-Za-zÀ-ÿ0-9\\-']{1," + TAMANHO_MAX_NUM_REGISTRO + "}";
        if(!numRegistro.matches(regex)){
            throw new ModelException("O Número do registro contém caracteres inválidos! " + numRegistro);
        }
    }

    public static void validarTipoProfissional(TipoProfissional tipoProfissional) throws ModelException {
        if(tipoProfissional == null){
            throw new ModelException("Tipo de profissional não pode ser nulo!");
        }
    }

    public static void validarEspecialidade(String especialidade) throws ModelException {
        if(especialidade == null || especialidade.length() == 0){
            throw new ModelException("Especialidade não pode ser nula! " + especialidade);
        }
        if(especialidade.length() > TAMANHO_MAX_ESPECIALIDADE){
            throw new ModelException("Especilidade excede o tamanho máximo por: " + (especialidade.length() - TAMANHO_MAX_ESPECIALIDADE) + " Caracteres!");
        }
        String regex = "[A-Za-zÀ-ÿ \\-']{1," + TAMANHO_MAX_ESPECIALIDADE + "}";
        if(!especialidade.matches(regex)) {
            throw new ModelException("A especialidade contém caracteres inválidos! " + especialidade);
        }
    }
}