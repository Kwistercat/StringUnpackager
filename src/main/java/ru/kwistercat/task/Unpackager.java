package ru.kwistercat.task;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Распаковщик строки, содержащей подстроки.
 *
 * @author Tagir Zalyatdinov
 * @since 16.03.2021
 */

public class Unpackager {
    /**
     * Исходная строка
     */
    private String data;
    /**
     * Стек для чисел
     */
    private Stack numbers = new Stack();
    /**
     * Стек для скобок
     */
    private Stack skobki = new Stack();

    /**
     * Метод проверяет одинаковое количество обратных друг другу скобок.
     *
     * @param data
     * @return - skobki.isEmpty() проверяет стек на пустоту
     */
    public boolean validate(String data) {
        try {
            for (Character ch : data.toCharArray()) {
                if (ch == '[') {
                    skobki.push('[');
                }
                if (ch == ']') {
                    skobki.pop();
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }

        return skobki.isEmpty();
    }


    /**
     * Метод проверяет является ли очередной символ строки числом, буквой или закрывающей скобкой.
     * Если символ является числом - он добавляется в стек чисел.
     * Если символ является буквой - он конкатенируется к подстроке.
     * Если символ является закрывающей скобкой, тогда подстрока повторяется такое число раз,
     * сколько находится на верхушке стека чисел, и число удаляется из стека.
     *
     * @param data
     * @return - конкатенация всех полученных подстрок
     */
    public String unpacking(String data) {
        String result = "";
        String part = "";
        for (Character ch : data.toCharArray()) {
            if (Character.isDigit(ch)) {
                result += part;
                part = "";
                numbers.push(Character.getNumericValue(ch));
            }
            if (Character.isLetter(ch)) {
                part += ch.toString();
            }
            if (ch == ']') {
                part = part.repeat((Integer) numbers.pop());
            }
        }

        result += part;
        return result;
    }

}