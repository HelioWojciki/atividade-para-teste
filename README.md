# Projeto de Processamento de Dados e Bônus

Aplicação Spring Boot desenvolvida para realizar o processamento de dados de funcionários e departamentos a partir de um arquivo, culminando no **cálculo do departamento campeão de vendas** e na **aplicação de bônus** aos vendedores.

## Funcionalidade Principal

O projeto executa a seguinte sequência lógica na inicialização:

1.  **Leitura de Dados:** Lê informações de um arquivo (`teste.txt`).
2.  **Extração e Mapeamento:** Transforma dados brutos em objetos (`Departamento` e `Funcionario`).
3.  **Cálculo do Campeão:** Determina qual departamento atingiu a maior venda total.
4.  **Aplicação de Bônus:** Aplica a regra de bônus aos vendedores do departamento campeão.

## Execução e Estrutura

### 1. Início do Processamento

A lógica de cálculo é executada automaticamente logo após a inicialização do Spring, usando a interface **`CommandLineRunner`** implementada na classe `ProcessadorDeArquivo`. Isso garante o uso correto da Injeção de Dependência (DI) para acessar os serviços.

### 2. Classes Chave

| Pacote | Classe | Função no Processo |
| :--- | :--- | :--- |
| `service` | `DepartamentoService` | Calcula o departamento campeão. |
| `service` | `AplicarBonusService` | Executa a regra de bonificação. |
| `util` | `LeitorArquivo` | Lida com a leitura de arquivos. |
| `util` | `ExtrairDados` | Converte o texto lido em objetos Java. |
| `main` | `ProcessadorDeArquivo` | Coordena a ordem de execução do processo (o *runner*). |

### 3. Como Rodar

Basta executar a classe principal `GestaoApplication`.
