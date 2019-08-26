package cn.jeff.demosecurity;

/**
 * @author swzhang
 * @date 2018/8/22
 * @description
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String s) {
        super(s);
    }
}
