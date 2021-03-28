package com.logotet.universitystudentassistant.data.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public static UniversityEntityPrep convertToPrepUni(UniversityEntity entity){
        UniversityEntityPrep entityPrep = new UniversityEntityPrep(entity.getName());
        entityPrep.setAddress(entity.getAddress());
        entityPrep.setCity(entity.getCity());
        entityPrep.setState(entity.getState());
        entityPrep.setWebPage(entity.getWebPage());
        return entityPrep;
    }

    public static UniversityEntity convertToMyUni(UniversityEntityPrep entityPrep){
        UniversityEntity entity = new UniversityEntity(entityPrep.getName());
        entity.setAddress(entityPrep.getAddress());
        entity.setCity(entityPrep.getCity());
        entity.setState(entityPrep.getState());
        entity.setWebPage(entityPrep.getWebPage());
        return entity;
    }

    public static List<UniversityEntity> convertToMyUnis(List<UniversityEntityPrep> universityEntityPreps){
        List<UniversityEntity> entities = new ArrayList<>();
        universityEntityPreps.forEach(entityPrep -> entities.add(convertToMyUni(entityPrep)));
        return entities;
    }

    public static List<UniversityEntityPrep> convertToPrepUnis(List<UniversityEntity> universityEntities){
        List<UniversityEntityPrep> entitiesPrep = new ArrayList<>();
        universityEntities.forEach(entity -> entitiesPrep.add(convertToPrepUni(entity)));
        return entitiesPrep;
    }
}