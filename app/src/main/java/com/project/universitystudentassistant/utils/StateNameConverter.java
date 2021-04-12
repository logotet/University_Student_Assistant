package com.project.universitystudentassistant.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class StateNameConverter {

    private static LinkedHashMap<String, String> states;
    private static StateNameConverter stateNameConverter;

    private StateNameConverter() {
            states = new LinkedHashMap<>();
            states.put("AL", "Alabama");
            states.put("AK", "Alaska");
            states.put("AZ", "Arizona");
            states.put("AR", "Arkansas");
            states.put("CA", "California");
            states.put("CO", "Colorado");
            states.put("CT", "Connecticut");
            states.put("DE", "Delaware");
            states.put("DC", "District Of Columbia");
            states.put("FL", "Florida");
            states.put("GA", "Georgia");
            states.put("HI", "Hawaii");
            states.put("ID", "Idaho");
            states.put("IL", "Illinois");
            states.put("IN", "Indiana");
            states.put("IA", "Iowa");
            states.put("KS", "Kansas");
            states.put("KY", "Kentucky");
            states.put("LA", "Louisiana");
            states.put("ME", "Maine");
            states.put("MD", "Maryland");
            states.put("MA", "Massachusetts");
            states.put("MI", "Michigan");
            states.put("MN", "Minnesota");
            states.put("MS", "Mississippi");
            states.put("MO", "Missouri");
            states.put("MT", "Montana");
            states.put("NE", "Nebraska");
            states.put("NV", "Nevada");
            states.put("NH", "New Hampshire");
            states.put("NJ", "New Jersey");
            states.put("NM", "New Mexico");
            states.put("NY", "New York");
            states.put("NC", "North Carolina");
            states.put("ND", "North Dakota");
            states.put("OH", "Ohio");
            states.put("OK", "Oklahoma");
            states.put("OR", "Oregon");
            states.put("PA", "Pennsylvania");
            states.put("RI", "Rhode Island");
            states.put("SC", "South Carolina");
            states.put("SD", "South Dakota");
            states.put("TN", "Tennessee");
            states.put("TX", "Texas");
            states.put("UT", "Utah");
            states.put("VT", "Vermont");
            states.put("VA", "Virginia");
            states.put("WA", "Washington");
            states.put("WV", "West Virginia");
            states.put("WI", "Wisconsin");
            states.put("WY", "Wyoming");
    }

    public static StateNameConverter getInstance(){
        if(stateNameConverter == null){
            stateNameConverter = new StateNameConverter();
        }
        return stateNameConverter;
    }

    public String getStateName(String stateCode){
        return states.entrySet().stream().filter(entry -> entry.getKey().equals(stateCode))
                .map(Map.Entry::getValue).toString();
    }

    public String getStateCode(String stateName){
      return states.entrySet().stream().filter(entry -> entry.getValue().equals(stateName))
                .map(Map.Entry::getKey)
              .findFirst().get();
    }
}
