package Main;

import BinaryTree.BST;
import AVLTree.AVL;
import entities.PIBData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Main {
    private static final HashMap<String, Integer> months = new HashMap<>();

    static {
        months.put("Janeiro", 1);
        months.put("Fevereiro", 2);
        months.put("Março", 3);
        months.put("Abril", 4);
        months.put("Maio", 5);
        months.put("Junho", 6);
        months.put("Julho", 7);
        months.put("Agosto", 8);
        months.put("Setembro", 9);
        months.put("Outubro", 10);
        months.put("Novembro", 11);
        months.put("Dezembro", 12);
    }


    public static void main(String[] args) {
        AVL avlTax = new AVL();
        AVL avlDate = new AVL();
        BST bstTax = new BST();
        BST bstDate = new BST();
        String path = "./src/readCSV/08-2024_tabelas_PIB_mensal_Mensal04.csv";

        List<PIBData> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

        //Lendo o arquivo
            String line = br.readLine(); // Lê o cabeçalho e ignora
            line = br.readLine(); // Lê a primeira linha de dados
            while (line != null) {
                // Divide a linha em colunas
                String[] vect = line.split(",");
                String month = vect[0];
                Double year = Double.parseDouble(vect[1]);
                Double tax = Double.parseDouble(vect[2]);

                // Cria um objeto PIBData com apenas os dados necessários e adiciona à lista
                PIBData data = new PIBData(months.get(month), year, tax);
                list.add(data);

                line = br.readLine(); // Lê a próxima linha
            }

            //Inserindo os elementos nas árvores cuja chave é o crescimento no setor agropecuário
            for (PIBData data : list) {
                avlTax.insertBalancedAsTax(data);
                bstTax.insertAsTax(data);

            }

            //Embaralhando lista original
                List<PIBData> subListA = list.subList(0, 87);
            List<PIBData> subListB = list.subList(87, 174);
            List<PIBData> subListC = list.subList(174, list.size());
            List<PIBData> unsortedList = new ArrayList<>();

            int unsortedListIdx = 0;
            while(unsortedList.size() < list.size()) {
                unsortedList.add(subListB.get(unsortedListIdx));
                unsortedList.add(subListA.get(unsortedListIdx));
                if(unsortedListIdx < 86)unsortedList.add(subListC.get(unsortedListIdx));
                unsortedListIdx++;
            }

            //Inserindo valores nas árvores cuja chave é a data (mês e ano)
            for (PIBData data : unsortedList) {
                avlDate.insertBalancedAsDate(data);
                bstDate.insertAsDate(data);
            }

            //Salvando médias anuais
            TreeMap<Double,Double> averageDiffFromYears= new TreeMap<>();
            double year = 2004.0;
            while(year < 2024.0){
                averageDiffFromYears.put(avlDate.getAverageFromYear(year) - avlDate.getAverageFromYear(year-1),year);
                year++;
            }


            System.out.println("-----------INFORMAÇÕES INICIAIS-----------");
            System.out.println("AVL para dados de agropecuária é uma BST? " + (avlTax.isTaxBST() ? "SIM!" : "NÃO!"));
            System.out.println("BST para dados de agropecuária é uma BST? " + (bstTax.isTaxBST() ? "SIM!" : "NÃO!"));
            System.out.println("AVL para dados de data(mês/ano) é uma BST? " + (avlDate.isDateBST() ? "SIM!" : "NÃO!"));
            System.out.println("BST para dados de data(mês/ano) é uma BST? " + (bstDate.isDateBST() ? "SIM!" : "NÃO!"));

            System.out.println("\nElementos inseridos: " + unsortedList.size());

            System.out.println("\nPara " + list.size() + " elementos:");
            System.out.println("Altura mínima = floor(log2(n)) = " + Math.floor(Math.log10(list.size())/Math.log10(2)));
            System.out.println("Altura máxima = n-1: " + (list.size()-1));

            System.out.println("\nAltura da BST de taxas gerada: " + bstTax.getHeight());
            System.out.println("Altura da AVL de taxas gerada: " + avlTax.getHeight());

            System.out.println("\nAltura da BST de Datas gerada: " + bstDate.getHeight());
            System.out.println("Altura da AVL de Datas gerada: " + avlDate.getHeight());

            System.out.println("------------------------------------------\n");

            System.out.println("-----------PERGUNTAS EXPLORATÓRIAS-----------");
            //Questao 1. Descobrir maior média anual
            System.out.println("1.Maior crescimento médio anual encontrado: (" +(averageDiffFromYears.lastEntry().getValue()-1) +" e "+averageDiffFromYears.lastEntry().getValue() + ") " + averageDiffFromYears.lastKey() );

            //Questao 2. Descobrir menor média anual
            System.out.println("2.Menor crescimento médio anual encontrado: (" +(averageDiffFromYears.firstEntry().getValue()-1) +" e "+averageDiffFromYears.firstEntry().getValue() + ") " + averageDiffFromYears.firstKey() );

            //Questao 3. Descobrir valores discrepantes
            double maxMonthDecrease = avlTax.getMaxDecrease();
            double yearFromMaxMonthDecrease = avlTax.search(maxMonthDecrease).getData().getYear();
            int monthFromMaxDecrease = avlTax.search(maxMonthDecrease).getData().getMonth();

            double maxMonthIncrease = avlTax.getMaxIncrease();
            double yearFromMaxMonthIncrease = avlTax.search(maxMonthIncrease).getData().getYear();
            int monthFromMaxIncrease = avlTax.search(maxMonthIncrease).getData().getMonth();

            System.out.println("3.Menor média mensal encontrada: (" +monthFromMaxDecrease +"/"+yearFromMaxMonthDecrease + ") " + maxMonthDecrease );
            System.out.println("  Maior média mensal encontrada: (" +monthFromMaxIncrease +"/"+yearFromMaxMonthIncrease + ") " + maxMonthIncrease );

            double average2017And2018 = avlDate.getAverageFromYear(2018) - avlDate.getAverageFromYear(2017);
            double average2018And2019 = avlDate.getAverageFromYear(2019) - avlDate.getAverageFromYear(2018);
            double average2020And2021 = avlDate.getAverageFromYear(2021) - avlDate.getAverageFromYear(2020);
            double average2021And2022 = avlDate.getAverageFromYear(2022) - avlDate.getAverageFromYear(2021);

            System.out.println("\n4.Analisando período pré-pandemia e pandemia.");
            System.out.println("  Pré-Pandemia(2017-2019):");
            System.out.println("   (2017 - 2018): " + average2017And2018);
            System.out.println("   (2018 - 2019): " + average2018And2019);
            System.out.println("Média do período: " + ((average2017And2018 + average2018And2019)/2));
            System.out.println("\n5.Pandemia(2020-2022):");
            System.out.println("   (2020 - 2021): " + average2020And2021);
            System.out.println("   (2021 - 2022): " + average2021And2022);
            System.out.println("Média do período: " + ((average2020And2021 + average2021And2022)/2));          
            System.out.println("---------------------------------------------\n");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados numéricos: " + e.getMessage());
        }
    }
}
