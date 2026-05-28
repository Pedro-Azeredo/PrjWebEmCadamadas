/*
   Grupo 12
   Componentes: Matheus de Assis Gonçalves e Pedro Henrique de Azeredo Ramos
*/

package model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class ItemDisponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false)
    private Duration tempoAtendimento;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "configuracao_perfil_id", nullable = false)
    @JsonBackReference
    private ConfiguraçãoPerfilProfissionalSaude configuracaoPerfilProfissionalSaude;

    public ItemDisponibilidade() {}

    public ItemDisponibilidade(DayOfWeek ds, LocalTime hi, LocalTime hf, Duration ta) throws ModelException {
        setDiaSemana(ds);
        setHoraInicio(hi);
        setHoraFim(hf, hi);
        setTempoAtendimento(ta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek ds) throws ModelException {
        ItemDisponibilidade.validarDiaSemana(ds);
        diaSemana = ds;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime hi) throws ModelException {
        ItemDisponibilidade.validarHoraInicio(hi);
        horaInicio = hi;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime hf, LocalTime hi) throws ModelException {
        this.validarHoraFim(hf, hi);
        horaFim = hf;
    }

    public Duration getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(Duration ta) throws ModelException {
        ItemDisponibilidade.validarTempoAtendimento(ta);
        tempoAtendimento = ta;
    }

    public ConfiguraçãoPerfilProfissionalSaude getConfiguracaoPerfilProfissionalSaude() {
        return configuracaoPerfilProfissionalSaude;
    }

    public void setConfiguracaoPerfilProfissionalSaude(ConfiguraçãoPerfilProfissionalSaude cp) throws ModelException {
        if(cp == null)
            throw new ModelException("A configuração de perfil não pode ser nula!");
        this.configuracaoPerfilProfissionalSaude = cp;
    }

    @Override
    public String toString() {
        return "ItemDisponibilidade [diaSemana=" + diaSemana + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", tempoAtendimento=" + tempoAtendimento + "]";
    }

    public static void validarDiaSemana(DayOfWeek ds) throws ModelException {
        if(ds == null)
            throw new ModelException("O dia da semana não pode ser nulo!");
    }

    public static void validarHoraInicio(LocalTime hi) throws ModelException {
        if(hi == null)
            throw new ModelException("A hora de início não pode ser nula!");
    }

    public static void validarHoraFim(LocalTime hf, LocalTime hi) throws ModelException {
        if(hf == null)
            throw new ModelException("A hora de fim não pode ser nula!");

        if(hi != null && hf.isBefore(hi))
            throw new ModelException("A hora fim não pode ser antes da hora início!");
    }

    public static void validarTempoAtendimento(Duration ta) throws ModelException {
        if(ta == null)
            throw new ModelException("O tempo de atendimento não pode ser nulo!");

        if(ta.isZero() || ta.isNegative())
            throw new ModelException("O tempo de atendimento deve ser maior que zero!");
    }



}
