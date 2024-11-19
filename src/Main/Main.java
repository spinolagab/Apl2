package Main;

import BTree.BST;
import AVLTree.AVL;
import entities.PIBData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        BST bst = new BST();
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
                PIBData data = new PIBData(mes, ano, agropecuaria);
                list.add(data);

                line = br.readLine(); // Lê a próxima linha
            }

            int countEl = 0;
            for (PIBData data : list) {
                avl.insertBalanced(data);
                bst.insert(data);
                countEl++;
            }
            System.out.println("Elementos no arquivo: " + list.size());
            System.out.println("Elementos inseridos: " + countEl);
            System.out.println("\nPara " + countEl + " elementos =");
            System.out.println("Altura mínima = floor(log2(n)) = " + Math.floor(Math.log10(countEl)/Math.log10(2)));
            System.out.println("Altura máxima = n-1: " + (countEl-1));
            System.out.println("\nAltura da BST gerada: " + bst.getHeight());
            System.out.println("Altura da AVL gerada: " + avl.getHeight());

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados numéricos: " + e.getMessage());
        }
    }
}
