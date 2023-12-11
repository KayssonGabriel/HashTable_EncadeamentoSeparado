package util;

import java.util.ArrayList;
import java.util.Iterator;

public class HashTableEncadeamento {

    private ArrayList<Aluno>[] tabela;
    private final int CAPACIDADE_DEFAULT = 19;

    public HashTableEncadeamento() {
        this.tabela = new ArrayList[CAPACIDADE_DEFAULT];
    }

    public HashTableEncadeamento(int capacidade) {
        this.tabela = new ArrayList[capacidade];
    }

    //Calcula o hash de uma determinada chave. A função de hash é a do método da divisão.
    private int hash(int chave) {
        return chave % this.tabela.length;
    }

    public Aluno get(int chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        if (alunos == null || alunos.isEmpty())
            return null;

        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == chave)
                return aluno;
        }

        return null;
    }

    //Adiciona o par chave, valor na tabela.
    public void put(int chave, Aluno valor) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        if (alunos == null) {
            alunos = new ArrayList<Aluno>();
            alunos.add(valor);
            this.tabela[hash] = alunos;
        } else {
            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).getMatricula() == chave) {
                    alunos.set(i, valor);
                    return;
                }
            }
            alunos.add(valor);
        }
    }

    public Aluno remove(int chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        Iterator<Aluno> it = alunos.iterator();
        Aluno atual = null;

        while (it.hasNext()) {
            atual = it.next();
            if (atual.getMatricula() == chave) {
                it.remove();
                return atual;
            }
        }

        return atual;
    }
}