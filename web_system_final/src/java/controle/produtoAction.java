package controle;

import entidade.produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produtoDAO;

@WebServlet("/produtoAction")
public class produtoAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao != null && acao.equals("botao_cadastrar")) {
            cadastrarProduto(request, response);
        } else if (acao != null && acao.equals("botao_consultar")) {
            listarProdutos(request, response);
        } else if (acao != null && acao.equals("botao_alterar")) {
            alterarProduto(request, response);
        } else if (acao.equals("botao_consultar_valor")) {
            consultarProduto(request, response);
        } else if (acao.equals("botao_excluir")) {
            excluirProduto(request, response);
        }
    }

    private void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            produto prod = new produto();
            prod.setLote(request.getParameter("lote"));
            prod.setNome(request.getParameter("nome"));
            prod.setDescricao(request.getParameter("descricao"));
            prod.setFornecedor(request.getParameter("fornecedor"));
            prod.setQuantidade(request.getParameter("quantidade"));
            prod.setValorCompra(request.getParameter("valor_compra"));
            prod.setValorVenda(request.getParameter("valor_venda"));

            produtoDAO produtoDao = new produtoDAO();
            String mensagem = produtoDao.cadastrarProduto(prod);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/produto.jsp").forward(request, response);
    }

    private void alterarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            produto prod = new produto();
            prod.setLote(request.getParameter("lote"));
            prod.setNome(request.getParameter("nome"));
            prod.setDescricao(request.getParameter("descricao"));
            prod.setFornecedor(request.getParameter("fornecedor"));
            prod.setQuantidade(request.getParameter("quantidade"));
            prod.setValorCompra(request.getParameter("valor_compra"));
            prod.setValorVenda(request.getParameter("valor_venda"));

            produtoDAO produtoDao = new produtoDAO();
            String mensagem = produtoDao.alterarProduto(prod);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/produto.jsp").forward(request, response);
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            produtoDAO produtoDao = new produtoDAO();
            List<produto> produtos = produtoDao.listarProdutos();

            if (produtos != null && !produtos.isEmpty()) {
                request.setAttribute("produtos", produtos);
            } else {
                request.setAttribute("errorMessage", "Nenhum produto encontrado.");
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Erro ao listar produtos: " + x.getMessage());
        }
        request.getRequestDispatcher("/produto.jsp").forward(request, response);
    }

    private void consultarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String lote = request.getParameter("lote");

            produtoDAO produtoDao = new produtoDAO();
            produto prod = produtoDao.consultarProduto(lote);

            if (prod != null) {
                prod.setLote(request.getParameter("lote"));
                prod.setNome(request.getParameter("nome"));
                prod.setDescricao(request.getParameter("descricao"));
                prod.setFornecedor(request.getParameter("fornecedor"));
                prod.setQuantidade(request.getParameter("quantidade"));
                prod.setValorCompra(request.getParameter("valor_compra"));
                prod.setValorVenda(request.getParameter("valor_venda"));
            } else {
                request.setAttribute("errorMessage", "Produto não encontrado.");
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/produto.jsp").forward(request, response);
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String lote = request.getParameter("lote");

            produtoDAO produtoDao = new produtoDAO();
            String mensagem = produtoDao.excluirProduto(lote);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/produto.jsp").forward(request, response);
    }
}
