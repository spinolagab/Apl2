package Main;

import BTree.BST;
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
    private static final HashMap<String, Integer> meses = new HashMap<>();

    static {
        meses.put("Janeiro", 1);
        meses.put("Fevereiro", 2);
        meses.put("Março", 3);
        meses.put("Abril", 4);
        meses.put("Maio", 5);
        meses.put("Junho", 6);
        meses.put("Julho", 7);
        meses.put("Agosto", 8);
        meses.put("Setembro", 9);
        meses.put("Outubro", 10);
        meses.put("Novembro", 11);
        meses.put("Dezembro", 12);
    }


    public static void main(String[] args) {
        AVL avlAgro = new AVL();
        AVL avlDate = new AVL();
        BST bstAgro = new BST();
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
                String mes = vect[0];
                Double ano = Double.parseDouble(vect[1]);
                Double agropecuaria = Double.parseDouble(vect[2]);

                // Cria um objeto PIBData com apenas os dados necessários e adiciona à lista
                PIBData data = new PIBData(meses.get(mes), ano, agropecuaria);
                list.add(data);

                line = br.readLine(); // Lê a próxima linha
            }

            //Inserindo os elementos nas árvores cuja chave é o crescimento no setor agropecuário
            for (PIBData data : list) {
                avlAgro.insertBalancedAsAgro(data);
                bstAgro.insertAsAgro(data);

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
            System.out.println("AVL para dados de agropecuária é uma BST? " + (avlAgro.isAgroBST() ? "SIM!" : "NÃO!"));
            System.out.println("BST para dados de agropecuária é uma BST? " + (bstAgro.isAgroBST() ? "SIM!" : "NÃO!"));
            System.out.println("AVL para dados de data(mês/ano) é uma BST? " + (avlDate.isDateBST() ? "SIM!" : "NÃO!"));
            System.out.println("BST para dados de data(mês/ano) é uma BST? " + (bstDate.isDateBST() ? "SIM!" : "NÃO!"));

            System.out.println("\nElementos inseridos: " + unsortedList.size());

            System.out.println("\nPara " + list.size() + " elementos:");
            System.out.println("Altura mínima = floor(log2(n)) = " + Math.floor(Math.log10(list.size())/Math.log10(2)));
            System.out.println("Altura máxima = n-1: " + (list.size()-1));

            System.out.println("\nAltura da BST de Agro gerada: " + bstAgro.getHeight());
            System.out.println("Altura da AVL de Agro gerada: " + avlAgro.getHeight());

            System.out.println("\nAltura da BST de Datas gerada: " + bstDate.getHeight());
            System.out.println("Altura da AVL de Datas gerada: " + avlDate.getHeight());

            System.out.println("------------------------------------------\n");

            System.out.println("-----------PERGUNTAS EXPLORATÓRIAS-----------");
            //Questao 1. Descobrir maior média anual
            System.out.println("1.Maior crescimento médio anual encontrado: (" +(averageDiffFromYears.firstEntry().getValue()-1) +" e "+averageDiffFromYears.firstEntry().getValue() + ") " + averageDiffFromYears.firstKey() );

            //Questao 2. Descobrir menor média anual
            System.out.println("2.Menor crescimento médio anual encontrado: (" +(averageDiffFromYears.lastEntry().getValue()-1) +" e "+averageDiffFromYears.lastEntry().getValue() + ") " + averageDiffFromYears.lastKey() );

            //Questao 3. Descobrir valores discrepantes
            double maxMonthDecrease = avlAgro.getMaxDecrease();
            double yearFromMaxMonthDecrease = avlAgro.search(maxMonthDecrease).getData().getAno();
            int monthFromMaxDecrease = avlAgro.search(maxMonthDecrease).getData().getMes();

            double maxMonthIncrease = avlAgro.getMaxIncrease();
            double yearFromMaxMonthIncrease = avlAgro.search(maxMonthIncrease).getData().getAno();
            int monthFromMaxIncrease = avlAgro.search(maxMonthIncrease).getData().getMes();

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
            System.out.println("\n  Pandemia(2020-2022):");
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
