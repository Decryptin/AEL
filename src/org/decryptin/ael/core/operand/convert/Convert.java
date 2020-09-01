package org.decryptin.ael.core.operand.convert;

public interface Convert {
    String binary      (String s);
    String octal       (String s);
    String decimal     (String s);
    String hexadecimal (String s);
}
