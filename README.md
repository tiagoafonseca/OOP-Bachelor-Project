# Projeto Rogue POO - ISCTE

Este projeto é um jogo estilo **Rogue** desenvolvido para a unidade curricular de Programação Orientada a Objetos (POO). O motor gráfico utiliza a biblioteca `GraphPack`.

## 🛠️ Arquitetura do Sistema
O backend foi refatorado de um padrão de controlador monolítico para uma **Arquitetura de 3 Camadas**, aumentando a escalabilidade e organização:
* **Controller**: Responsável pela interface e fluxo principal.
* **Service**: Onde reside a lógica de negócio e regras do jogo.
* **Repository**: Camada de persistência e manipulação de dados.

---

## 🚀 Como Correr o Projeto

### Pré-requisitos
* **Java SDK 1.8**: Certifica-te de que o teu ambiente está configurado para o Java 8.
* **Estrutura**: Os comandos abaixo devem ser executados na raiz do repositório.

### 1. Via Terminal (Linha de Comandos)

Para compilar e correr o projeto manualmente, é necessário incluir a biblioteca `GraphPack` e as pastas de recursos no *classpath*.

**No Windows (PowerShell ou CMD):**
```bash
# Compilar o projeto para a pasta bin e correr
java -cp "bin;GraphPack_2022_2023_V1_3\bin;GraphPack_2022_2023_V1_3\src;images;rooms" pt.iscte.poo.example.MainExample
```

### 2. Via VS Code (Interface)

1. Abre a pasta raiz do projeto no VS Code.
2. Navega até o ficheiro: src/pt/iscte/poo/example/MainExample.java.
3. Clica no botão Run que aparece por cima do método main.
4. Certifica-te de que o VS Code está a usar o JDK 1.8 nas definições do projeto.

## 📂 Estrutura de Ficheiros
- **src/:** Contém todo o código fonte Java.
- **images/:** Pasta com os assets gráficos (sprites).
- **rooms/:** Ficheiros de texto que definem o layout dos níveis.
- **GraphPack.../:** Biblioteca gráfica necessária para a execução.

## 👥 Autor
Tiago Afonseca (104624)

