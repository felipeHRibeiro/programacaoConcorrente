class Tarefa implements Runnable {

    // Para saber quem está executando
    private String nomeDaTarefa;

    public Tarefa(String nomeDaTarefa) {
        this.nomeDaTarefa = nomeDaTarefa;
    }

    // O coração da Thread
    @Override
    public void run() {
        System.out.println("Iniciando: " + nomeDaTarefa);

        // Um loop para simular um trabalho em etapas
        for (int i = 1; i <= 3; i++) {
            System.out.println(nomeDaTarefa + " - Processando etapa " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Erro na execução.");
            }
        }

        System.out.println("Concluído: " + nomeDaTarefa);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema Principal iniciou.");

        // Criando as tarefas
        Runnable tarefa1 = new Tarefa("Download A");
        Runnable tarefa2 = new Tarefa("Download B");

        // Criando os trabalhadores
        Thread t1 = new Thread(tarefa1);
        Thread t2 = new Thread(tarefa2);

        // Iniciando execução paralela
        t1.start();
        t2.start();
    }
}
