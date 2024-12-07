package br.com.alura.tabelafipe.tabelafipe.model;

public class Veiculo {
    private String valor;
    private String marca;
    private String modelo;
    private Integer anoModelo;
    private String combustivel;
    private String mesReferencia;

    public Veiculo(){

    }

    public Veiculo(DadosVeiculo dados){
        this.valor = dados.valor();
        this.marca = dados.marca();
        this.modelo = dados.modelo();
        this.anoModelo = dados.anoModelo();
        this.combustivel = dados.combustivel();
        this.mesReferencia = dados.mesReferencia();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    @Override
    public String toString(){
        return "Modelo: " + getModelo() +
                ", Valor: " + getValor() +
                ", Ano: " + getAnoModelo() +
                ", Combustível: " + getCombustivel() +
                ", Mês de referência: " + getMesReferencia();
    }
}
