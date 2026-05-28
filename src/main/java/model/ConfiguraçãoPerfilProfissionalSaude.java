/*
   Grupo 12
   Componentes: Matheus de Assis Gonçalves e Pedro Henrique de Azeredo Ramos
*/

package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class ConfiguraçãoPerfilProfissionalSaude extends ConfiguraçãoPerfil {
    public static final int  TAMANHO_MIN_ESPECIALIDADE = 2, TAMANHO_MAX_ESPECIALIDADE = 80, TAMANHO_MIN_NUM_REGISTRO = 2, TAMANHO_MAX_NUM_REGISTRO = 30;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true, length = TAMANHO_MAX_NUM_REGISTRO)
    private String numRegistro;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private TipoProfissional tipoProfissional;

    @Column (nullable = false, length = TAMANHO_MAX_ESPECIALIDADE)
    private String especialidade;

    @OneToMany (mappedBy = "configuracaoPerfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List <Consulta> listaConsultas = new ArrayList <> ();

    @OneToMany (mappedBy = "configuracaoPerfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List <Reserva> listaReservas = new ArrayList <> ();

    @OneToMany (mappedBy = "configuracaoPerfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List <ItemDisponibilidade> listaItensDisponibilidade = new ArrayList <> ();

    public ConfiguraçãoPerfilProfissionalSaude () {}
    public ConfiguraçãoPerfilProfissionalSaude (String nr, TipoProfissional tp, String esp, ItemDisponibilidade item) throws ModelException {
        setNumRegistro (nr);
        setTipoProfissional (tp);
        setEspecialidade (esp);
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getNumRegistro () {
        return numRegistro;
    }

    public void setNumRegistro (String nr) throws ModelException {
        ConfiguraçãoPerfilProfissionalSaude.validarNumRegistro (nr);

        numRegistro = nr;
    }

    public TipoProfissional getTipoProfissional () {
        return tipoProfissional;
    }

    public void setTipoProfissional (TipoProfissional tp) throws ModelException {
        ConfiguraçãoPerfilProfissionalSaude.validarTipoProfissional (tp);

        tipoProfissional = tp;
    }

    public String getEspecialidade () {
        return especialidade;
    }

    public void setEspecialidade (String esp) throws ModelException {
        ConfiguraçãoPerfilProfissionalSaude.validarEspecialidade (esp);

        especialidade = esp;
    }

    public List <ItemDisponibilidade> getItensDisponibilidade () {
        return new ArrayList <> (listaItensDisponibilidade);
    }

    public void setItensDisponibilidade (List <ItemDisponibilidade> novaLista) throws ModelException {
        if (novaLista == null)
            throw new ModelException ("A lista de itens de disponibilidade não pode ser nula!");

        listaItensDisponibilidade = novaLista;
    }

    public List <Consulta> getListaConsultas () {
        return new ArrayList <Consulta> (listaConsultas);
    }

    public void setListaConsultas (List <Consulta> novaLista) throws ModelException {
        if (novaLista == null)
            throw new ModelException ("A lista de consultas não pode ser nula!");

        listaConsultas = novaLista;
    }

    public boolean adicionarListaConsultas (Consulta nova) {
        return listaConsultas.add (nova);
    }

    public boolean removerListaConsultas (Consulta ex) {
        return listaConsultas.remove (ex);
    }

    public boolean adicionarListaItensDisponibilidade (ItemDisponibilidade nova) {
        return listaItensDisponibilidade.add (nova);
    }

    public boolean removerListaItensDisponibilidade (ItemDisponibilidade ex) {
        return listaItensDisponibilidade.remove (ex);
    }

    public List <Reserva> getListaReservas () {
        return new ArrayList <Reserva> (listaReservas);
    }

    public void setListaReservas (List <Reserva> novaLista) throws ModelException {
        if (novaLista == null)
            throw new ModelException ("A lista de reservas não pode ser nula!");

        listaReservas = novaLista;
    }

    public boolean adicionarListaReserva (Reserva nova) {
        return listaReservas.add (nova);
    }

    public boolean removerListaReserva (Reserva ex) {
        return listaReservas.remove (ex);
    }

    @Override
    public String toString () {
        return "ConfiguracaoPerfilProfissionalSaude [numRegistro=" + numRegistro + ", tipoProfissional=" + tipoProfissional + ", especialidade=" + especialidade + "]";
    }

    public static void validarNumRegistro (String nr) throws ModelException {
        if (nr == null || nr.isBlank ())
            throw new ModelException ("O número de registro não pode ser nulo ou vazio!");

        if (nr.length () < TAMANHO_MIN_NUM_REGISTRO || nr.length () > TAMANHO_MAX_NUM_REGISTRO)
            throw new ModelException ("O número de registro deve possuir entre " + TAMANHO_MIN_NUM_REGISTRO + " e "+ TAMANHO_MAX_NUM_REGISTRO + " caracteres!");

        String regex = "^[A-Za-z0-9\\-./]+$";

        if (! nr.matches (regex))
            throw new ModelException ("O número de registro contém caracteres inválidos!");
    }

    public static void validarTipoProfissional (TipoProfissional tp) throws ModelException {
        if (tp == null)
            throw new ModelException ("O tipo profissional não pode ser nulo!");
    }

    public static void validarEspecialidade (String esp) throws ModelException {
        if (esp == null || esp.isBlank ())
            throw new ModelException ("A especialidade não pode ser nula ou vazia!");

        if (esp.length () < TAMANHO_MIN_ESPECIALIDADE || esp.length () > TAMANHO_MAX_ESPECIALIDADE)
            throw new ModelException ("A especialidade deve possuir entre " + TAMANHO_MIN_ESPECIALIDADE + " e "+ TAMANHO_MAX_ESPECIALIDADE + " caracteres!");

        String regex = "[A-Za-zÀ-ÿ \\-\\']{2,80}";

        if (! esp.matches (regex))
            throw new ModelException ("A especialidade contém caracteres inválidos!");
    }
}