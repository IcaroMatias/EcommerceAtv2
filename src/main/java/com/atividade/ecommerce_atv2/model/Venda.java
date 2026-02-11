package com.atividade.ecommerce_atv2.model;

public class Venda {
    private int id;
    private String armamento;
    private String cliente;
    private int quantidade;
    private double precoUnitario;
    private double valorTotal;

    public Venda() {}
    public Venda(String armamento, String cliente, int quantidade, double precoUnitario, double valorTotal) {
        this.armamento = armamento;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
    }

    //Getters e Setters

    public int getId() { return id; }
    public void setId(int id) {this.id = id;}
    public String getArmamento() { return armamento; }
    public void setArmamento(String armamento) { this.armamento = armamento; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente;}
    public int getQuantidade() { return quantidade;}
    public void setQuantidade(int quantidade) { this.quantidade = quantidade;}
    public double getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario( double precoUnitario) {this.precoUnitario = precoUnitario;}
    public double getValorTotal() { return valorTotal;}
    public void setValorTotal(double valorTotal) {this.valorTotal = valorTotal;}

}
