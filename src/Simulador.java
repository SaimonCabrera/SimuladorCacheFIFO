import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simulador {
    private MemoriaPrincipal memoriaPrincipal;
    private Cache cache;

    public Simulador(int tamanhoMemoriaPrincipal, int tamanhoCache) {
        memoriaPrincipal = new MemoriaPrincipal(tamanhoMemoriaPrincipal);
        cache = new Cache(tamanhoCache);
    }

    public void loadMemoriaPrincipal(String filename) throws IOException {
        memoriaPrincipal.loadFromFile(filename);
    }

    public void simulacao(String filename) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(filename));
        String linha;
        while ((linha = leitor.readLine()) != null) {
            int endereco = Integer.parseInt(linha.trim(), 2);
            if (cache.leitura(endereco, memoriaPrincipal)) {
                System.out.println("Cache hit no endereço " + linha);
            } else {
                System.out.println("Cache miss no endereço " + linha);
            }
        }
        leitor.close();
        printDados();
    }

    private void printDados() {
        int totalLeituras = cache.getLeituraTotal();
        int hits = cache.getHits();
        int misses = cache.getMisses();
        double hitRate = (double) hits / totalLeituras;
        double missRate = (double) misses / totalLeituras;

        System.out.println("Total de leituras: " + totalLeituras);
        System.out.println("Cache hits: " + hits);
        System.out.println("Cache misses: " + misses);
        System.out.println("Taxa de Hit: " + String.format("%.2f", hitRate));
        System.out.println("Taxa de Miss: " + String.format("%.2f", missRate));
    }

    public static void main(String[] args) {
        try {
            String arquivoMemoriaPrincipal = "MemoriaPrincipal.txt";
            String arquivoLeituras = "Leituras.txt";

            Simulador simulador = new Simulador(32, 8);
            simulador.loadMemoriaPrincipal(arquivoMemoriaPrincipal);
            simulador.simulacao(arquivoLeituras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
