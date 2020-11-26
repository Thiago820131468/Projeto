/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DTO.MusicaDTO;
import DTO.GeneroDTO;
import DTO.UsuarioDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MusicaDAO {
    
Connection con;
PreparedStatement pstm;
PreparedStatement pstm2;
ArrayList<MusicaDTO> lista = new ArrayList<>();
//ArrayList<MusicaDTO> lista2 = new ArrayList<>();
//ArrayList<MusicaDTO> lista3 = new ArrayList<>();
/*
    public void cadastrarMusica (MusicaDTO objmusicadto){
        
        String sql = "insert into musicas (nome_Musica) values (?)";      
        con = new ConexaoDAO().conectaBD();
        
        
            try {
                pstm = con.prepareStatement(sql);
                pstm.setString(1,  objmusicadto.getNome_musica());
                
               

                pstm.execute();
                pstm.close();

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir a musica" + erro);
            }
     } 
    */

   public ArrayList<MusicaDTO> puxarMusica(){
       
       String sql = " select * from musicas ";
        con = new ConexaoDAO().conectaBD();
        
        try {
            pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                MusicaDTO objmusicadto = new MusicaDTO();
                objmusicadto.setMusicId(rs.getInt("musicId"));
                objmusicadto.setNome_musica(rs.getString("nome_Musica"));
                
                lista.add(objmusicadto);
            }
        }
       
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao puxa a musica do banco: " + erro );
        }
       return lista;      
    }  
  
//   public ArrayList<MusicaDTO> pegarNomeMusica(int id){
//       
//       String sql = "SELECT `avaliacao`, `nome_Musica`, `genderId`, `usuario`, `musica` FROM `avaliacao` INNER JOIN `musicas` ON `avaliacao`.`musica` = `musicas`.`musicId` AND `musicas`.`musicId` = `avaliacao`.`musica` WHERE `usuario` != ? and `avaliacao` != '0'";
//       //String sql2 = "SELECT musica,  avaliacao, nome_Musica, genderId, usuario FROM avaliacao INNER JOIN musicas ON avaliacao.musica = musicas.musicId AND musicas.musicId = avaliacao.musica WHERE musica = ? ;";
//       con = new ConexaoDAO().conectaBD();  
//       
//        try {
//            pstm = con.prepareStatement(sql);
//            pstm.setInt(1, id);
//            ResultSet rs = pstm.executeQuery();
// 
//            //pstm2 = con.prepareStatement(sql2);
//                
//            //ResultSet rs2 = pstm2.executeQuery();
//             
//            //int musica = rs2.getInt("musica");
//            //objmusicaDTO.setMusicId(musica);
//            //pstm2.setInt(1, musica);
//            
//            /*ENQUANTO A TABELA DO ResultSet ESTIVER COM VALORES CHAMA O MÉTODO SetNome_genero e SetId
//            E ARMAZENA NA VARIÁVEL QUE ESTÁ NA CLASSE GeneroDTO.*/
//            while(rs.next()){ 
//                MusicaDTO objmusicaDTO = new MusicaDTO();
//                String nome = rs.getString("nome_Musica");
//                objmusicaDTO.setNome_musica(nome);
//                //int aval = rs.getInt("avaliacao");
//                //objmusicaDTO.setAvaliacao(aval);           
//           /*ARMAZENA GÊNERO E O SEU ID NUM ARRYLIST.*/
//               lista2.add(objmusicaDTO);
//            }
//            
//        } 
//        
//        catch (SQLException erro) {
//            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: "+erro);
//        }
//        
//        return lista2; 
//    }
//   
   
   
   
   
    

}



