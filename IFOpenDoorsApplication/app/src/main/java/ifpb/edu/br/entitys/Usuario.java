package ifpb.edu.br.entitys;

/**
 * Created by leo on 26/08/16.
 */
public class Usuario {
    protected String email;
    protected String senha;


    public Usuario(){
        email= this.email;
        senha=this.senha;
    }


    public void setEmail(String email){
        email=this.email;
    }

    public String getEmail(){

        return email;
    }

    public void setSenha(String senha){
        email=this.senha;
    }

    public String getSenha(){

        return senha;
    }



}
