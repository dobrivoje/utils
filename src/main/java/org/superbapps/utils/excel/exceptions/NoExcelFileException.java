/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.excel.exceptions;

/**
 *
 * @author DPrtenjak
 */
public class NoExcelFileException extends Exception {

    public NoExcelFileException() {
        super("File is either not an Excel 2003+ version, or is with corrupted structure !");
    }

    public NoExcelFileException(String message) {
        super(message);
    }

}
