# Modelo para Apresentação do Projeto

# Projeto `Bubbles`

# Descrição Resumida do Projeto/Jogo

* Criação de um jogo de niveis utilizando conceitos de programação orientado a objetos e 'cellular automata' para um trabalho de Unicamp.
 
* O jogo consistirá de um canhão controlado pelo jogador que lança peças de diferentes tipos. O jogo terá um tabuleiro pre-determinado com peças, e ao juntar pelo menos 3 peças do mesmo tipo, as mesmas desaparecem. O objetivo entao é eliminar todas as peças do tabuleiro para passar de fase e arrecatar pontos. Diferentes combinações pode ter efeitos indesejáveis. Ao realizar jogadas, uma barra do topo começa a encurtar o tabuleiro e se não houver mais espaço para o canhão lançar peças, perde-se o jogo.

# Equipe
* `Melanie N Soares` - `241997`

# Vídeos do Projeto

## Vídeo da Prévia
> <Coloque um link para o vídeo da prévia do projeto.>

## Vídeo do Jogo
> <Coloque um link para o vídeo em que é demonstrada a versão final do jogo. Esse vídeo deve ter em torno de 5 minutos. Este vídeo não apresenta slides, nem substitui a apresentação final do projeto, que será feita por conferência. Ele mostra apenas o jogo em funcionamento.>

# Slides do Projeto

## Slides da Prévia
`<Coloque um link para os slides da prévia do projeto.>`

## Slides da Apresentação Final
`<Coloque um link para os slides da apresentação final do projeto.>`

## Relatório de Evolução

> <Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.>

# Destaques de Código
Recursão para verificar os vizinhos da peça lançada
~~~java
public int checkNeighbors(int i,int j, int combo, Table table)
    {
        if(i-1>=0 && table.vTable.get(i-1)[j] != null && table.vTable.get(i-1)[j].register == register && checkCombo(i-1,j)){
            vCombo.add(new Positions(i-1,j));
            combo = checkNeighbors(i-1,j,combo+1,table);
        }
        if(j-1 >= 0 && table.vTable.get(i)[j-1] != null && table.vTable.get(i)[j-1].register == register && checkCombo(i,j-1)){
            vCombo.add(new Positions(i,j-1));
            combo=checkNeighbors(i,j-1,combo+1,table);
        }
        if(j+1<=8 && table.vTable.get(i)[j+1] != null && table.vTable.get(i)[j+1].register == register && checkCombo(i,j+1)){
            vCombo.add(new Positions(i,j+1));
            combo=checkNeighbors(i,j+1,combo+1,table);
        }

        if(i+1<= table.vTable.size() && table.vTable.get(i+1)[j] != null && table.vTable.get(i+1)[j].register == register && checkCombo(i+1,j)){
            vCombo.add(new Positions(i+1,j));
            combo=checkNeighbors(i+1,j,combo+1,table);
        }
        
        return combo;
    }
~~~

# Destaques de Pattern
`<Destaque de patterns adotados pela equipe. Sugestão de estrutura:>`

## Diagrama do Pattern
`<Diagrama do pattern dentro do contexto da aplicação.>`

## Código do Pattern
~~~java
// Recorte do código do pattern seguindo as mesmas diretrizes de outros destaques
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

> <Explicação de como o pattern foi adotado e quais suas vantagens, referenciando o diagrama.>

# Conclusões e Trabalhos Futuros

> <Apresente aqui as conclusões do projeto e propostas de trabalho futuro. Esta é a oportunidade em que você pode indicar melhorias no projeto a partir de lições aprendidas e conhecimentos adquiridos durante a realização do projeto, mas que não puderam ser implementadas por questões de tempo. Por exemplo, há design patterns aprendidos no final do curso que provavelmente não puderam ser implementados no jogo -- este é o espaço onde você pode apresentar como aplicaria o pattern no futuro para melhorar o jogo.>
