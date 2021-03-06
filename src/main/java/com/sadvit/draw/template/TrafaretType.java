package com.sadvit.draw.template;

public enum TrafaretType {

    SOLID, DASHED, DASH_DOTTED;

    @Override
    public String toString() {
        switch (this) {
            case DASH_DOTTED:
                return "Точка тире";
            case DASHED:
                return "Пунктирная";
            case SOLID:
                return "Сплошная";
            default:
                break;
        }
        return null;
    }
}
