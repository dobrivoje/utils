package org.superbapps.utils.common.colors;

import java.util.List;

public interface IColorGenerator {

    List generateRGBColor(int colorIndex) throws IndexOutOfBoundsException;

}
