package modelo;

import entidade.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO {

    public String cadastrarUsuario(usuario user) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "INSERT INTO usuario (cpf, nome, cep, logradouro, numero, bairro, complemento, cidade, uf, observacao, email, senha) VALUES ("
                        + "'" + user.getCpf() + "','"
                        + user.getNome() + "','"
                        + user.getCep() + "','"
                        + user.getLogradouro() + "','"
                        + user.getNumero() + "','"
                        + user.getBairro() + "','"
                        + user.getComplemento() + "','"
                        + user.getCidade() + "','"
                        + user.getUf() + "','"
                        + user.getObservacao() + "','"
                        + user.getEmail() + "','"
                        + user.getSenha() + "')";

                preparedStatement = connection.prepareStatement(sql);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Usuário cadastrado com sucesso.";
                } else {
                    return "Nenhum registro inserido. Verifique os valores.";
                }
            } catch (Exception e) {
                return "Erro ao cadastrar usuário: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

    public String alterarUsuario(usuario user) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "UPDATE usuario SET "
                        + "nome='" + user.getNome() + "', "
                        + "cep='" + user.getCep() + "', "
                        + "logradouro='" + user.getLogradouro() + "', "
                        + "numero='" + user.getNumero() + "', "
                        + "bairro='" + user.getBairro() + "', "
                        + "complemento='" + user.getComplemento() + "', "
                        + "cidade='" + user.getCidade() + "', "
                        + "uf='" + user.getUf() + "', "
                        + "observacao='" + user.getObservacao() + "', "
                        + "email='" + user.getEmail() + "', "
                        + "senha='" + user.getSenha() + "' "
                        + "where cpf='" + user.getCpf() + "';";
                
                preparedStatement = connection.prepareStatement(sql);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Dados do usuário atualizados com sucesso.";
                } else {
                    return "Nenhum registro atualizado. Verifique o CPF.";
                }
            } catch (Exception e) {
                return "Erro ao atualizar usuário: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

    public List<usuario> listarUsuarios() {
        try {
            List<usuario> usuarios = new ArrayList<>();
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "SELECT * FROM usuario";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    usuario user = new usuario();
                    user.setCpf(resultSet.getString("cpf"));
                    user.setNome(resultSet.getString("nome"));
                    user.setLogradouro(resultSet.getString("logradouro"));
                    user.setNumero(resultSet.getString("numero"));
                    user.setBairro(resultSet.getString("bairro"));
                    user.setComplemento(resultSet.getString("complemento"));
                    user.setCep(resultSet.getString("cep"));
                    user.setCidade(resultSet.getString("cidade"));
                    user.setUf(resultSet.getString("uf"));
                    user.setEmail(resultSet.getString("email"));
                    user.setSenha(resultSet.getString("senha"));
                    user.setObservacao(resultSet.getString("observacao"));
                    usuarios.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }

            return usuarios;
        } catch (Exception x) {
            x.printStackTrace();
            return null;
        }
    }

    public usuario consultarUsuario(String cpf) {
        usuario user = null;
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "SELECT * FROM usuario WHERE cpf = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cpf);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new usuario();
                    user.setCpf(resultSet.getString("cpf"));
                    user.setNome(resultSet.getString("nome"));
                    user.setLogradouro(resultSet.getString("logradouro"));
                    user.setComplemento(resultSet.getString("complemento"));
                    user.setCep(resultSet.getString("cep"));
                    user.setCidade(resultSet.getString("cidade"));
                    user.setUf(resultSet.getString("uf"));
                    user.setEmail(resultSet.getString("email"));
                    user.setSenha(resultSet.getString("senha"));
                    user.setObservacao(resultSet.getString("observacao"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return user;
    }

    public String excluirUsuario(String cpf) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "DELETE FROM usuario WHERE cpf = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cpf);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Usuário excluído com sucesso.";
                } else {
                    return "Nenhum registro excluído. Verifique o CPF.";
                }
            } catch (Exception e) {
                return "Erro ao excluir usuário: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

}
