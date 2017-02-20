package com.epam.task5.parser;

import com.epam.task5.bean.Dish;

import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public interface XMLMenuParser {
    List<Dish> parse(String filePath);
}
