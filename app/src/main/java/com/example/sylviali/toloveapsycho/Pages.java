package com.example.sylviali.toloveapsycho;

/**
 * Created by Sylvia Li on 2017/9/20.
 */

public class Pages {
    String pageNum;
    String description = "";
    String choiceA = "";
    String aNum;
    String choiceB = "";
    String bNum;
    String choiceC = "";
    String cNum;

    public Pages (String pageNum, String description, String choiceA, String aNum, String choiceB,
                  String bNum, String choiceC, String cNum) {
        this.pageNum = pageNum;
        this.description = description;
        this.choiceA = choiceA;
        this.aNum = aNum;
        this.choiceB = choiceB;
        this.bNum = bNum;
        this.choiceC = choiceC;
        this.cNum = cNum;
    }

}
