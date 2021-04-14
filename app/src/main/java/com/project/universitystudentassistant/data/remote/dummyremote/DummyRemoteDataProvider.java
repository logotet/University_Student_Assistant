package com.project.universitystudentassistant.data.remote.dummyremote;

import com.project.universitystudentassistant.data.models.UniversityEntity;

import java.util.ArrayList;
import java.util.List;

public class DummyRemoteDataProvider {

    public List<UniversityEntity> getListOFUniversities(){
        List<UniversityEntity> entityList = new ArrayList<>();
        UniversityEntity universityEntity;
        universityEntity = new UniversityEntity("Harvard");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("Stanford");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("Brown");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("UCLA");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("Columbia University");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("University of Utah");
        entityList.add(universityEntity);
        universityEntity = new UniversityEntity("Princeton");
        entityList.add(universityEntity);
        return entityList;
    }
}
