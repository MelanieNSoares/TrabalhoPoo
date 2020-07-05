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

> O principal dificuldade do projeto foi ter que programar em um chromebook de espaco muito limitado (muito ruim nao recomendo). Ao desenvolver esse projeto, muito das implementacoes que inicialmente estive em mente foram aperfeicoados ou mudados ao aprender coisas novas no decorrer da disciplina. Algumas mudancas sao:

* Armazenamento da quantidade de pecas em um vetor colors[5]. Tal necessidade surge do fato que o componente canon poderia ter pecas de um tipo nao presente no tabuleiro. Sendo assim, era muito dificil eliminar todas as pecas do tabuleiro ja que sempre sera lancado pecas randomizando no tabuleiro. Agora isso nao acontece e o canon sempre retorna pecas presentes.

* Ao aprender sobre arrays dinamicos, o tabuleiro representado por vTable deixa de ser estatico e passa a ser dinamico. Isso eh importante porque esse vTable vai diminuindo ao longo do jogo e portanto nao eh mais necessario clonar as pecas presentes em vTable para um novo vetor.
~~~java
Antigamente
  public void updateTable(int level, int moves){
    if(level <= 5 && moves == 6 || level > 5 && moves == 5){
      //update vTable
  
      int length = vTable.length;
      vTable = resizeTable(length);
        
      if(GameOver()){
          //game over;
      }
      
    }
  }

  public Piece[][] resizeTable(int length){
       Piece table[][] = new Piece[length-1][7];
       for(int i = 0; i < length - 1;i++){
           for(int j = 0; j < 7; j++){
               table[i][j] = vTable[i][j];
           }
       }
       return table;
   }
~~~
Passa a ser simples
~~~java
  public void updateTable(int level, int moves){
    if(level <= 5 && moves % 6 == 0){
      int length = vTable.size() - 1;
      vTable.remove(length);
  
    }
  }
~~~


   


# Destaques de Código
~~~java
Recursão para verificar os vizinhos da peça lançada e adiciona pecas de tipos iguais a um array dinamico
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

~~~java
Verifica se a posicao olhada ja foi constado no array dinamico
 public boolean checkCombo(int x1, int y1){
        for(int i = 0; i< vCombo.size(); i++){
            if(vCombo.get(i).x == x1 && vCombo.get(i).y == y1){
                return false;
            }
        }
        return true;

    } 
~~~  

~~~java   
 O componente canon possui um queue que lanca sempre a peca da posicao 1;o vetor apenas armazena pecas que estao presentes no tabuleiro
 public void updateCanon(Table table, int lvl){

    temp = qCanon[0];
    qCanon[1] = temp;
    qCanon[0] = randomizer.getPiece(lvl);

    while(qCanon[0].register < 5 && table.colors[qCanon[0].register] == 0){
      qCanon[0] = randomizer.getPiece(lvl);
    }

  }
~~~

~~~java  
metodo que retorna uma peca randomizado; utilizacao de polimorfismo: a partir do nivel 5 pode aparecer uma peca especial no canon.
 public Piece getPiece(){
          int_random = random.nextInt(5);/// utiliza a class Random do java para retornar um numero aleatoria que sera interpretado logo em seguida
          
          rand = interpretPiece(int_random);
    
        return rand;
    
      }

      public Piece getPiece(int lvl){
        if(lvl >= 5){
          int_random = random.nextInt(6);
          rand = interpretPiece(int_random);
        }
        else{
          int_random = random.nextInt(5);
          rand = interpretPiece(int_random);
        }

        return rand;
      }*/
~~~

~~~java    
utilizacao de um vetor onde os indices representam uma das pecas e armazena a quantidade dessas pecas no tabuleiro. Isso posibilita que o canon sempre tera pecas do tabuleiro
public int setTable(int level){
 ...
  colors = new int[5];
 ...
  Piece piece = randomizer.getPiece();
  ...
  (vTable.get(x))[y] = piece;
   colors[piece.register]++;
   ...
~~~

~~~java
Remove a ultima linha do tabuleiro,que eh um array dinamico, tornando o jogo mais dificil
  public void updateTable(int level, int moves){
    if(level <= 5 && moves % 6 == 0){
      int length = vTable.size() - 1;
      vTable.remove(length);
  
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

>
* Adicionar mais niveis

* Adicionar mais peca diferentes

* Implementar mais interacoes de pecas de diferentes tipos

* Arrumar bugs que aparecem no GUI

* Implementar uma maneira de armazenar jogadores que atingiram highscores de pontos

