package basic.enums;

public enum Color {


    RED("红色"),
    WHITE("白色"),
    BLACK("黑色");

    private String name;

    Color(String name) {
        this.name = name;
    }
}
