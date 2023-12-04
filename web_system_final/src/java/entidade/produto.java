package entidade;

public class produto {

    private String lote, nome, descricao, fornecedor, quantidade, valorCompra,
            valorVenda;

    public produto() {
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(String valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return "usuario{"
                + "lote=" + lote
                + ", nome=" + nome
                + ", descricao=" + descricao
                + ", fornecedor=" + fornecedor
                + ", quatidade=" + quantidade
                + ", valorCompra=" + valorCompra
                + ", valorVenda=" + valorVenda
                + '}';
    }

}
