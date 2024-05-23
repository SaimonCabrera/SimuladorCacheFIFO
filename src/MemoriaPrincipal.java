
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemoriaPrincipal {
    private int[] memoria;

    public MemoriaPrincipal(int tamanho) {
        memoria = new int[tamanho];
    }

    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String linha;
        int index = 0;
        while ((linha = reader.readLine()) != null && index < memoria.length) {
            memoria[index++] = Integer.parseInt(linha.trim(), 2);
        }
        reader.close();
    }

    public int read(int endereco) {
        return memoria[endereco];
    }
}
