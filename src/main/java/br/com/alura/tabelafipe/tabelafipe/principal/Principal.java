package br.com.alura.tabelafipe.tabelafipe.principal;

import br.com.alura.tabelafipe.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.tabelafipe.model.DadosModelo;
import br.com.alura.tabelafipe.tabelafipe.model.DadosVeiculo;
import br.com.alura.tabelafipe.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.tabelafipe.services.ConsumoApi;
import br.com.alura.tabelafipe.tabelafipe.services.Conversor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoApi consumo;
    private Conversor conversor;
    private Scanner leitura;
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1";

    public Principal(){
        this.consumo = new ConsumoApi();
        this.conversor = new Conversor();
        this.leitura = new Scanner(System.in);
    }

    public void iniciarMenu(){

        //selecionando a categoria do automóvel

        System.out.println("""
                Digite a categoria de automóvel:
                - Caminhão
                - Carro
                - Moto
                """);

        var opcaoCategoria = leitura.nextLine();
        String endereco;

        if(opcaoCategoria.toUpperCase().contains("CAMI")){
            endereco = ENDERECO + "/caminhoes/marcas";

        }else if(opcaoCategoria.toUpperCase().contains("CARR")){
            endereco = ENDERECO + "/carros/marcas";

        } else{
            endereco = ENDERECO + "/motos/marcas";
        }

        //selecionando a marca

        String json = consumo.obterJson(endereco);
        List<Dados> listaMarcas = conversor.obterLista(json, Dados.class);

        listaMarcas.stream()
                .sorted(Comparator.comparing(Dados :: nome))
                .forEach(m -> System.out.println(
                        "Marca: " + m.nome() + " -> Código: " + m.codigo()
                ));

        //selecionando o modelo

        System.out.println("\nDigite o código da marca que deseja filtrar: ");
        var codigo = leitura.nextLine();
        endereco = endereco + "/" + codigo + "/modelos";

        json = consumo.obterJson(endereco);
        DadosModelo listaModelos = conversor.converterDados(json, DadosModelo.class);


        //selecionando o ano do modelo

        String modeloNome;

        if(listaModelos.modelos().size() == 1){
            modeloNome = listaModelos.modelos().get(0).nome();

        } else{
            listaModelos.modelos().stream()
                    .sorted(Comparator.comparing(Dados :: nome))
                    .forEach(m -> System.out.println(
                            "Nome: " + m.nome() + " -> Código: " + m.codigo())
                    );

            System.out.println("\nDigite o nome do modelo que deseja buscar: ");
            modeloNome = leitura.nextLine();
        }

        List<Dados> modelosFiltrados = listaModelos.modelos().stream()
                .filter(m -> m.nome().toUpperCase().contains(modeloNome.toUpperCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelo(s) encontrado(s):");
        modelosFiltrados.forEach(m -> System.out.println(
                "Nome: " + m.nome() + " -> código: " + m.codigo()
        ));

        if(modelosFiltrados.size() == 1){
            codigo = modelosFiltrados.get(0).codigo();

        } else{
            System.out.println("Digite o código do modelo desejado: ");
            codigo = leitura.nextLine();
        }

        endereco = endereco + "/" + codigo + "/anos";

        json = consumo.obterJson(endereco);
        List<Dados> listaAnos = conversor.obterLista(json, Dados.class);

        List<Veiculo> listaVeiculosConsultados = new ArrayList<>();
        for(int i = 0; i < listaAnos.size(); i++){
            String enderecoVeiculos = endereco + "/" + listaAnos.get(i).codigo();
            String jsonVeiculo = consumo.obterJson(enderecoVeiculos);
            Veiculo veiculoElemento = new Veiculo(conversor.converterDados(jsonVeiculo, DadosVeiculo.class));

            listaVeiculosConsultados.add(veiculoElemento);
        }

        listaVeiculosConsultados.forEach(System.out::println);

//        System.out.println("\nAno(s) do modelo: ");
//        listaAnos.forEach(m -> System.out.println(
//                m.nome() + " -> Código: " + m.codigo()
//        ));

//        System.out.println("Por fim, digite o código do ano desejado do veículo: ");
//        codigo = leitura.nextLine();
//
//        endereco = endereco + "/" + codigo;
//
//        json = consumo.obterJson(endereco);
//
//        DadosVeiculo modeloConsultado = conversor.converterDados(json, DadosVeiculo.class);
//        Veiculo veiculoConsultado = new Veiculo(modeloConsultado);
//
//        System.out.println(veiculoConsultado);

    }
}
