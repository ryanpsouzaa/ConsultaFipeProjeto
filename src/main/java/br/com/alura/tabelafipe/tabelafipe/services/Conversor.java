package br.com.alura.tabelafipe.tabelafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class Conversor {
    ObjectMapper mapper = new ObjectMapper();

    public <T> T converterDados(String json, Class<T> classe){
        try{
            return mapper.readValue(json, classe);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterLista(String json, Class<T> classe){
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try{
            return mapper.readValue(json, lista);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
