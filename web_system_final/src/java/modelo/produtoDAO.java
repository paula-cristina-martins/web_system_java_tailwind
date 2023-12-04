package modelo;

import entidade.produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class produtoDAO {

    public String cadastrarProduto(produto prod) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "INSERT INTO produto (lote, nome, descricao, fornecedor, quantidade, valor_compra, valor_venda) VALUES ("
                        + "'" + prod.getLote() + "','"
                        + prod.getNome() + "','"
                        + prod.getDescricao() + "','"
                        + prod.getFornecedor() + "','"
                        + prod.getQuantidade() + "','"
                        + prod.getValorCompra() + "','"
                        + prod.getValorVenda() + "')";

                preparedStatement = connection.prepareStatement(sql);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Produto cadastrado com sucesso.";
                } else {
                    return "Nenhum registro inserido. Verifique os valores.";
                }
            } catch (Exception e) {
                return "Erro ao cadastrar produto: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

    public String alterarProduto(produto prod) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "UPDATE produto SET "
                        + "nome='" + prod.getNome() + "', "
                        + "descricao='" + prod.getDescricao() + "', "
                        + "fornecedor='" + prod.getFornecedor() + "', "
                        + "quantidade='" + prod.getQuantidade() + "', "
                        + "valor_compra='" + prod.getValorCompra() + "', "
                        + "valor_venda='" + prod.getValorVenda() + "' "
                        + "where lote='" + prod.getLote() + "';";

                preparedStatement = connection.prepareStatement(sql);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Dados do produto atualizados com sucesso.";
                } else {
                    return "Nenhum registro atualizado. Verifique o lote.";
                }
            } catch (Exception e) {
                return "Erro ao atualizar produto: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

    public List<produto> listarProdutos() {
        try {
            List<produto> produtos = new ArrayList<>();
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "SELECT * FROM produto";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    produto prod = new produto();
                    prod.setLote(resultSet.getString("lote"));
                    prod.setNome(resultSet.getString("nome"));
                    prod.setDescricao(resultSet.getString("descricao"));
                    prod.setFornecedor(resultSet.getString("fornecedor"));
                    prod.setQuantidade(resultSet.getString("quantidade"));
                    prod.setValorCompra(resultSet.getString("valor_compra"));
                    prod.setValorVenda(resultSet.getString("valor_venda"));
                    produtos.add(prod);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }

            return produtos;
        } catch (Exception x) {
            x.printStackTrace();
            return null;
        }
    }

    public produto consultarProduto(String lote) {
        produto prod = null;
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "SELECT * FROM produto WHERE lote = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, lote);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    prod = new produto();
                    prod.setLote(resultSet.getString("lote"));
                    prod.setNome(resultSet.getString("nome"));
                    prod.setDescricao(resultSet.getString("descricao"));
                    prod.setFornecedor(resultSet.getString("fornecedor"));
                    prod.setQuantidade(resultSet.getString("quantidade"));
                    prod.setValorCompra(resultSet.getString("valor_compra"));
                    prod.setValorVenda(resultSet.getString("valor_venda"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return prod;
    }

    public String excluirProduto(String lote) {
        try {
            conexao conexaoDB = new conexao();
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = conexaoDB.iniciarConexao();

                String sql = "DELETE FROM produto WHERE lote = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, lote);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return "Produto excluído com sucesso.";
                } else {
                    return "Nenhum registro excluído. Verifique o lote.";
                }
            } catch (Exception e) {
                return "Erro ao excluir produto: " + e.getMessage();
            } finally {
                conexaoDB.fecharConexao(connection, preparedStatement);
            }
        } catch (Exception x) {
            return "Mensagem de erro:" + x.getMessage();
        }
    }

}
