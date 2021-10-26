package demo.ooad;

public class StringMessage implements Message {
    private String body;

    public StringMessage(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return "string: " + this.body;
    }

    @Override
    public boolean isSameType(Message message) {
        return message instanceof StringMessage;
    }

    @Override
    public Message accumulate(Message message) {
        if (message instanceof StringMessage) {
//        StringMessage newMessage = (StringMessage)message; //ClassCastException
        } else {
            throw new IllegalArgumentException("");
        }
        return null;
    }
}

class Girl {
    public int getAge() {
        return 18;
    }
}

class Boy {
    private int beardSize;

    public int getBeardSize() {
        return beardSize * 5;
    }
}

class POJO {
    private int field;

    int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}

class ValidatingPOJO extends POJO {
    @Override
    protected int getField() {
        return super.getField();
    }

    @Override
    public void setField(int field) {
        super.setField(field);
    }
}

class POJOClient {
    public static void main(String[] args) {
        final POJO pojo = new ValidatingPOJO();

        pojo.setField(5); // 1MSLoC
    }
}