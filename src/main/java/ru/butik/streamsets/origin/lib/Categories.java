package ru.butik.streamsets.origin.lib;

import java.util.Iterator;

public class Categories implements ButikOrigin {
    private final CategoriesServiceImpl service;

    public Categories(CategoriesServiceImpl service) {
        this.service = service;
    }

    @Override
    public Iterator<?> getIterator() {
        return service.getCategories();
    }
}
