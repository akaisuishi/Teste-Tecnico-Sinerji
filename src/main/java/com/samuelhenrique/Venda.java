package com.samuelhenrique;

import java.time.Month;
/*Classe para representação das vendas que serão informadas*/
public class Venda {
    private String vendedor;
    private Month mes;
    private int ano;
    private double valor;

    public Venda(String vendedor, Month mes, int ano, double valor) {
        this.vendedor = vendedor;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;

    }

    public String getVendedor() {
        return vendedor;
    }

    public Month getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }
}
