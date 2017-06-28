package Abstractions;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by MARKAN on 28.06.2017.
 */
public interface ParsingFactory<T> {
    ArrayList<T> parseText(String[] text);
}
