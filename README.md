## Apresentação

**Projeto:** Web API RESTful para apresentação dos ganhadores do prêmio Pior Filme do Golden Raspberry Awards

**Funcionalidade**: 
Essa solução fornece um serviço que possibilita a leitura e manutenção da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
Retorna como resultado duas listas, uma contendo os diretores premiados no menor intevalo de tempo e outra com os diretores premiados com o maior intervalo de tempo entre os prêmios.

### Como testar
#### API Disponível do Heroku

##### Recursos Providos:
1. HTTP POST, para envio da planilha CSV com os indicados e vencedores:
   https://worst-film-api.herokuapp.com/api/v1/worstfilm

- O arquivo de carga deverá possuir cinco colunas delimitadas por ";" como no exemplo abaixo:
```
year;title;studios;producers;winner
1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes
1980;Cruising;Lorimar Productions, United Artists;Jerry Weintraub;
```

2. HTTP GET, para obentenção dos produtores com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.
   https://worst-film-api.herokuapp.com/api/v1/worstfilm

#### API Disponível para Download no GitHub
Para testar a API será necessário seguir os passos descritos abaixo:

1. Baixar o projeto do repositório https://github.com/ivanparreira/worst-film-api na máquina local.
2. Abrir o projeto utilizando a IDE de preferência.
3. Rodar a aplicação a partir da classe "WorstFilmapiApplication".

##### Recursos Providos:

1. HTTP POST, para envio da planilha CSV com os indicados e vencedores:
   http://localhost:8080/api/v1/worstfilm
- O arquivo de carga deverá possuir cinco colunas delimitadas por ";" como no exemplo abaixo:
```
year;title;studios;producers;winner
1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes
1980;Cruising;Lorimar Productions, United Artists;Jerry Weintraub;
```

2. HTTP GET, para obentenção dos produtores com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.
   http://localhost:8080/api/v1/worstfilm

 
Exemplo de resposta HTTP GET tanto local quanto no Heroku:
```
{
    "min": [
        {
            "producer": "Allen Covert",
            "interval": 1,
            "previousWin": 2011,
            "followingWin": 2012
        },
        {
            "producer": "Wyck Godfrey",
            "interval": 1,
            "previousWin": 2011,
            "followingWin": 2012
        },
        {
            "producer": "Stephenie Meyer and Karen Rosenfelt",
            "interval": 1,
            "previousWin": 2011,
            "followingWin": 2012
        }
    ],
    "max": [
        {
            "producer": "Jerry Weintraub",
            "interval": 18,
            "previousWin": 1980,
            "followingWin": 1998
        }
    ]
}      
```