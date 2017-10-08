package com.example.sylviali.toloveapsycho;

import java.util.Comparator;

/**
 * Created by Sylvia Li on 2017/10/3.
 */

public class PageComparator implements Comparator<Page> {
    @Override
    public int compare(Page page, Page t1) {
        return page.compareTo(t1);
    }
}
