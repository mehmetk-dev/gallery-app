package com.mehmetkerem.galleryApp.service;

public interface IGenericService<RS, RQ> {

    void delete(long id);

    RS update(Long id, RQ request);

    RS getById(Long id);
}
