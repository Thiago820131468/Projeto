/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MusicaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author bonet
 */
public class RecomendacaoDAO {
    
Connection con;
PreparedStatement pstm;
//PreparedStatement pstm2;
ArrayList<MusicaDTO> lista = new ArrayList<>();   
ArrayList<MusicaDTO> lista2 = new ArrayList<>();
  
    
    public ArrayList<MusicaDTO> navd(int id){
       
       String sql = "SELECT `avaliacao`, `nome_Musica`, `genderId`, `usuario`, `musica` FROM `avaliacao` INNER JOIN `musicas` ON `avaliacao`.`musica` = `musicas`.`musicId` AND `musicas`.`musicId` = `avaliacao`.`musica` WHERE `usuario` != ? and `avaliacao` != '0'";
       con = new ConexaoDAO().conectaBD();  
       
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
 
       
            //objmusicaDTO.setMusicId(musica);
            //pstm2.setInt(1, musica);
            
            /*ENQUANTO A TABELA DO ResultSet ESTIVER COM VALORES CHAMA O MÉTODO SetNome_genero e SetId
            E ARMAZENA NA VARIÁVEL QUE ESTÁ NA CLASSE GeneroDTO.*/
            while(rs.next()){ 
                MusicaDTO objmusicaDTO = new MusicaDTO();
                String nome = rs.getString("nome_Musica");
                objmusicaDTO.setNome_musica(nome);
                objmusicaDTO.setAvaliacao(rs.getInt("avaliacao"));
           /*ARMAZENA GÊNERO E O SEU ID NUM ARRYLIST.*/
               lista.add(objmusicaDTO);
            }
            
        }catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: "+erro);
        }
        
        return lista; 
    }      

    
    public ArrayList<MusicaDTO> fv (int id){
         String sql = "SELECT `nome_Musica` FROM `favorito` INNER JOIN `musicas` ON `favorito`.`genero` = `musicas`.`genderId` WHERE `usuario` = ? AND `genero` = `genderId`";
         con = new ConexaoDAO().conectaBD();
         MusicaDTO objmusicaDTO = new MusicaDTO();
         try{
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
 
            while(rs.next()){
                String nome = rs.getString("nome_Musica");
                objmusicaDTO.setNome_musica(nome);
                 lista2.add(objmusicaDTO);
                
            }
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao puxar as músicas dos generos favoritos: " + erro);
         }
         
         return lista2;
         
    }
  
}
    