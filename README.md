# AGF-Lang : A Grande Família - lang #

A linguagem da Grande Família

## Instruções de execução ##`

```bash
java -jar agf-lang.jar testes/expressoes.agf -m
```

### Windows ###

#### Compilando manualmente ####

```bash
javac -d . .\src\agf\*.java
```

#### Executando Manualmente ####

```bash
java .\agf\Main testes\expressoes.agf
```

### Linux ###

#### Compilando manualmente e Executando Manualmente ####

```bash
javac -d . ./src/agf/*.java && java agf/Main testes/expressoes.agf
```

#### Compilando manualmente (linux) ####

```bash
javac -d . ./src/agf/*.jav
```

#### Executando Manualmente (linux) ####

```bash
java agf/Main testes/expressoes.agf
```

### Executando ###

```bash
java -jar agf-lang.jar .\testes\expressoes.agf
```

```bash
java -jar agf-lang.jar testes/expressoes.agf
```

## Variáveis ##

As variáveis da Linguagem de Programação AGF contam com um sistema de validação de tipo dentre outros necessários para uma linguagem de programação e possuem como respectivos tipos aos do Java de acordo com a  tabela abaixo:

|  **AGF** | **Java** |
|:--------:|:--------:|
|   bebel  |    int   |
| mendonca |   float  |

Para declarar uma variável, deve-se definir o tipo seguido de dois pontos ( : ) após isso o nome da variável e ponto e virgula ( ; ) no final.
Caso queira atribuir um valor à uma variável criada, basta fazer como no exemplo abaixo:

```code
bebel:exemplo=1;
```

Caso não defina um valor por padrão as variáveis são iniciadas da seguinte forma:
|  **AGF** | **Valor** |
|:--------:|:---------:|
|   bebel  |     0     |
| mendonca |    0,0    |

As variáveis podem ter o nome do seu tipo alterado em PalavrasReservadas.java

## Palavras Reservadas ##

AGF conta com um sistema de palavras reservadas que podem ser alteradas, as palavras reservadas da linguagem são:

- **bebel**: declaração de uma variável do tipo inteiro;

```AGF
#catucaPai#
bebel:nomeDoInteiro;
#catucaMae#
```

- **mendonca**: declaração de uma variável do tipo float;

```AGF
#catucaPai#
mendonca:nomeDoFloat;
#catucaMae#
```

- **carrarinha** (**carrarinhaln**): impressão de string ou variável (impressão de string ou variável com quebra de linha);

```AGF
#catucaPai#
carrarinha|"Isso é um print"|;
carrarinhaln|"Isso é um print com quebra de linha"|;
#catucaMae#
```

- **tuco**: Atribuição de valor à uma variável via teclado;

```AGF
#catucaPai#
tuco|nomeDoInteiro|;
#catucaMae#
```

TODO:

- **lineu**: Laço de repetição equivalente à um while;

```AGF
#catucaPai#
lineu|nomeInteiro < 10|{;
    carrarinha|"Repeticao: "|;
    carrarinhaln|nomeInteiro|;
};
#catucaMae#
```

- **beicola** (**etelvina**): Estrutura condicional equivalente à um if (estrutura condicional equivalente à um else);

```AGF
#catucaPai#
beicola|nomeInteiro == 10| {;
    carrarinhaln|"Inteiro = 10"|;
};
etelvina {;
    carrarinhaln|"Inteiro != 10"|;
};
#catucaMae#
```
