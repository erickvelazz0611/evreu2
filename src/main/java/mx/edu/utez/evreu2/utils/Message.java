package mx.edu.utez.evreu2.utils;

public class Message<T> {
//Respuestas de un servidor
    T data;
    String message;
    boolean error;
    int status;
}
