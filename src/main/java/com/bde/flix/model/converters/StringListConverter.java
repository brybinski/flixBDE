package com.bde.flix.model.converters;
import com.bde.flix.model.History;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

import java.io.Serializable;
import java.util.ArrayList;

@Converter
public class  StringListConverter<Serializable> implements AttributeConverter<ArrayList<Serializable>, String> {

    // TODO: implement this
    @Override
    public String convertToDatabaseColumn(ArrayList<Serializable> i) {
        return "";
    }

    @Override
    public ArrayList<Serializable> convertToEntityAttribute(String string) {
        return new ArrayList<Serializable>();
    }
}
