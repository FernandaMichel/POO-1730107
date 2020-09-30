/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upv.poo;

/**
 *
 * @author Fernanda Michel
 */
public class MatrixReaderException extends Exception {
    
    public MatrixReaderException(String message) {
        super();     
    }
    
    public MatrixReaderException (String message, Throwable innerEx){
        super(message, innerEx);
    }
    
}
