import java.util.Random;

public class TabelaHash90 {

    public static void main(String[] args) {

        int[] tabela = new int[100];
        boolean[] ocupado = new boolean[100];

        int[] valoresInseridos = new int[100];
        int[] colisoesElemento = new int[100];

        Random random = new Random(1090);

        int quantidade = 0;
        int totalColisoes = 0;

        // Inserção dos elementos
        for (int i = 0; i < 100; i++) {

            // Impede novas inserções quando atingir 90%
            if (quantidade >= 90) {
                break;
            }

            int valor = random.nextInt(100001);
            int posicao = valor % 100;
            int colisoes = 0;

            // Procura uma posição livre
            while (ocupado[posicao]) {

                colisoes++;
                totalColisoes++;

                posicao = (posicao + 1) % 100;
            }

            // Insere o elemento
            tabela[posicao] = valor;
            ocupado[posicao] = true;

            valoresInseridos[quantidade] = valor;
            colisoesElemento[quantidade] = colisoes;

            quantidade++;
        }

        // Exibe a quantidade de elementos inseridos
        System.out.println("===== RESULTADOS =====");

        System.out.println("Elementos inseridos: " + quantidade);

        System.out.println(
                "Ocupacao da tabela: " + quantidade + "%"
        );

        System.out.println(
                "Total de colisoes: " + totalColisoes
        );

        // Encontra os tres elementos que mais colidiram
        System.out.println("\nTres elementos que mais colidiram:");

        int primeiro = -1;
        int segundo = -1;
        int terceiro = -1;

        for (int i = 0; i < quantidade; i++) {

            if (primeiro == -1 ||
                    colisoesElemento[i] > colisoesElemento[primeiro]) {

                terceiro = segundo;
                segundo = primeiro;
                primeiro = i;

            } else if (segundo == -1 ||
                    colisoesElemento[i] > colisoesElemento[segundo]) {

                terceiro = segundo;
                segundo = i;

            } else if (terceiro == -1 ||
                    colisoesElemento[i] > colisoesElemento[terceiro]) {

                terceiro = i;
            }
        }

        if (primeiro != -1) {
            System.out.println(
                    "Valor: " + valoresInseridos[primeiro]
                            + " | Colisoes: " + colisoesElemento[primeiro]
            );
        }

        if (segundo != -1) {
            System.out.println(
                    "Valor: " + valoresInseridos[segundo]
                            + " | Colisoes: " + colisoesElemento[segundo]
            );
        }

        if (terceiro != -1) {
            System.out.println(
                    "Valor: " + valoresInseridos[terceiro]
                            + " | Colisoes: " + colisoesElemento[terceiro]
            );
        }

        // Mostra as posicoes livres
        System.out.println("\nPosicoes livres na tabela:");

        int livres = 0;

        for (int i = 0; i < 100; i++) {

            if (!ocupado[i]) {
                System.out.print(i + " ");
                livres++;
            }
        }

        System.out.println("\nTotal de posicoes livres: " + livres);
    }
}