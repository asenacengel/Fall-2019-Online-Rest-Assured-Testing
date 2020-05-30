package com.automation.tests.Murodil_2;

import java.util.List;

public class AllSpartans {

  private   List<POJO_deserialization> spartanList ;

    public AllSpartans() {
    }

    public AllSpartans(List<POJO_deserialization> spartanList) {
        this.spartanList = spartanList;
    }

    public List<POJO_deserialization> getSpartanList() {
        return spartanList;
    }

    public void setSpartanList(List<POJO_deserialization> spartanList) {
        this.spartanList = spartanList;
    }
}
