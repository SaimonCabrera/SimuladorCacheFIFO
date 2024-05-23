import java.util.LinkedList;
import java.util.Queue;

public class Cache {
    private int[] cache;
    private int[] tags;
    private Queue<Integer> fifo;
    private int tamanhoCache;
    private int hits;
    private int misses;

    public Cache(int tamanho) {
        tamanhoCache = tamanho;
        cache = new int[tamanho];
        tags = new int[tamanho];
        fifo = new LinkedList<>();
        for (int i = 0; i < tamanho; i++) {
            tags[i] = -1;
        }
        hits = 0;
        misses = 0;
    }

    public boolean leitura(int endereco, MemoriaPrincipal memoriaPrincipal) {
        for (int tag : tags) {
            if (tag == endereco) {
                hits++;
                return true; // Cache hit
            }
        }

        misses++;
        if (fifo.size() >= tamanhoCache) {
            int indexRemovido = fifo.poll();
            tags[indexRemovido] = -1;
        }

        int indexVazio = fifo.size();
        fifo.add(indexVazio);
        tags[indexVazio] = endereco;
        cache[indexVazio] = memoriaPrincipal.read(endereco);
        return false; // Cache miss
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getLeituraTotal() {
        return hits + misses;
    }
}
