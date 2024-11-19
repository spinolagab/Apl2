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

            int countEl = 0;
            for (PIBData data : list) {
                avlAgro.insertAsAgro(data);
                avlDate.insertAsDate(data);
                bstAgro.insertAsAgro(data);
                bstDate.insertAsDate(data);
                countEl++;
            }
            System.out.println("Elementos no arquivo: " + list.size());
            System.out.println("Elementos inseridos: " + countEl);
            System.out.println("\nPara " + countEl + " elementos:");
            System.out.println("Altura mínima = floor(log2(n)) = " + Math.floor(Math.log10(countEl)/Math.log10(2)));
            System.out.println("Altura máxima = n-1: " + (countEl-1));
            System.out.println("\nAltura da BST de Agro gerada: " + bstAgro.getHeight());
            System.out.println("Altura da AVL de Agro gerada: " + avlAgro.getHeight());
            System.out.println("\nAltura da BST de Datas gerada: " + bstDate.getHeight());
            System.out.println("Altura da AVL de Datas gerada: " + avlDate.getHeight());

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados numéricos: " + e.getMessage());
        }
    }
}
