package com.samuelhenrique;

import com.samuelhenrique.Cargos.Gerente;
import com.samuelhenrique.Cargos.Secretario;
import com.samuelhenrique.Cargos.Vendedor;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Funcionario> funcionarios = new ArrayList<>();

        // Input de funcionários
        System.out.println("Quantos funcionários deseja adicionar?");
        int numFuncionarios = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        for (int i = 0; i < numFuncionarios; i++) {
            System.out.println("Digite o nome do funcionário:");
            String nome = scanner.nextLine();
            System.out.println("Digite o cargo do funcionário (Secretário, Vendedor, Gerente):");
            String cargo = scanner.nextLine();
            System.out.println("Digite a data de contratação (yyyy-mm-dd):");
            LocalDate dataContratacao = LocalDate.parse(scanner.nextLine());

            switch (cargo.toLowerCase()) {
                case "secretário":
                case "secretario":
                    funcionarios.add(new Secretario(nome, dataContratacao));
                    break;
                case "vendedor":
                    funcionarios.add(new Vendedor(nome, dataContratacao));
                    break;
                case "gerente":
                    funcionarios.add(new Gerente(nome, dataContratacao));
                    break;
                default:
                    System.out.println("Cargo inválido.");
                    i--; // Permitir nova tentativa
            }
        }

        // Input de vendas
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Vendedor) {
                Vendedor vendedor = (Vendedor) funcionario;
                System.out.println("Quantas vendas deseja adicionar para " + vendedor.Nome + "?");
                int numVendas = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                for (int j = 0; j < numVendas; j++) {
                    System.out.println("Digite o mês da venda (1-12):");
                    Month mes = Month.of(scanner.nextInt());
                    System.out.println("Digite o ano da venda:");
                    int ano = scanner.nextInt();
                    System.out.println("Digite o valor da venda:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); // Consumir nova linha

                    vendedor.adicionarVenda(new Venda(vendedor.Nome, mes, ano, valor));
                }
            }
        }

        // Solicitar mês e ano para cálculos
        System.out.println("Digite o mês para os cálculos (1-12):");
        Month mes = Month.of(scanner.nextInt());
        System.out.println("Digite o ano para os cálculos:");
        int ano = scanner.nextInt();

        // Realizar cálculos
        double totalPago = calcularTotalPago(funcionarios, mes, ano);
        double totalSalarios = calcularTotalSalarios(funcionarios, mes, ano);
        double totalBeneficios = calcularTotalBeneficios(funcionarios, mes, ano);
        Funcionario maiorSalario = calcularMaiorSalario(funcionarios, mes, ano);
        Funcionario maiorBeneficio = calcularMaiorBeneficio(funcionarios, mes, ano);
        Vendedor maiorVenda = calcularMaiorVenda(funcionarios, mes, ano);

        // Verificar se maiorVenda é nulo antes de acessar seu campo Nome
        if (maiorVenda != null) {
            System.out.println("Vendedor com maior venda em " + mes + " de " + ano + ": " + maiorVenda.Nome);
        } else {
            System.out.println("Nenhum vendedor encontrado com vendas em " + mes + " de " + ano);
        }

        // Exibir resultados
        System.out.println("Total pago em " + mes + " de " + ano + ": " + totalPago);
        System.out.println("Total pago em salários em " + mes + " de " + ano + ": " + totalSalarios);
        System.out.println("Total pago em benefícios em " + mes + " de " + ano + ": " + totalBeneficios);
        System.out.println("Funcionário com maior salário em " + mes + " de " + ano + ": " + maiorSalario.Nome);
        System.out.println("Funcionário com maior benefício em " + mes + " de " + ano + ": " + maiorBeneficio.Nome);
        System.out.println("Vendedor com maior venda em " + mes + " de " + ano + ": " + maiorVenda.Nome);
    }

    public static double calcularTotalPago(List<Funcionario> funcionarios, Month mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcularSalario(ano, mes);
        }
        return total;
    }

    public static double calcularTotalSalarios(List<Funcionario> funcionarios, Month mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.SalarioBase;
        }
        return total;
    }

    public static double calcularTotalBeneficios(List<Funcionario> funcionarios, Month mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcularBeneficios(ano, mes);
        }
        return total;
    }

    public static Funcionario calcularMaiorSalario(List<Funcionario> funcionarios, Month mes, int ano) {
        Funcionario maior = null;
        double maiorSalario = 0;
        for (Funcionario funcionario : funcionarios) {
            double salario = funcionario.calcularSalario(ano, mes);
            if (salario > maiorSalario) {
                maiorSalario = salario;
                maior = funcionario;
            }
        }
        return maior;
    }

    public static Funcionario calcularMaiorBeneficio(List<Funcionario> funcionarios, Month mes, int ano) {
        Funcionario maior = null;
        double maiorBeneficio = 0;
        for (Funcionario funcionario : funcionarios) {
            double beneficio = funcionario.calcularBeneficios(ano, mes);
            if (beneficio > maiorBeneficio) {
                maiorBeneficio = beneficio;
                maior = funcionario;
            }
        }
        return maior;
    }

    public static Vendedor calcularMaiorVenda(List<Funcionario> funcionarios, Month mes, int ano) {
        Vendedor maior = null;
        double maiorVenda = 0;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Vendedor) {
                Vendedor vendedor = (Vendedor) funcionario;
                double totalVendas = 0;
                for (Venda venda : vendedor.getVendas()) {
                    if (venda.getAno() == ano && venda.getMes() == mes) {
                        totalVendas += venda.getValor();
                    }
                }
                if (totalVendas > maiorVenda) {
                    maiorVenda = totalVendas;
                    maior = vendedor;
                }
            }
        }
        return maior;
    }
}