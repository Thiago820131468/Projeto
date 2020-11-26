package DAO;

import DTO.UsuarioDTO;
import VIEW.LoginUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();
    /*ESSE MÉTODO IRÁ FAZER A INSERÇÃO DO USUÁRIO NA BANCO ATRAVÉS DA QUERY.*/
    public void cadastrarUsuario(UsuarioDTO objusuariodto){
        
        String sql = "insert into usuario (nome_usuario, senha_usuario) values (?,?)";      
        con = new ConexaoDAO().conectaBD();
        
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getSenha_usuario());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO" + erro);
        }
    }
    
    public boolean LoginUsuario(UsuarioDTO objusuariodto, String login, String senha, int Userid) {
        boolean autenticado = false;
        String sql = "SELECT userId, nome_usuario, senha_usuario FROM usuario WHERE nome_usuario=? and senha_usuario=? and userId=?";
        
        if (!login.isEmpty() && !senha.isEmpty()) {
            try {
                pstm = con.prepareStatement(sql);
               // pstm.setInt(1, userId);
                pstm.setString(1, login);
                pstm.setString(2, senha);
   
                rs = pstm.executeQuery();
                
               
                
                if (rs.next()) {
                    autenticado = true;
                } else {
                    pstm.close();
                    return autenticado;
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null,"UsuarioDAO" + erro);
            }
        }
        return autenticado; 
    }   
    
    /*
    public ArrayList<UsuarioDTO> PesquisarIdUsuario(){
        String sql = "select userId from usuario";
        con = new ConexaoDAO().conectaBD();
        
         
        
        try {
            pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
               UsuarioDTO objusuarioDTO = new UsuarioDTO();
               objusuarioDTO.setUserid(rs.getInt("userId"));
               
               System.out.println(objusuarioDTO.getUserid());
               
               lista.add(objusuarioDTO);
            }
            
        } 
        
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar o id do usuario no banco: "+erro);
        }
        
        return lista;
        
    } 

    */
}
