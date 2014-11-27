package com.cummins.mowo.representation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class TimecardListRepresentation {

    private List<TimecardRepresentation> list;

    public List<TimecardRepresentation> getList() {
        if (list == null) {
            list = new ArrayList<TimecardRepresentation>();
        }
        return list;
    }

    public void setList(List<TimecardRepresentation> list) {
        this.list = list;
    }

}