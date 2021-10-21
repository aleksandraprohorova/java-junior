package com.acme.edu.messages;

import com.acme.edu.common.Message;
import com.acme.edu.common.Printer;

public class StringMessage implements Message {
    public void setValue(String value) {
        this.value = value;
    }

    private String value;
    private int counter;

    public StringMessage(String value) {
        this.value = value;
        counter = 1;
    }
    @Override
    public String getDecoratedString() {
        if (counter > 1) {
            return "string: " + value + " (x" + counter + ")";
        } else {
            return "string: " + value;
        }
    }

    @Override
    public void clear() {
        value = "";
        counter = 1;
    }

    @Override
    public boolean isSameType(Message message) {
        return (message instanceof StringMessage);
    }

    @Override
    public void accumulate(Message message, Printer printer) {
        if (!(message instanceof StringMessage)) {
            throw new IllegalArgumentException("Can not accumulate message which is not type of StringMessage");
        }
        StringMessage stringMessage = (StringMessage) message;
        if (value.equals(stringMessage.value)) {
            counter++;
        } else {
            printer.print(this);
            value = stringMessage.value;
            counter = 1;
        }
    }


}
