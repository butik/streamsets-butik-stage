package ru.butik.streamsets.origin.lib;

import com.streamsets.pipeline.api.Label;

public enum SourceOrigin implements Label {
    NONE("Select source", null),
    CATEGORIES("Categories source", Categories.class);

    private final String origin;
    private final Class<? extends ButikOrigin> destClass;

    SourceOrigin(String origin, Class<? extends ButikOrigin> destClass) {
        this.destClass = destClass;
        this.origin = origin;
    }

    @Override
    public String getLabel() {
        return origin;
    }

    public Class<? extends ButikOrigin> getValue() {
        return destClass;
    }
}
