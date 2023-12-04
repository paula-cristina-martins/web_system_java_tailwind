<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*" %>
<%@page import="entidade.usuario" %>
<%@ page import="controle.usuarioAction"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Padrões de Desenvolvimento</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
        <script lang="JavaScript">
            function validarEmail(obj) {
                if (obj.value.indexOf('@') === -1) {
                    window.alert("E-mail Inválido!");
                    obj.value = "exemplo@gmail.com";
                }
            }
        </script>
    </head>
    <body class="bg-gray-100 font-sans">
        <nav>
            <!-- desktop -->
            <div class="hidden md:block bg-gray-800 text-white p-4">
                <div class="flex items-center justify-between pl-10">
                    <h1 class="text-xl uppercase">Projeto Java</h1>
                    <div class="flex items-center pr-10">
                        <a href="index.html" class="mx-4 rounded-3xl py-1 px-3 hover:bg-blue-100 hover:text-gray-800">Início</a>
                        <a href="usuario.jsp" class="mx-4 rounded-3xl py-1 px-3 hover:bg-blue-100 hover:text-gray-800">Usuários</a>
                        <a href="produto.jsp" class="mx-4 rounded-3xl py-1 px-3 hover:bg-blue-100 hover:text-gray-800">Produtos</a>
                    </div>
                </div>
            </div>

            <!-- mobile -->
            <div class="md:hidden bg-gray-800 text-white px-6 py-4">
                <div class="flex items-center justify-between">
                    <h1 class="text-xl uppercase">Projeto Java</h1>
                    <button id="menu-toggle" class="text-white focus:outline-none">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path></svg>
                    </button>
                </div>
                <div id="menu" class="hidden mt-4 ml-2 uppercase">
                    <a href="index.html" class="block py-2">- Início</a>
                    <a href="usuario.jsp" class="block py-2">- Usuários</a>
                    <a href="produto.jsp" class="block py-2">- Produtos</a>
                </div>
            </div>

            <script>
                document.getElementById('menu-toggle').addEventListener('click', function () {
                    document.getElementById('menu').classList.toggle('hidden');
                });
            </script>
        </nav>

        <form id="login_form" method="post" action="usuarioAction" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 lg:mx-32 mt-5">
            <h1 class="text-center text-gray-500 mb-6 text-3xl uppercase">Formulário de Usuários</h1>
            <div class="flex flex-wrap mb-6">
                <div class="flex flex-wrap -mx-3 mb-6">
                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="nome">
                            Nome *
                        </label>
                        <input id="nome" name="nome" type="text" value="<%= (request.getParameter("nome") != null) ? request.getParameter("nome") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="logradouro">
                            Logradouro *
                        </label>
                        <input id="logradouro" name="logradouro" type="text" value="<%= (request.getParameter("logradouro") != null) ? request.getParameter("logradouro") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="complemento">
                            Complemento *
                        </label>
                        <input id="complemento" name="complemento" type="text" value="<%= (request.getParameter("complemento") != null) ? request.getParameter("complemento") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="cpf">
                            CPF *
                        </label>
                        <input id="cpf" name="cpf" type="number" value="<%= (request.getParameter("cpf") != null) ? request.getParameter("cpf") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="numero">
                            Nº *
                        </label>
                        <input id="numero" name="numero" type="text" value="<%= (request.getParameter("numero") != null) ? request.getParameter("numero") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="cidade">
                            Cidade *
                        </label>
                        <input id="cidade" name="cidade" type="text" value="<%= (request.getParameter("cidade") != null) ? request.getParameter("cidade") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="cep">
                            CEP *
                        </label>
                        <input id="cep" name="cep" type="number" value="<%= (request.getParameter("cep") != null) ? request.getParameter("cep") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="bairro">
                            Bairro *
                        </label>
                        <input id="bairro" name="bairro" type="text" value="<%= (request.getParameter("bairro") != null) ? request.getParameter("bairro") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="uf">
                            Estado *
                        </label>
                        <input id="uf" name="uf" type="text" value="<%= (request.getParameter("uf") != null) ? request.getParameter("uf") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="email">
                            E-mail *
                        </label>
                        <input id="email" name="email" type="email" onblur="validarEmail(this)" value="<%= (request.getParameter("email") != null) ? request.getParameter("email") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>
                </div>

                <div class="w-full">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="observacao">
                        Observação
                    </label>
                    <textarea id="observacao" name="observacao" rows="8" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"><%= (request.getParameter("observacao") != null) ? request.getParameter("observacao") : ""%></textarea>
                </div>
            </div>

            <div id="mensagens" class="mt-4 mb-6">
                <% String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {%>
                <div class="sucesso bg-green-100 border-l-4 border-green-500 text-green-700 p-4" role="alert">
                    <p><%= successMessage%></p>
                </div>
                <% } %>

                <% String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {%>
                <div class="erro bg-red-100 border-l-4 border-red-500 text-red-700 p-4" role="alert">
                    <p><%= errorMessage%></p>
                </div>
                <% }%>
            </div>

            <div class="flex items-center justify-between">
                <div id="botoes-container" class="space-x-1 md:space-x-5">
                    <button class="botao-cadastrar bg-blue-700 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded" name="acao" type="submit" value="botao_cadastrar" onclick="validarForm()">
                        Cadastrar
                    </button>
                    <button class="botao-consultar bg-green-700 hover:bg-green-600 text-white font-bold py-2 px-4 rounded" name="acao" type="submit" value="botao_consultar">
                        Consultar
                    </button>
                    <button class="botao-alterar bg-yellow-700 hover:bg-yellow-600 text-white font-bold py-2 px-4 rounded" name="acao" type="submit" value="botao_alterar">
                        Alterar
                    </button>
                </div>
            </div>

        </form>

        <div id="login_form" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 lg:mx-32 my-9">
            <h1 class="text-center text-gray-500 mb-6 text-3xl uppercase">Lista de Usuários</h1>
            <table class="w-full border-collapse border border-gray-400">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="hidden md:table-cell py-2 px-4">CPF</th>
                        <th class="py-2 px-4">Nome</th>
                        <th class="hidden md:table-cell py-2 px-4">CEP</th>
                        <th class="hidden md:table-cell py-2 px-4">Cidade</th>
                        <th class="hidden md:table-cell py-2 px-4">Estado</th>
                        <th class="hidden md:table-cell py-2 px-4">E-mail</th>
                        <th class="py-2 px-4">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<usuario> usuarios = (List<usuario>) request.getAttribute("usuarios");
                        if (usuarios != null && !usuarios.isEmpty()) {
                            for (usuario user : usuarios) {%>
                    <tr class="text-center">
                        <td class="hidden md:table-cell py-2 px-4"><%= user.getCpf()%></td>
                        <td class="py-2 px-4"><%= user.getNome()%></td>
                        <td class="hidden md:table-cell py-2 px-4"><%= user.getCep()%></td>
                        <td class="hidden md:table-cell py-2 px-4"><%= user.getCidade()%></td>
                        <td class="hidden md:table-cell py-2 px-4"><%= user.getUf()%></td>
                        <td class="hidden md:table-cell py-2 px-4"><%= user.getEmail()%></td>
                        <td class="py-2 px-4 block md:flex justify-center md:space-x-2">
                            <form method="post" action="usuarioAction">
                                <input type="hidden" name="cpf" value="<%= user.getCpf()%>">
                                <input type="hidden" name="nome" value="<%= user.getNome()%>">
                                <input type="hidden" name="cep" value="<%= user.getCep()%>">
                                <input type="hidden" name="logradouro" value="<%= user.getLogradouro()%>">
                                <input type="hidden" name="numero" value="<%= user.getNumero()%>">
                                <input type="hidden" name="bairro" value="<%= user.getBairro()%>">
                                <input type="hidden" name="complemento" value="<%= user.getComplemento()%>">
                                <input type="hidden" name="cidade" value="<%= user.getCidade()%>">
                                <input type="hidden" name="uf" value="<%= user.getUf()%>">
                                <input type="hidden" name="observacao" value="<%= user.getObservacao()%>">
                                <input type="hidden" name="email" value="<%= user.getEmail()%>">
                                <input type="hidden" name="senha" value="<%= user.getSenha()%>">
                                <button class="bg-blue-500 hover:bg-blue-400 text-white py-1 px-5 rounded-md" name="acao" type="submit" value="botao_consultar_valor" onclick="validarForm()">
                                    Editar
                                </button>
                            </form>                           
                            <form method="post" action="usuarioAction">
                                <input type="hidden" name="cpf" value="<%= user.getCpf()%>">
                                <button class="bg-red-500 hover:bg-red-400 text-white py-1 px-4 mt-3 md:mt-0 rounded-md" name="acao" type="submit" value="botao_excluir" onclick="validarForm()">
                                    Excluir
                                </button>
                            </form>                           
                        </td>
                    </tr>
                    <% }
                    } else {%>
                    <tr>
                        <td colspan="7" class="">Clique em consultar para obter os resultados.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>