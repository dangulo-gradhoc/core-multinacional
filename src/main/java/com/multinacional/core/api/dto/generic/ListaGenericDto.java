package com.multinacional.core.api.dto.generic;

import java.util.List;

import lombok.Data;

@Data
public class ListaGenericDto<S> {

    public Long total;
    public List<S> lista;
}
