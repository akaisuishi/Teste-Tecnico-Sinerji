package com.samuelhenrique;

import java.time.LocalDate;
import java.time.Month;


/*Classe Funcionário que representa um Objeto com atributos de Nome, Cargo, Data da Contratação e seu Salário Base*/


public abstract class Funcionario {
    protected String Nome;
    protected String Cargo;
    protected LocalDate DataContratacao;
    protected double SalarioBase;
/*Criação do Construtor Funcionário onde será chamado em subclasses que irão herdar seus atributos*/
    public Funcionario(String Nome, String Cargo, LocalDate DataCotratacao, double SalarioBase) {
        this.Nome = Nome;
        this.Cargo = Cargo;
        this.DataContratacao = DataCotratacao;
        this.SalarioBase = SalarioBase;
    }
/*Métodos da classe Funcionário para cálculos*/
    public abstract double calcularSalario(int ano, Month mes);

    public abstract double calcularBeneficios(int ano, Month mes);
}
