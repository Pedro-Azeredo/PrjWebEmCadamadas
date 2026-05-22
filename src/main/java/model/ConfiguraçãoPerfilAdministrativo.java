import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import model.ConfiguraçãoPerfil;
import model.ModelException;


@Entity
public class ConfiguraçãoPerfilAdministrativo extends ConfiguraçãoPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String funcao;


    @OneToMany(mappedBy = "configuracaoPerfilAdministrativo")
    private List<Usuario> usuarios = new ArrayList<>();


    public ConfiguraçãoPerfilAdministrativo(){
    }

    public ConfiguraçãoPerfilAdministrativo(String funcao)  throws ModelException {
        super();
        this.setFuncao(funcao);
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) throws ModelException{
        this.funcao = funcao;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public void setUsuarios(List<Usuario> usuarios) throws ModelException{
        this.usuarios = usuarios;
    }
}