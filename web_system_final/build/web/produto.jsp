<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.sql.*" %>
<%@page import="entidade.produto" %>
<%@ page import="controle.produtoAction"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Padrões de Desenvolvimento</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
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
        <form id="login_form" method="post" action="produtoAction" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 lg:mx-32 mt-5">
            <h1 class="text-center text-gray-500 mb-6 text-3xl uppercase">Formulário de Produtos</h1>
            <div class="flex flex-wrap mb-6">
                <div class="flex flex-wrap -mx-3 mb-6">
                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="lote">
                            Lote *
                        </label>
                        <input id="lote" name="lote" type="text" value="<%= (request.getParameter("lote") != null) ? request.getParameter("lote") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="nome">
                            Nome *
                        </label>
                        <input id="nome" name="nome" type="text" value="<%= (request.getParameter("nome") != null) ? request.getParameter("nome") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="fornecedor">
                            Fornecedor *
                        </label>
                        <input id="fornecedor" name="fornecedor" type="text" value="<%= (request.getParameter("fornecedor") != null) ? request.getParameter("fornecedor") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="quantidade">
                            Quantidade *
                        </label>
                        <input id="quantidade" name="quantidade" type="number" value="<%= (request.getParameter("quantidade") != null) ? request.getParameter("quantidade") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="valor_compra">
                            Valor da Compra *
                        </label>
                        <input id="valor_compra" name="valor_compra" type="number" value="<%= (request.getParameter("valor_compra") != null) ? request.getParameter("valor_compra") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>

                    <div class="w-full md:w-1/2 px-3 mb-3">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="valor_venda">
                            Valor da Venda *
                        </label>
                        <input id="valor_venda" name="valor_venda" type="number" value="<%= (request.getParameter("valor_venda") != null) ? request.getParameter("valor_venda") : ""%>" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>
                </div>
                <div class="w-full">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="descricao">
                        Descrição
                    </label>
                    <textarea id="descricao" name="descricao" rows="8" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"><%= (request.getParameter("descricao") != null) ? request.getParameter("descricao") : ""%></textarea>
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
            <h1 class="text-center text-gray-500 mb-6 text-3xl uppercase">Lista de Produtos</h1>
            <table class="w-full border-collapse border border-gray-400">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="hidden md:table-cell py-2 px-4">Lote</th>
                        <th class="py-2 px-4">Nome</th>
                        <th class="hidden md:table-cell py-2 px-4">Quantidade</th>
                        <th class="hidden md:table-cell py-2 px-4">Valor Compra</th>
                        <th class="hidden md:table-cell py-2 px-4">Valor Venda</th>
                        <th class="py-2 px-4">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<produto> produtos = (List<produto>) request.getAttribute("produtos");
                        if (produtos != null && !produtos.isEmpty()) {
                            for (produto prod : produtos) {%>
                    <tr class="text-center">
                        <td class="hidden md:table-cell py-2 px-4"><%= prod.getLote()%></td>
                        <td class="py-2 px-4"><%= prod.getNome()%></td>
                        <td class="hidden md:table-cell py-2 px-4"><%= prod.getQuantidade()%></td>
                        <td class="hidden md:table-cell py-2 px-4">R$ <%= prod.getValorCompra()%></td>
                        <td class="hidden md:table-cell py-2 px-4">R$ <%= prod.getValorVenda()%></td>
                        <td class="py-2 px-4 block md:flex justify-center md:space-x-2">
                            <form method="post" action="produtoAction">
                                <input type="hidden" name="lote" value="<%= prod.getLote()%>">
                                <input type="hidden" name="nome" value="<%= prod.getNome()%>">
                                <input type="hidden" name="descricao" value="<%= prod.getDescricao()%>">
                                <input type="hidden" name="fornecedor" value="<%= prod.getFornecedor()%>">
                                <input type="hidden" name="quantidade" value="<%= prod.getQuantidade()%>">
                                <input type="hidden" name="valor_compra" value="<%= prod.getValorCompra()%>">
                                <input type="hidden" name="valor_venda" value="<%= prod.getValorVenda()%>">
                                <button class="bg-blue-500 hover:bg-blue-400 text-white py-1 px-5 rounded-md" name="acao" type="submit" value="botao_consultar_valor" onclick="validarForm()">
                                    Editar
                                </button>
                            </form>
                            <form method="post" action="produtoAction">
                                <input type="hidden" name="lote" value="<%= prod.getLote()%>">
                                <button class="bg-red-500 hover:bg-red-400 text-white py-1 px-4 mt-3 md:mt-0 rounded-md" name="acao" type="submit" value="botao_excluir" onclick="validarForm()">
                                    Excluir
                                </button>
                            </form>
                        </td>
                    </tr>
                    <% }
                    } else {%>
                    <tr>
                        <td colspan="6" class="py-2 px-4">Clique em consultar para obter os resultados.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

        </div>
    </body>
</html>