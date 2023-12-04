package controle;

import entidade.usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.usuarioDAO;

@WebServlet("/usuarioAction")
public class usuarioAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao != null && acao.equals("botao_cadastrar")) {
            cadastrarUsuario(request, response);
        } else if (acao != null && acao.equals("botao_consultar")) {
            listarUsuarios(request, response);
        } else if (acao != null && acao.equals("botao_alterar")) {
            alterarUsuario(request, response);
        } else if (acao.equals("botao_consultar_valor")) {
            consultarUsuario(request, response);
        } else if (acao.equals("botao_excluir")) {
            excluirUsuario(request, response);
        }
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usuario user = new usuario();
            user.setCpf(request.getParameter("cpf"));
            user.setNome(request.getParameter("nome"));
            user.setCep(request.getParameter("cep"));
            user.setLogradouro(request.getParameter("logradouro"));
            user.setNumero(request.getParameter("numero"));
            user.setBairro(request.getParameter("bairro"));
            user.setComplemento(request.getParameter("complemento"));
            user.setCidade(request.getParameter("cidade"));
            user.setUf(request.getParameter("uf"));
            user.setObservacao(request.getParameter("observacao"));
            user.setEmail(request.getParameter("email"));
            user.setSenha(request.getParameter("senha"));

            usuarioDAO usuarioDao = new usuarioDAO();
            String mensagem = usuarioDao.cadastrarUsuario(user);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }

    private void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usuario user = new usuario();
            user.setCpf(request.getParameter("cpf"));
            user.setNome(request.getParameter("nome"));
            user.setCep(request.getParameter("cep"));
            user.setLogradouro(request.getParameter("logradouro"));
            user.setNumero(request.getParameter("numero"));
            user.setBairro(request.getParameter("bairro"));
            user.setComplemento(request.getParameter("complemento"));
            user.setCidade(request.getParameter("cidade"));
            user.setUf(request.getParameter("uf"));
            user.setObservacao(request.getParameter("observacao"));
            user.setEmail(request.getParameter("email"));
            user.setSenha(request.getParameter("senha"));

            usuarioDAO usuarioDao = new usuarioDAO();
            String mensagem = usuarioDao.alterarUsuario(user);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usuarioDAO usuarioDao = new usuarioDAO();
            List<usuario> usuarios = usuarioDao.listarUsuarios();

            if (usuarios != null && !usuarios.isEmpty()) {
                request.setAttribute("usuarios", usuarios);
            } else {
                request.setAttribute("errorMessage", "Nenhum usuário encontrado.");
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Erro ao listar usuários: " + x.getMessage());
        }
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }

    private void consultarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cpf = request.getParameter("cpf");

            usuarioDAO usuarioDao = new usuarioDAO();
            usuario user = usuarioDao.consultarUsuario(cpf);

            if (user != null) {
                request.setAttribute("cpf", user.getCpf());
                request.setAttribute("nome", user.getNome());
                request.setAttribute("logradouro", user.getLogradouro());
                request.setAttribute("complemento", user.getComplemento());
                request.setAttribute("cep", user.getCep());
                request.setAttribute("cidade", user.getCidade());
                request.setAttribute("uf", user.getUf());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("senha", user.getSenha());
                request.setAttribute("observacao", user.getObservacao());
            } else {
                request.setAttribute("errorMessage", "Usuário não encontrado.");
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }

    private void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cpf = request.getParameter("cpf");

            usuarioDAO usuarioDao = new usuarioDAO();
            String mensagem = usuarioDao.excluirUsuario(cpf);

            if (mensagem != null && mensagem.startsWith("Erro")) {
                request.setAttribute("errorMessage", mensagem);
            } else {
                request.setAttribute("successMessage", mensagem);
            }
        } catch (Exception x) {
            request.setAttribute("errorMessage", "Mensagem de erro:" + x.getMessage());
        }
        request.getRequestDispatcher("/usuario.jsp").forward(request, response);
    }
}
