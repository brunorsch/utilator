# Utilator
App Android para controle de gastos com luz e água.

![Demo do funcionamento básico do app](https://i.imgur.com/tfddzkM.gif)

# Features
_Features marcadas já estão implementadas_
- [x] Controle de consumo de luz, baseado na leitura em kWh da informação do medidor de energia;
- [ ] Controle de consumo de água, baseado na leitura em m³ do hidrômetro;
- [ ] Controle de várias contas (Ex: Mais de uma conta de luz para controle, para pessoas que têm uma casa na cidade e outra no interior, p. ex.)
- [ ] Preço por unidade de consumo configurável por conta;
- [X] Input para registro da leitura da informação do medidor de energia/hidrômetro;
- [ ] Histórico de leituras registradas por conta
- [ ] Notificações diárias lembrando de realizar a leitura dos medidores;

# Libs, ferramentas, padrões utilizados, etc.
- Padrão MVVM
- Jetpack Compose
- Room (SQLite)
- Koin
- Material UI 3

# Motivação
Sempre quis desenvolver um app Android que não fosse apenas um front-end para uma aplicação que roda num servidor, mas sim um _old-fashioned_ app android standalone, o qual lida não só com as UIs 
mas com toda a regra de negócio da aplicação e armazenamento dos dados em banco de dados local. 

Desde que o Jetpack Compose v1.0 foi lançado, essa vontade aumentou ainda mais, uma vez que nunca me dei bem com o modelo "antigo" de UIs do Android, com XMLs e afins, diferentemente do Compose, o qual me 
pareceu muito mais convidativo e simples de utilizar. Dessa forma, utilizei a ideia do Utilator para servir como aprendizado para desenvolvimento Android com Jetpack Compose, além de aplicar padrões como
MVVM e a aprender sobre a utilização do ORM Room para interagir com o banco SQLite do app. 

O nome vem da junção de _Utility bills_ (Basicamente conta de água e luz) + _Calculator_.
