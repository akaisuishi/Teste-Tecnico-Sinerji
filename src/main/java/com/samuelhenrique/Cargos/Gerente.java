package com.samuelhenrique.Cargos;

import com.samuelhenrique.Funcionario;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
/*SubClasse para o cargo de Gerente que herda atributos da classe Funcionário*/
public class Gerente extends Funcionario {
    public Gerente(String Nome, LocalDate DataContratacao) {
        super(Nome, "Gerente", DataContratacao, 20000);
    }

    @Override
    public double calcularSalario(int ano, Month mes) {
        int anosDeServico = Period.between(DataContratacao, LocalDate.of(ano, mes.getValue(), 1)).getYears();
        return SalarioBase + (3000 * anosDeServico);
    }

    @Override
    public double calcularBeneficios(int ano, Month mes) {
        return 0; // Gerentes não têm benefícios adicionais
    }
}
