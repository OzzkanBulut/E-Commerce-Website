package com.ozkan.business.service;

import java.util.List;

public interface IProductService <D,E>{

    public D entityToDto(E e);
    public E dtoToEntity(D d);

    public D productServiceCreate(D d);

    public List<D> productServiceList();

    public D productServiceFindById(Long id);

    public D productServiceUpdateById(Long id,D d);

    public D productServiceDeleteById(Long id);
}
