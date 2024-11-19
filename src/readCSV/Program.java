package readCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.PIBData;
import java.nio.file.Paths;

public class Program {

    public static void main(String[] args) {

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

            // Imprime os dados lidos
            System.out.println("Dados do PIB (somente Agropecuária):");
            for (PIBData data : list) {
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados numéricos: " + e.getMessage());
        }
    }
}