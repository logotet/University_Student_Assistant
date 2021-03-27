package com.project.universitystudentassistant.models;

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
}
