package com.samuelhenrique.Cargos;

import com.samuelhenrique.Funcionario;
import com.samuelhenrique.Venda;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/*SubClasse para o cargo de Vendedor que herda atributos da classe Funcionário*/
public class Vendedor extends Funcionario {
    private List<Venda> vendas;

    public Vendedor(String nome, LocalDate DataContratacao) {
        super(nome, "Vendedor", DataContratacao, 12000);
        this.vendas = new ArrayList<>();
    }

    public List<Venda> getVendas() {
        return vendas;
    }
    public void adicionarVenda(Venda venda) {
        this.vendas.add(venda);
    }


    @Override
    public double calcularBeneficios(int ano, Month mes) {
        double totalVendas = 0;
        for (Venda venda : vendas) {
            if (venda.getAno() == ano && venda.getMes() == mes) { // Correção aqui
                totalVendas += venda.getValor();
            }
        }
        return totalVendas * 0.3; // 30% sobre o valor vendido
    }

    @Override
    public double calcularSalario(int ano, Month mes) {
        int anosDeServico = Period.between(DataContratacao, LocalDate.of(ano, mes.getValue(), 1)).getYears();
        return SalarioBase + (1800 * anosDeServico) + calcularBeneficios(ano, mes);
    }
}
