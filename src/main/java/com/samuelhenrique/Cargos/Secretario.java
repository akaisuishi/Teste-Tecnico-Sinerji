package com.samuelhenrique.Cargos;

import com.samuelhenrique.Funcionario;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
/*SubClasse para o cargo de Secretário que herda atributos da classe Funcionário*/
public class Secretario extends Funcionario {

    public Secretario(String Nome, LocalDate DataCotratacao) {
        super(Nome, "Secretário", DataCotratacao, 7000);
    }

    @Override
    public double calcularSalario(int ano, Month mes) {
        int anosDeServico = Period.between(DataContratacao, LocalDate.of(ano, mes.getValue(), 1)).getYears();
        return SalarioBase + (1000 * anosDeServico) + (0.2 * SalarioBase);
    }

    @Override
    public double calcularBeneficios(int ano, Month mes) {
        return 0.2 * SalarioBase;
    }
}
