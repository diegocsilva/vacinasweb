/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.utils;

import br.com.imunita.vacinasweb.model.enuns.EnumSexo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Rodolpho
 */
public class GeradorRandom {

    private static final String nomes[] = {"Miguel", "Davi", "Arthur", "Gabriel", "Pedro", "Lucas", "Matheus", "Bernardo", "Rafael",
        "Guilherme", "Enzo", "Felipe", "Gustavo", "Nicolas", "Heitor", "Samuel", "João Pedro", "Pedro Henrique", "Cauã", "Henrique",
        "Murilo", "Eduardo", "Vitor", "Daniel", "Lorenzo", "Vinicius", "Pietro", "João Vitor", "Leonardo", "Théo", "Caio", "Isaac",
        "Lucca", "João", "Davi Lucas", "Enzo Gabriel", "Yuri", "Bryan", "Thiago", "João Gabriel", "Benjamin", "Joaquim", "Emanuel",
        "Sophia", "Julia", "Alice", "Manuela", "Isabella", "Laura", "Maria Eduarda", "Giovanna", "Valentina", "Beatriz", "Luiza",
        "Helena", "Maria Luiza", "Isadora", "Mariana", "Gabriela", "Ana Clara", "Rafaela", "Maria Clara", "Isabelly", "Yasmin",
        "Ana Julia", "Lívia", "Lara", "Lorena", "Heloísa", "Melissa", "Sarah", "Ana Luiza", "Letícia", "Nicole", "Ana Beatriz",
        "Emanuelly", "Esther", "Lavínia", "Marina", "Cecília", "Rebeca", "Vitória", "Maria Fernanda"};

    private static final String sobrenomes[] = {"Ferreira", "Silveira", "Moreira", "Meira", "Teixeira", "Caldeira", "Pinheira",
        "Barreira", "Cabreira", "Costa", "Quarteira", "Parreira", "Caseira", "Roseira", "Porteira", "Monteira", "Soeira", "Mangueira",
        "Castanheira", "Videira", "Banheira", "Cachoeira", "Junqueira", "Siqueira", "Cerqueira", "Madeira", "Vieira", "Nogueira",
        "Limeira", "Figueira", "Macieira", "Carreira", "Palmeira", "Regueira", "Amoreira", "Aroeira", "Gabeira", "Ribeira", "Taveira",
        "Rameira", "Coqueiro", "Carvalheira", "Espinheira", "Laranjeira", "Leiria", "Casqueira", "da Bandeira", "da Silveira",
        "da Madureira", "da Gama", "da Fonseca", "da Fontoura", "da Nóbrega", "da Veiga", "da Maia", "da Silva", "da Costa",
        "da Rocha", "da Terra", "da Cruz", "da Cunha", "da Mata", "da Rosa", "da Mota", "da Paz", "da Luz", "da Conceição", "das Neves",
        "da Barra", "da Penha", "da Barranca", "da Fronteira", "da Aldeia", "Passarinho", "Viveiros", "Ribas", "Matoso", "Brum",
        "Álvares", "Alves", "Fernandes", "Gonçalves", "Rodrigues", "Martins", "Lopes", "Gomes", "Mendes", "Nunes", "Antunes", "Soares",
        "Domingues", "Marques", "Paes", "Guedes", "Peres", "Dias", "Ximenes", "Veles", "Diegues", "Ramires"};

    private static final String palavras[] = {"Percebemos", "cada", "vez", "mais", "que", "o", "julgamento", "imparcial", "das",
        "eventualidades", "promove", "a", "alavancagem", "do", "retorno", "esperado", "a", "longo", "prazo", "No", "mundo", "atual",
        "a", "mobilidade", "dos", "capitais", "internacionais", "garante", "a", "contribuição", "de", "um", "grupo", "importante",
        "na", "determinação", "dos", "modos", "de", "operação", "convencionais", "Todavia", "a", "competitividade", "nas",
        "transações", "comerciais", "exige", "a", "precisão", "e", "a", "definição", "de", "alternativas", "às", "soluções",
        "ortodoxas", "O", "cuidado", "em", "identificar", "pontos", "críticos", "na", "contínua", "expansão", "de", "nossa",
        "atividade", "maximiza", "as", "possibilidades", "por", "conta", "dos", "procedimentos", "normalmente", "adotados", "A",
        "nível", "organizacional", "a", "revolução", "dos", "costumes", "estimula", "a", "padronização", "do", "sistema", "de",
        "formação", "de", "quadros", "que", "corresponde", "às", "necessidades", "É", "claro", "que", "o", "novo", "modelo",
        "estrutural", "aqui", "preconizado", "possibilita", "uma", "melhor", "visão", "global", "do", "fluxo", "de", "informações",
        "Podemos", "já", "vislumbrar", "o", "modo", "pelo", "qual", "o", "comprometimento", "entre", "as", "equipes", "não", "pode",
        "mais", "se", "dissociar", "dos", "paradigmas", "corporativos", "Ainda", "assim", "existem", "dúvidas", "a", "respeito", "de",
        "como", "a", "adoção", "de", "políticas", "descentralizadoras", "aponta", "para", "a", "melhoria", "das", "novas",
        "proposições", "Gostaria", "de", "enfatizar", "que", "o", "início", "da", "atividade", "geral", "de", "formação", "de",
        "atitudes", "faz", "parte", "de", "um", "processo", "de", "gerenciamento", "dos", "níveis", "de", "motivação", "departamental",
        "Assim", "mesmo", "a", "valorização", "de", "fatores", "subjetivos", "causa", "impacto", "indireto", "na", "reavaliação", "do",
        "impacto", "na", "agilidade", "decisória", "Por", "outro", "lado", "a", "percepção", "das", "dificuldades", "oferece", "uma",
        "interessante", "oportunidade", "para", "verificação", "do", "orçamento", "setorial", "Desta", "maneira", "o",
        "desenvolvimento", "contínuo", "de", "distintas", "formas", "de", "atuação", "desafia", "a", "capacidade", "de", "equalização",
        "dos", "conhecimentos", "estratégicos", "para", "atingir", "a", "excelência", "Acima", "de", "tudo", "é", "fundamental",
        "ressaltar", "que", "o", "acompanhamento", "das", "preferências", "de", "consumo", "nos", "obriga", "à", "análise", "dos",
        "relacionamentos", "verticais", "entre", "as", "hierarquias", "O", "incentivo", "ao", "avanço", "tecnológico", "assim", "como",
        "a", "crescente", "influência", "da", "mídia", "prepara-nos", "para", "enfrentar", "situações", "atípicas", "decorrentes",
        "das", "regras", "de", "conduta", "normativas"};

    public static String gerarNome(Integer qtdeSobrenome) {
        Random random = new Random();

        String nome = nomes[random.nextInt(nomes.length)];

        if (qtdeSobrenome > 0) {
            for (int i = 0; i < qtdeSobrenome; i++) {
                nome += " " + sobrenomes[random.nextInt(sobrenomes.length)];
            }
        }

        return nome;
    }

    public static String gerarTexto(Integer qtdePalavras) {
        Random random = new Random();

        String nome = palavras[random.nextInt(palavras.length)];

        if (qtdePalavras > 1) {
            for (int i = 0; i < qtdePalavras; i++) {
                nome += " " + palavras[random.nextInt(palavras.length)];
            }
        }

        return nome;
    }

    public static String gerarEmail() {
        Random random = new Random();

        String email = nomes[random.nextInt(nomes.length)];
        email += sobrenomes[random.nextInt(sobrenomes.length)];
        email += "@gmail.com";
        return email;
    }

    public static Boolean gerarBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static EnumSexo gerarSexo() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return EnumSexo.MASCULINO;
        } else {
            return EnumSexo.FEMININO;
        }
    }

    public static Integer gerarNumero(Integer qtdeRegistros, Integer valorInicial) {
        Random random = new Random(valorInicial);
        return random.nextInt(qtdeRegistros);
    }

    public static Integer gerarNumero(Integer qtdeRegistros) {
        return gerarNumero(qtdeRegistros, 1);
    }

    public static String gerarNumeroTelefone() {
        StringBuilder numero = new StringBuilder();
        numero.append("(");
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append(") ");
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append("-");
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());
        numero.append(gerarNumero(9).toString());

        return numero.toString();
    }

    public static Date gerarData() {
        long offset = Timestamp.valueOf("1970-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return new Date(rand.getTime());
    }
}
