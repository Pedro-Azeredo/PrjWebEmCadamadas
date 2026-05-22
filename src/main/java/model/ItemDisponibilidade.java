//
// GRUPO 12
// Componentes: Pedro Henrique de Azeredo Ramos e Matheus de Assis Gonçalves
//
package model;

import jakarta.persistence.*;

@Entity
public class ItemDisponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 13)
    private String diaSemana;

    @Column(nullable=false)
    private String horaInicio;

    @Column(nullable=false)
    private String horaFim;

    @Column(nullable=false)
    private String tempoAtendimento;

    //botar jsonbackreference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_configuraçao_perfil_profissional_saude", nullable = false)
    private ConfiguraçãoPerfilProfissionalSaude configuracaoPerfilProfissionalSaude;

    public ItemDisponibilidade() {
        super();
    }

    public ItemDisponibilidade(String  diaSemana, String horaInicio, String horaFim, String tempoAtendimento, ConfiguraçãoPerfilProfissionalSaude configuraçãoPerfilProfissionalSaude) throws ModelException{
        super();
        this.setDiaSemana(diaSemana);
        this.setHoraInicio(horaInicio);
        this.setHoraFim(horaFim);
        this.setTempoAtendimento(tempoAtendimento);
        this.setConfiguracaoPerfilProfissionalSaude(configuraçãoPerfilProfissionalSaude);
    }

    public Long getId() {
        return id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) throws ModelException {
        ItemDisponibilidade.validarDiaSemana(diaSemana);
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) throws ModelException{
        ItemDisponibilidade.validarHoraInicio(horaInicio);
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) throws ModelException {
        ItemDisponibilidade.validarHoraFim(horaFim);
        this.horaFim = horaFim;
    }

    public String getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(String tempoAtendimento) throws ModelException {
        ItemDisponibilidade.validarTempoAtendimento(tempoAtendimento);
        this.tempoAtendimento = tempoAtendimento;
    }

    public ConfiguraçãoPerfilProfissionalSaude getConfiguracaoPerfilProfissionalSaude() {
        return configuracaoPerfilProfissionalSaude;
    }

    public void setConfiguracaoPerfilProfissionalSaude(ConfiguraçãoPerfilProfissionalSaude configuracaoPerfilProfissionalSaude) throws ModelException{
        ItemDisponibilidade.validarConfiguraçãoPerfilProfissionalSaude(configuracaoPerfilProfissionalSaude);
        this.configuracaoPerfilProfissionalSaude = configuracaoPerfilProfissionalSaude;
        configuracaoPerfilProfissionalSaude.adicionarItemDisponibilidade(this);
    }

    private static void validarDiaSemana(String diaSemana) throws ModelException{
        //A FAZER
    }
    private static void validarHoraInicio(String horaInicio) throws ModelException{
        //A FAZER
    }
    private static void validarHoraFim(String horaFim) throws ModelException{
        //A FAZER
    }
    private static void validarTempoAtendimento(String tempoAtendimento) throws ModelException{
        //A FAZER
    }
    public static void validarConfiguraçãoPerfilProfissionalSaude(ConfiguraçãoPerfilProfissionalSaude configuracaoPerfilProfissionalSaude) throws ModelException {
        if (configuracaoPerfilProfissionalSaude == null)
            throw new ModelException("A configuracao de Perfil do Profissional de Saude não pode ser nula!");
    }
}

